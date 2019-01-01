package org.netsharp.rest.tm;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.core.NetsharpException;
import org.netsharp.util.ReflectManager;
import org.netsharp.util.StringManager;

public class NoticeParmater {
	
	public Integer page = 1;
	public Integer rows = 100;
	public Integer annNum = 1600;
	public String annType;
	public String tmType;
	public String coowner;
	public String recUserName;
	public String allowUserName;
	public String byAllowUserName;
	public String appId;
	public String appIdZhiquan;
	public String bfchangedAgengedName;
	public String changeLastName;
	public String transferUserName;
	public String acceptUserName;
	public String regName;
	public String tmName;
	//商标大类,从1到45
	public Integer intCls;
	public String fileType;
	public Boolean totalYOrN = true;
	public String appDateBegin;
	public String appDateEnd;
	
	
	public String toParameters() {
		
		List<String> pars = new ArrayList<String>();
		
		
		Field[] fields = ReflectManager.getDeclaredFields(this.getClass());
		
		try {
			
			for(Field field : fields) {
				
				String par = field.getName()+"=";
				Object value = field.get(this);
				if(value!=null) {
					par += value.toString();
				}
				
				pars.add(par);
			}
		}catch(Exception ex) {
			throw new NetsharpException("生成商标报告调用参数时异常",ex);
		}
		
		
		return StringManager.join("&",pars);
	}
	
}
