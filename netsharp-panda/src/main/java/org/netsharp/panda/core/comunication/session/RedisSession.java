package org.netsharp.panda.core.comunication.session;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.cache.base.CacheCommandFactory;
import org.netsharp.cache.base.ICacheCommand;
import org.netsharp.cache.base.dict.CacheType;
import org.netsharp.panda.core.PandaConfiguration;
import org.netsharp.panda.session.PandaSession;

public class RedisSession implements ISession {
	
	private static Log logger = LogFactory.getLog(RedisSession.class);
	public static final String SESSION_TICKET_KEY = "SESSION_TICKET_KEY";
	private static ICacheCommand redis = CacheCommandFactory.create(CacheType.redis,"session");
	private String id;
	
	private HttpServletRequest request;

	
	
	public RedisSession(HttpServletRequest request) {
		this.request = request;
	}

	public String getId() {
		if(id==null) {
			this.id =  this.getCookie(SESSION_TICKET_KEY);
		}
		return this.id;
	}
	
	public Object getSession(String key) {
		String rediskey = this.getId() + key;
		logger.trace("session get:"+rediskey);
		return redis.get(rediskey);
		
	}
	
	public void setSession(String key, Object obj) {
		String rediskey = this.getId() + key;
		
		//在登录的时候this.getId()取到的是上次的cookie
		//所以优先去PandaSession中设置的值
		PandaSession ps = (PandaSession)obj;
		if(ps!=null) {
			rediskey = ps.getSession().getSessionId()+key;
		}
		
		logger.trace("session set :"+rediskey);
		int timeout = PandaConfiguration.getInstance().getSessionTimtout() * 60;
		redis.set(rediskey, timeout , obj);
	}

	public void removeSession(String key) {
		String rediskey = this.getId() + key;
		logger.debug("session remove:"+rediskey);
		redis.del(rediskey);
	}
	
	private String getCookie(String cookieName) {
		
		if(this.request.getCookies()==null) {
			return null;
		}
		
		for(Cookie cookie : this.request.getCookies()) {
			if(cookieName.equalsIgnoreCase(cookie.getName())) {
				return cookie.getValue();
			}
		}

		return null;
	}
}
