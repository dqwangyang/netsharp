package org.netsharp.autoencoder.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.netsharp.autoencoder.base.IEncoderService;
import org.netsharp.autoencoder.entity.Encoder;
import org.netsharp.autoencoder.entity.EncoderRecords;
import org.netsharp.autoencoder.entity.EncoderRule;
import org.netsharp.autoencoder.entity.EncoderType;
import org.netsharp.base.IPersistableService;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.core.EntityState;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.service.PersistableService;

@Service
public class EncoderService extends PersistableService<Encoder> implements IEncoderService {
	public EncoderService() {
		super();
		this.type = Encoder.class;
	}

	@Override
	public String getNextCode(String bizType, String entityType) {
		// 根据传递的实体类名查询编码记录表，取得下一个编码并更新编码记录表
		Oql oql = new Oql();
		{
			oql.setType(EncoderRecords.class);
			oql.setSelects("*");
			oql.setFilter("is_valid=1 and billType=? and entityType=?");
			oql.setParameters(new QueryParameters());
			{
				oql.getParameters().add("@billType", bizType, Types.NVARCHAR);
				oql.getParameters().add("@entityType", entityType, Types.NVARCHAR);
			}
		}
		IPersistableService<EncoderRecords> service = ServiceFactory.create(IEncoderService.class);
		List<EncoderRecords> ls = service.queryList(oql);
		String bm = null;

		for (EncoderRecords en : ls) {
			String temp = en.getFixationType();// rs.getString("fixation_type");
			String dateFormatStr = en.getDateFormat();// rs.getString("date_format");
			String[] dateFormat = null;
			if (dateFormatStr != null) {
				dateFormat = dateFormatStr.split(",");
			}
			int index = en.getCurrentIndex();// rs.getInt("current_index");
			int iLength = en.getIndexLength();// rs.getInt("index_length");
			bm = buildCode(temp, dateFormat, ++index, iLength);
			updateIndex(bizType, entityType, index);
		}

		return bm;
	}

	@Override
	public Encoder save(Encoder entity) {
		if (EntityState.New.equals(entity.getEntityState())) {
			Encoder old = this.byBizType(entity.getBillType());
			if (old != null) {
				throw new BusinessException("单据类型已存在！");
			}
		}
		try {
			Class.forName(entity.getEntityType());
		} catch (ClassNotFoundException e) {
			throw new BusinessException("实体类型配置错误，请修改后重新保存！");
		}
		addOrUpdateEncoderRecord(entity);
		return super.save(entity);
	}

	@Override
	public Encoder byBizType(String bizType) {
		Oql oql = new Oql();
		{
			oql.setSelects("*");
			oql.setType(this.type);
			oql.setFilter("billType=?");
			QueryParameters qps = new QueryParameters();
			{
				qps.add("@billType", bizType, Types.VARCHAR);
			}
			oql.setParameters(qps);
		}
		return this.queryFirst(oql);
	}

	@Override
	public List<Encoder> saves(List<Encoder> entities) {
		for (Encoder entity : entities) {
			addOrUpdateEncoderRecord(entity);
		}
		return super.saves(entities);
	}

	/**
	 * 根据clss更新is_valid状态为1的index值
	 * 
	 * @param clss
	 * @param index
	 */
	private void updateIndex(String bizType, String entityType, int index) {
		String cmdText = "update sys_bd_code_records set current_index = ? where is_valid = 1 and bill_type = ? and entity_type=?";
		QueryParameters qps = new QueryParameters();
		qps.add("@current_index", index, Types.INTEGER);
		qps.add("@bill_type", bizType, Types.VARCHAR);
		qps.add("@entity_type", entityType, Types.VARCHAR);
		this.pm.executeNonQuery(cmdText, qps);
	}

	/**
	 * 更具条件生成编码
	 * 
	 * @param temp
	 *            编码模板
	 * @param dateFormat
	 *            要替换的日期格式串
	 * @param index
	 *            当前序列值
	 * @param iLength
	 *            序列的长度
	 * @return 返回当前序列的编码
	 */
	private String buildCode(String temp, String[] dateFormat, int index, int iLength) {
		StringBuilder sb = new StringBuilder();
		if (temp != null) {
			sb.append(temp);
		}
		SimpleDateFormat sdf = null;
		int start = 0;
		if (dateFormat != null) {
			for (int i = 0; i < dateFormat.length; i++) {
				sdf = new SimpleDateFormat(dateFormat[i]);
				start = sb.indexOf(dateFormat[i]);
				sb.replace(start, start + dateFormat[i].length(), sdf.format(Calendar.getInstance().getTime()));
			}
		}
		int len = String.valueOf(index).length();
		start = sb.length() - len;
		sb.replace(start, start + len, String.valueOf(index));
		return sb.toString();
	}

	private void addOrUpdateEncoderRecord(Encoder entity) {

		List<EncoderRule> eRules = entity.getEncoderRules();
		StringBuilder temp = new StringBuilder("@@@@@@");
		StringBuilder dateFormat = new StringBuilder();
		int indexLength = 0;
		int start = 0;
		for (EncoderRule eRule : eRules) {
			if (eRule.getRowType() == EncoderType.A) {
				start = temp.indexOf("@", eRule.getRowNum() - 1);
				temp.replace(start, start + 1, eRule.getRuleFormat());
			} else if (eRule.getRowType() == EncoderType.B) {
				start = temp.indexOf("@", eRule.getRowNum() - 1);
				temp.replace(start, start + 1, eRule.getRuleFormat());
				dateFormat.append(eRule.getRuleFormat()).append(",");
			} else if (eRule.getRowType() == EncoderType.C) {
				indexLength = eRule.getRuleLength();
				start = temp.indexOf("@", eRule.getRowNum() - 1);
				if (eRule.getRuleFormat().length() < eRule.getRuleLength()) {
					StringBuffer sb = new StringBuffer();
					for (int i = 0; i < indexLength; i++) {
						sb.append("0");
					}
					temp.replace(start, start + 1, sb.toString());
				} else {
					temp.replace(start, start + 1, eRule.getRuleFormat());
				}

			}
		}
		if (dateFormat.length() > 0) {
			dateFormat.deleteCharAt(dateFormat.length() - 1);
		}

		if (entity.getEntityState() == EntityState.New) {
			EncoderRecords record = new EncoderRecords();
			{
				record.setEntityState(EntityState.New);
				record.setEncoder(entity);
				record.setBillType(entity.getBillType());
				record.setFixationType(temp.toString().replaceAll("@", ""));
				record.setIsValid(true);
				record.setExpandClassName(entity.getExpandClassName());
				record.setEntityType(entity.getEntityType());
				record.setDateFormat(dateFormat.toString());
				record.setIndexLength(indexLength);
				record.setCurrentIndex(0);
			}
			List<EncoderRecords> erds = new ArrayList<EncoderRecords>();
			erds.add(record);
			entity.setEncoderRecords(erds);
		} else if (entity.getEntityState() == EntityState.Persist) {
			List<EncoderRecords> erds = entity.getEncoderRecords();
			EncoderRecords record = null;
			if (erds != null && erds.size() > 0) {
				for (int i = 0, len = erds.size(); i < len; i++) {
					if (erds.get(i).getIsValid()) {
						record = erds.get(i);
						break;
					}
				}
			} else {
				erds = new ArrayList<EncoderRecords>();
				record = new EncoderRecords();
				erds.add(record);
			}

			{
				record.setEncoder(entity);
				record.setBillType(entity.getBillType());
				record.setFixationType(temp.toString().replaceAll("@", ""));
				record.setIsValid(true);
				record.setExpandClassName(entity.getExpandClassName());
				record.setEntityType(entity.getEntityType());
				record.setDateFormat(dateFormat.toString());
				record.setIndexLength(indexLength);
			}
			entity.setEncoderRecords(erds);

		}
	}

	@Override
	public String createEncodeRule(Class<?> type, String briefCode, String fixCode, String dateformat, Integer serialLength) {
		IPersistableService<EncoderRecords> service = ServiceFactory.create(IPersistableService.class);
		String bizType = type.getSimpleName() + "_" + briefCode;
		Oql oql = new Oql();
		{
			oql.setSelects("*");
			oql.setType(EncoderRecords.class);
			oql.setFilter("billType='" + bizType + "'");
		}
		EncoderRecords record = service.queryFirst(oql);
		if (record == null) {
			record = new EncoderRecords();
			{
				record.toNew();
				record.setBillType(bizType);
				record.setEntityType(type.getName());
				record.setDateFormat(dateformat);
				record.setCurrentIndex(0);
				record.setIndexLength(serialLength);
				record.setIsValid(true);
				StringBuilder fixationType = new StringBuilder();
				if (briefCode == null)
					throw new BusinessException("briefCode 门店简码不能为空!");
				fixationType.append(briefCode);
				if (fixCode != null)
					fixationType.append(fixCode);
				if (dateformat != null)
					fixationType.append(dateformat);
				for (int i = 0; i < serialLength; i++) {
					fixationType.append("0");
				}
				record.setFixationType(fixationType.toString());
			}
			service.save(record);
		}

		return record.getBillType();
	}

	@Override
	public String getExpandClassName(String bizType, String entityType) {
		String cmdText = "select * from sys_bd_code_records where is_valid=1 and bill_type=? and entity_type=?";
		QueryParameters qps = new QueryParameters();
		qps.add("@bill_type", bizType, Types.VARCHAR);
		qps.add("@entity_type", entityType, Types.VARCHAR);
		ResultSet rs = this.pm.executeReader(cmdText, qps);
		String className = null;
		try {
			if (rs.next()) {
				className = rs.getString("expand_classname");
			}
		} catch (SQLException e) {
		}
		return className;
	}

}
