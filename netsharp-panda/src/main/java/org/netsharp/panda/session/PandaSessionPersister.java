package org.netsharp.panda.session;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.organization.entity.Employee;
import org.netsharp.panda.core.HttpContext;
import org.netsharp.panda.core.PandaConfiguration;
import org.netsharp.panda.core.comunication.IRequest;

public class PandaSessionPersister {
	
	private static Log logger = LogFactory.getLog(PandaSessionPersister.class);

	public static final String SESSION = "_SESSION";
		
	public static boolean logined() {
		
		IRequest request = HttpContext.getCurrent().getRequest();
		PandaSession ps = (PandaSession) request.getSession(SESSION);
		
		return ps != null;
	}

	// 从Session中得到用户权限设置
	public static PandaSession get() {

		IRequest request = HttpContext.getCurrent().getRequest();
		PandaSession ps = (PandaSession) request.getSession(SESSION);
		
		logger.trace("session get : " + request.getSessionId());
		
		if (ps != null) {
			//1.需要测试session的失效场景，首先设置session的失效期为5分钟，那么redis的失效时间不会超过10分钟，那么看用户是不是需要重新登录
			//2.因为每次get都去set，那么redis的失效时间是什么情况，是不是以最后一次的set为失效时间
			//3.虽然开发设置失效时间为35分钟，但是redis并不是到了35分钟就让缓存时效，他应该是有自己的策略，这样也会导致session的失效处理不可控
			request.setSession(SESSION, ps);
			return ps;
		}else {
			return null;
		}
	}
	
	// 根据员工添加Session
	public static void add(Employee employee) {

		IRequest request = HttpContext.getCurrent().getRequest();
		int timeout = PandaConfiguration.getInstance().getSessionTimtout() * 60 ;
		request.setsetMaxInactiveInterval( timeout );
		
		logger.trace("session add : " + request.getSessionId());
		
		PandaSession ps = new PandaSession(employee.getTicket(),employee.getId(), employee.getName(), employee.getMobile(),employee.getPhoto());
		PermissionQuery query = new PermissionQuery();
		query.query(ps);
		
		UserClient uc = new UserClient();
		{
			uc.setIp(request.getClientIp());
			uc.setUserAgent(request.getUserAgent());
			
			ps.setUserClient(uc);
		}
		
		ps.getSession().setLastedTime(new Date());

		request.setSession(SESSION, ps);
	}

	// 释放用户权限设置
	// session注销或者注销时执行
	public static void remove() {

		HttpContext ctx = HttpContext.getCurrent();
		IRequest request = ctx.getRequest();
		
		logger.trace("session remove : " + request.getSessionId());
		
		request.removeSession(SESSION);
	}
	
	// 管理员修改用户权限时使用
	public static void reset() {
	}
}
