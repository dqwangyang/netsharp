package org.netsharp.communication.protocol.tcp.container.server.filter;

import org.netsharp.communication.core.CommunicationException;
import org.netsharp.communication.provider.SiContext;
import org.netsharp.session.ISessionManager;
import org.netsharp.session.NetsharpSession;

public class RpcSessionManager implements ISessionManager {

	public Long getUserId() {
		NetsharpSession session = this.getSession();
		if(session==null) {
			return null;
		}
		return session.getUserId();
	}

	public String getUserName() {
		NetsharpSession session = this.getSession();
		if(session==null) {
			return null;
		}
		return session.getUserName();
	}
	
	public String getMobiles() {
		NetsharpSession session = this.getSession();
		if(session==null) {
			return null;
		}
		return session.getMobiles();
	}

	public Long getDepartmentId() {
		NetsharpSession session = this.getSession();
		if(session==null) {
			return null;
		}
		return session.getDepartmentId();
	}

	public String getDepartmentIds() {
		NetsharpSession session = this.getSession();
		if(session==null) {
			return null;
		}
		return session.getDepartmentIds();
	}

	public Long getTenancyId() {
		NetsharpSession session = this.getSession();
		if(session==null) {
			return null;
		}
		return session.getTenancyId();
	}

	public String getDepartmentPathCode() {
		NetsharpSession session = this.getSession();
		if(session==null) {
			return null;
		}
		return session.getDepartmentPathCode();
	}

	public String[] getDepartmentPathCodes() {
		NetsharpSession session = this.getSession();
		if(session==null) {
			return null;
		}
		return session.getDepartmentPathCodes();
	}

	public String getTenancyName() {
		NetsharpSession session = this.getSession();
		if(session==null) {
			return null;
		}
		return session.getTenancyName();
	}

	public String getDepartmentName() {
		NetsharpSession session = this.getSession();
		if(session==null) {
			return null;
		}
		return session.getDepartmentName();
	}
	
	public Long getPostId() {
		NetsharpSession session = this.getSession();
		if(session==null) {
			return null;
		}
		return session.getPostId();
	}

	public String getPostName() {
		NetsharpSession session = this.getSession();
		if(session==null) {
			return null;
		}
		return session.getPostName();
	}
	
	public NetsharpSession getSession() {
		SiContext ctx = SiContext.getCurrent();
		if(ctx==null) {
			throw new CommunicationException("SiContext丢失");
		}
		return ctx.Request.getSession();
	}

	
}
