package org.netsharp.panda.servlet.invoke.rest.filters;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.panda.servlet.filters.IPandaFilter;
import org.netsharp.panda.servlet.invoke.PandaContext;
import org.netsharp.panda.servlet.invoke.rest.RestContext;
import org.netsharp.panda.servlet.invoke.rest.Result;

/*
 * rest调用异常处理，主要处理的是后台的日志
 * 前端的异常通过Result.Type返回到前端进行处理
 * */
public class ExceptionFilter implements IPandaFilter {
	
	protected static final Log logger = LogFactory.getLog(ExceptionFilter.class.getName());

	@Override
	public void invoking(PandaContext pandaContext) {
		
		
	}

	@Override
	public void invoked(PandaContext pandaContext) {
		
		RestContext ctx = (RestContext) pandaContext;
		Result<Object> result = ctx.getResponse();
		
		if(result.type == Result.Type.error) {
			logger.error(result.message, ctx.getException());
		}
		
		if(result.type == Result.Type.fatal) {
			logger.fatal(result.message, ctx.getException());
		}
	}
}

