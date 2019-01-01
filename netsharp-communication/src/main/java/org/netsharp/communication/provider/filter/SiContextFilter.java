package org.netsharp.communication.provider.filter;

import org.netsharp.communication.core.CommunicationException;
import org.netsharp.communication.provider.IServiceFilter;
import org.netsharp.communication.provider.SiContext;

public class SiContextFilter implements IServiceFilter {
	
	SiContext current;
	
	public void invoking(SiContext ctx) {

		this.current = SiContext.getCurrent();
		
		if (this.current == null) {
			//顶级服务调用
			ctx.Top = ctx;
		} 
		else {
			//非顶级服务调用,SiContext需要切换
			this.current.getSubs().add(ctx);
			ctx.Parent = current;
			ctx.Top=current.Top;
		}
		
		SiContext.setCurrent(ctx);
	}

	/**
	 * 服务嵌套调用的时候要有多个Context
	 * 子调用结束则设置parent为线程上下文context
	 * */
	public void invoked(SiContext ctx) {
		if(ctx.Parent!=null) {
			SiContext.setCurrent(ctx.Parent);
		}else if(ctx.Top == ctx){
			SiContext.clean();
		}else {
			throw new CommunicationException("服务嵌套调用SiContext混乱");
		}
	}
}
