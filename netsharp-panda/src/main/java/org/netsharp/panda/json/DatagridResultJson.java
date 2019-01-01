package org.netsharp.panda.json;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.netsharp.core.Column;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.convertor.impl.DateConvertor;
import org.netsharp.core.property.IProperty;
import org.netsharp.entity.IPersistable;
import org.netsharp.panda.commerce.EasyuiDatagridResult;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.util.JavaEnumManager;
import org.netsharp.util.StringManager;

public class DatagridResultJson {

	private EasyuiDatagridResult result;
	private PDatagrid pdatagrid;

	public DatagridResultJson(EasyuiDatagridResult result, PDatagrid pdatagrid) {

		this.result = result;
		this.pdatagrid = pdatagrid;
	}

	public Object parse() {

		Mtable meta = MtableManager.getMtable(pdatagrid.getEntityId());
		Column keyColumn = meta.getKeyColumn();
		IProperty keyProperty = keyColumn.getProperty();
		DateConvertor convertor = new DateConvertor();
		
		List<HashMap<String, Object>> rows = new ArrayList<HashMap<String, Object>>();

		for (Object obj : result.getRows()) {
			
			IPersistable p = (IPersistable) obj;
			HashMap<String, Object> item = new HashMap<String, Object>();
			Object key = keyProperty.field(p);
			item.put(keyColumn.getPropertyName(), key);

			for (PDatagridColumn column : this.pdatagrid.getColumns()) {

				String propertyName = column.getPropertyName();
				Object value = p.get(propertyName);
				if(value == null) {
					continue;
				}
				
				if (value instanceof Date) {
					value = convertor.toString(value);
				} else if (value.getClass().isEnum()) {
					value = JavaEnumManager.getText((Enum<?>) value);
				} else if (value instanceof BigDecimal) {
					// 处理BigDecimal被全局2位小数覆盖的问题
					if (column.getPrecision() != null) {
						BigDecimal v = new BigDecimal(value.toString());
						value = v.setScale(column.getPrecision(), BigDecimal.ROUND_HALF_UP).toString();
					}
				}
				item.put(column.getColumnName(), value);
			}
			rows.add(item);
		}
		
		setSensitive(rows,pdatagrid,meta);
		
		HashMap<String, Object> maps = new HashMap<String, Object>();
		{
			maps.put("total", this.result.getTotal());
			maps.put("rows", rows);
			if (this.result.getFooter() != null && this.result.getFooter().size() > 0) {
				maps.put("footer", this.result.getFooter());
			}
		}

		return maps;
	}
	
	//敏感信息*处理
	public static void setSensitive(List<HashMap<String, Object>> rows,PDatagrid pdatagrid,Mtable meta) {
		
		List<String> sensitiveFileds = new ArrayList<String>();
		
		for (PDatagridColumn column : pdatagrid.getColumns()) {
			Column property = meta.findProperty(column.getPropertyName());
			if(property==null) {
				continue;
			}
			if(property.isMobiles()) {
				sensitiveFileds.add(column.getColumnName());
			}else if(property.isSensitive()) {
				sensitiveFileds.add(column.getColumnName());
			}
		}
		
		if(sensitiveFileds.size()==0) {
			return ;
		}
		
		for(HashMap<String, Object> item : rows) {
			
			for(String key : sensitiveFileds) {
				Object value = item.get(key);
				if(value==null) {
					continue;
				}
				
				String mobile = value.toString();
				if(mobile.length()<3) {
					continue;
				}
				int step = mobile.length() / 3 + 1 ;
				if((step * 2) >= mobile.length()) {
					step -=1;
				}
				
				mobile = mobile.substring(0, step-1) + StringManager.padLeft("", step, '*') + mobile.substring(step*2);
				
				item.put(key, mobile);
			}
		}
	}
}
