package org.netsharp.wx.ea.service;

import java.sql.Types;

import org.netsharp.communication.Service;
import org.netsharp.core.BusinessException;
import org.netsharp.core.DataTable;
import org.netsharp.core.EntityState;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.entity.IPersistable;
import org.netsharp.service.PersistableService;
import org.netsharp.wx.ea.base.IWxeaMessageService;
import org.netsharp.wx.ea.entity.WxeaMessage;

@Service
public class WxeaMessageService extends PersistableService<WxeaMessage> implements IWxeaMessageService {

	public WxeaMessageService() {
		super();
		this.type = WxeaMessage.class;
	}

	@Override
	public WxeaMessage save(WxeaMessage entity) {

		if (EntityState.Deleted.equals(entity.getEntityState())) {
			WxeaMessage old = this.byId(entity.getId());
			if (old.getFixReceivers() != null && old.getFixReceivers().size() > 0) {
				throw new BusinessException("消息下有固定接收人,请删除所有接收人后再删除表单！");
			}
			entity = super.save(entity);
		} else {
			WxeaMessage old = this.byCode(entity.getCode());
			if (old != null && !old.getId().equals(entity.getId())) {
				throw new BusinessException("此消息编码已存在，请修改编码后重新保存。");
			}
			entity = super.save(entity);
			updateTOUsers(entity);
		}

		return entity;
	}

	private void updateTOUsers(WxeaMessage entity) {
		String cmdText = "UPDATE wx_ea_message SET to_user=(SELECT GROUP_CONCAT(DISTINCT e.mobile SEPARATOR '|') FROM wx_ea_message_receiver r left JOIN sys_permission_employee e on r.receiver_id = e.id WHERE pmconfig_id=?) WHERE id=?";
		QueryParameters qps = new QueryParameters();
		qps.add("@pmconfig_id", entity.getId(), Types.INTEGER);
		qps.add("@id", entity.getId(), Types.INTEGER);
		this.pm.executeNonQuery(cmdText, qps);

	}

	/**
	 * Title: byWxUserConfigurationType Description: 根据应用类型查询应用接收人
	 * 
	 * @param type
	 * @return
	 * @see org.netsharp.wx.IWxeaMessageService.IWxUserconfigurationService#byWxUserConfigurationType(org.netsharp.wx.dic.WxUserConfigurationType)
	 */
	@Override
	public WxeaMessage byCode(String code) {
		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("WxeaMessage.*,wxpaConfiguration.*,fixReceivers.*");
			oql.setFilter("code = ?");
			QueryParameters qps = new QueryParameters();
			qps.add("@code", code, Types.VARCHAR);
			oql.setParameters(qps);
		}
		return this.queryFirst(oql);
	}

	@Override
	public String getFixReceiversByCode(String code) {
		String cmdText = "SELECT GROUP_CONCAT(DISTINCT fix.receiver_id SEPARATOR '|') AS receivers FROM wx_ea_configuration cnf LEFT JOIN wx_ea_message_receiver fix ON cnf.id=fix.pmconfig_id WHERE cnf.code=?";
		QueryParameters qps = new QueryParameters();
		qps.add("@code", code, Types.VARCHAR);
		DataTable dt = this.pm.executeTable(cmdText, qps);
		if (dt.get(0).get("receivers") != null)
			return dt.get(0).get("receivers").toString();
		else
			return "";
	}

	@Override
	public String getSendMessage(String code, IPersistable entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
