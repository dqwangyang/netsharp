package org.netsharp.dbm.service.parse;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.core.NetsharpException;
import org.netsharp.util.StringManager;

public class DbmParser {
	
	private List<Expression> expressions;
	
	public DbmQL parse(String cmdText) {
		
		this.getExpression();
		
		int cindex = 0;
		int eindex =0;
		
		while(cindex<cmdText.length()) {
			
			if(eindex == expressions.size()) {
				break;
			}
			
			Expression expr = expressions.get(eindex);
			
			cindex = cmdText.toLowerCase().indexOf(expr.getKey(), cindex);
			if(cindex<0) {
				break;
			}
			cindex += expr.getKey().length();//前进到当前关键字之后
			
			if(eindex+1<expressions.size()) {
				
				Expression exprNext = expressions.get(eindex+1);
				int i = cmdText.toLowerCase().indexOf(exprNext.getKey(), cindex);
				if(i>=0) {
					String value = cmdText.substring(cindex,i-1);
					value = StringManager.trim(value, ' ');
					expr.setValue(value);
					
					cindex = i;
					eindex++;
				}
				else {
					String value = cmdText.substring(cindex);
					value = StringManager.trim(value, ' ');
					expr.setValue(value);
					
					break;
				}
			}
			else {
				String value = cmdText.substring(cindex);
				value = StringManager.trim(value, ' ');
				expr.setValue(value);
				
				break;
			}
		}

		DbmQL dbmQL = new DbmQL();
		{
			//
			Expression exp = this.getExpression("from");
			if (StringManager.isNullOrEmpty(exp.getValue())) {
				throw new NetsharpException("未能解析entityId");
			}
			dbmQL.setFrom(exp.getValue());
			
			if(exp.getValue().indexOf('.')>0) {
				dbmQL.setOql(true);
			}

			//
			exp = this.getExpression("select");
			if (StringManager.isNullOrEmpty(exp.getValue())) {
				throw new NetsharpException("未能解析select");
			}
			dbmQL.setSelect(exp.getValue());

			//
			exp = this.getExpression("where");
			if (!StringManager.isNullOrEmpty(exp.getValue())) {
				dbmQL.setWhere(exp.getValue());
			}

			//
			exp = this.getExpression("order by");
			if (!StringManager.isNullOrEmpty(exp.getValue())) {
				dbmQL.setOrderby(exp.getValue());
			}
			
			//
			exp = this.getExpression("limit");
			if (!StringManager.isNullOrEmpty(exp.getValue())) {
				dbmQL.setLimit(exp.getValue());
			}
		}
		
		System.out.println(dbmQL);

		return dbmQL;
	}
	
	private void getExpression(){
		this.expressions = new ArrayList<Expression>();
		{
			Expression ex = new Expression(); {
				ex.setKey("select");
				expressions.add(ex);
			}
			
			ex = new Expression(); {
				ex.setKey("from");
				expressions.add(ex);
			}
			
			ex = new Expression(); {
				ex.setKey("where");
				expressions.add(ex);
			}
			
			ex = new Expression(); {
				ex.setKey("order by");
				expressions.add(ex);
			}
			
			ex = new Expression(); {
				ex.setKey("limit");
				expressions.add(ex);
			}
		}
	}
	
	private Expression getExpression(String key) {
		
		for(Expression exp : this.expressions) {
			if(StringManager.equals(key, exp.getKey())) {
				return exp;
			}
		}
		
		throw new NetsharpException("dbm异常，没有表达式："+key);
	}
}
