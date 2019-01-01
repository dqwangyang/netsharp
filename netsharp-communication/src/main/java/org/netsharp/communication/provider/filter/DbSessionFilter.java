package org.netsharp.communication.provider.filter;

import java.lang.reflect.Method;

import org.netsharp.communication.provider.IServiceFilter;
import org.netsharp.communication.provider.SiContext;
import org.netsharp.core.annotations.Transaction;
import org.netsharp.dataccess.DbSession;

public class DbSessionFilter implements IServiceFilter {
	
	@Override
	public void invoking(SiContext ctx) {
		
		Method method=ctx.InterfaceMethod;
		
		if(ctx.Top != ctx){
			return;
		}
		
		Transaction transaction= method.getAnnotation(Transaction.class);
		ctx.IsTransaction = (transaction!=null) ;
		
		if(!ctx.IsTransaction){
			return;
		}
		
		DbSession session= DbSession.getSession();
		if(session==null){
			session = DbSession.start();
		}
				
		session.beginTransaction();
	}

	@Override
	public void invoked(SiContext ctx) {
		
		DbSession session=DbSession.getSession();
		
		if(session==null){
			return;
		}
		
		if(ctx.Top!=ctx){
			return;
		}
		
		if(ctx.IsTransaction){
			if(ctx.Response.getSucceed()){
				session.commit();
			}
			else{
				session.rollback();
			}
		}
		
		// 未启动事务但是启动了查询的这种场景也是需要执行session关闭的
		session.close();
	}
}