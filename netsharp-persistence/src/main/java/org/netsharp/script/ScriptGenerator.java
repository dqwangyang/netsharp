package org.netsharp.script;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.netsharp.core.Column;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.NetsharpException;
import org.netsharp.core.TableSubs;
import org.netsharp.util.DateManage;
import org.netsharp.util.StringManager;

public class ScriptGenerator {
	
	public List<String> generateComposite(Object entity){
		if(entity==null){
			throw new NetsharpException("参数为空，不能生成INSERT脚本");
		}
		List<String> sqls = new ArrayList<String>();
		Mtable meta = MtableManager.getMtable(entity.getClass());
		String[] ss = this.generate(entity);
		for(String sql : ss){
			sqls.add(sql);
		}
		
		for(TableSubs subMetas : meta.getSubs().values()){
			Iterable<?> subs = subMetas.getSubs(entity);
			
			for(Object sub : subs){
				
				List<String> subSqls = this.generateComposite(sub);
				sqls.addAll(subSqls);
			}
		}
		
		return sqls;
	}
	
	public String[] generate(Object entity){
		if(entity==null){
			throw new NetsharpException("参数为空，不能生成INSERT脚本");
		}
		Mtable meta = MtableManager.getMtable(entity.getClass());
		ArrayList<Column> columns = meta.getColumns();
		
		String[] cs = new String[columns.size()];
		Object[] values = new Object[columns.size()];
		
		for(int i=0;i<columns.size();i++){
			Column column = columns.get(i);
			cs[i]=column.getColumnName();
			
			Object value = column.getProperty().field(entity);
			values[i]=value;
		}
		
		String[] sqls = new String[2];
		
		sqls[0] = "DELETE FROM "+meta.getTableName()+" WHERE "+meta.getKeyColumn().getColumnName()+" = "+this.generateValue( meta.getId(entity) ) +";";
		sqls[1] = this.generate(meta.getTableName(), cs, values) +";";
		
		return sqls;
	}
	
	public String generate(String tableName,String[] columns,Object[] values){
		if(columns.length!=values.length){
			throw new NetsharpException("columns.length与values.length 不一致");
		}
		
		String[] vs = new String[columns.length];
		for(int i=0;i<columns.length;i++){
			String v = this.generateValue(values[i]);
			vs[i]=v;
		}
		
		String sql = "INSERT INTO " + tableName +" ("+StringManager.join(",",columns)+") VALUES ("+StringManager.join(",",vs)+")";
		
		return sql;
	}
	
	public String generateValue(Object value){
		if(value==null){
			return "NULL";
		}
		else if(value instanceof String){
			return "'"+value.toString().replace("'", "\\'")+"'";
		}
		else if(value instanceof Date){
			return "'"+DateManage.toLongString((Date)value)+"'";
		}
		else if(value instanceof Timestamp){
			return "'"+DateManage.toLongString((Timestamp)value)+"'";
		}
		else if(value instanceof UUID){
			return "'"+value.toString()+"'";
		}
		else if(value instanceof Short){
			return value.toString();
		}
		else if(value instanceof Integer){
			return value.toString();
		}
		else if(value instanceof Long){
			return value.toString();
		}
		else if(value instanceof Float){
			return value.toString();
		}else if(value instanceof Double){
			return value.toString();
		}
		else if(value instanceof BigDecimal){
			return value.toString();
		}else if(value instanceof Boolean){
			Boolean b = (Boolean)value;
			return b ? "1" : "0";
		}
		else if(value instanceof UUID){
			return "'"+value.toString()+"'";
		}
		else if(value.getClass().isEnum()){
			return "'"+value.toString()+"'";
		}
		else{
			throw new NetsharpException("脚本生成不支持的value类型:"+value.getClass().getName());
		}
	}
}
