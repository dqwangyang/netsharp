package org.netsharp.panda.commerce;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.panda.dic.IntelligentMode;
import org.netsharp.util.StringManager;

/**
 * @ClassName: FilterParameter
 * @Description:TODO 查询参数对象
 * @author: 韩伟
 * @date: 2018年1月5日 上午11:43:45
 * 
 * @Copyright: 2018 www.yikuaxiu.com Inc. All rights reserved.
 */
public class FilterParameter {

	/**
	 * @Fields key : TODO(关键字)
	 */
	private String key;

	/**
	 * @Fields value1 : TODO(值1)
	 */
	private Object value1;

	/**
	 * @Fields value2 : TODO(值2)
	 */
	private Object value2;

	/**
	 * @Fields intelligentMode : TODO(匹配方式)
	 */
	private IntelligentMode intelligentMode1;

	private IntelligentMode intelligentMode2;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Object getValue1() {
		if (null == key){
			return value1;
		}
		if(!"keyword".equals(key)){
			return value1;
		}
		return StringManager.escapeSql(value1);
	}

	public void setValue1(Object value1) {
		this.value1 = value1;
	}

	public Object getValue2() {
		if (null == key){
			return value2;
		}
		if(!"keyword".equals(key)){
			return value2;
		}
		return StringManager.escapeSql(value2);
	}

	public void setValue2(Object value2) {
		this.value2 = value2;
	}

	public IntelligentMode getIntelligentMode1() {
		return intelligentMode1;
	}

	public void setIntelligentMode1(IntelligentMode intelligentMode1) {
		this.intelligentMode1 = intelligentMode1;
	}

	public IntelligentMode getIntelligentMode2() {
		return intelligentMode2;
	}

	public void setIntelligentMode2(IntelligentMode intelligentMode2) {
		this.intelligentMode2 = intelligentMode2;
	}

	public String getFilter() {

		List<String> ss = new ArrayList<String>();
		
		if(this.value1 != null && !StringManager.isNullOrEmpty(this.value1.toString())){

			String expression1 = this.intelligentMode1.getExpression();
			String filter1 = String.format(expression1, this.key, this.value1);
			ss.add(filter1);
		}
		if (this.value2 != null) {

			String expression2 = this.intelligentMode2.getExpression();
			String filter2 = String.format(expression2, this.key, this.value2);
			ss.add(filter2);
		}

		String filter = StringManager.join(" and ", ss);
		return filter;
	}
}
