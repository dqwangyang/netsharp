package org.netsharp.panda.session.startup;

import org.netsharp.panda.session.PandaSession;
import org.netsharp.panda.session.PandaSessionPersister;
import org.netsharp.session.ISessionManager;
import org.netsharp.session.NetsharpSession;

public class PandaSessionManager implements ISessionManager{
	
	public Integer getUserId(){
		PandaSession ps = PandaSessionPersister.get();
		if(ps==null){
			return null;
		}
		return ps.getSession().getUserId();
	}
	
	public String getUserName(){
		PandaSession ps = PandaSessionPersister.get();
		if(ps==null){
			return null;
		}
		return ps.getSession().getUserName();
	}

	public String getMobiles() {
		PandaSession ps = PandaSessionPersister.get();
		if(ps==null){
			return null;
		}
		return ps.getSession().getMobiles();
	}

	public Integer getDepartmentId() {
		PandaSession ps = PandaSessionPersister.get();
		if(ps==null){
			return null;
		}
		return ps.getSession().getDepartmentId();
	}

	public String getDepartmentIds() {
		PandaSession ps = PandaSessionPersister.get();
		if(ps==null){
			return null;
		}
		return ps.getSession().getDepartmentIds();
	}

	public Integer getTenancyId() {
		PandaSession ps = PandaSessionPersister.get();
		if(ps==null){
			return null;
		}
		return ps.getSession().getTenancyId();
	}

	public String getDepartmentPathCode() {
		
		PandaSession ps = PandaSessionPersister.get();
		if(ps==null){
			return null;
		}
		return ps.getSession().getDepartmentPathCode();
	}

	public String[] getDepartmentPathCodes() {
		PandaSession ps = PandaSessionPersister.get();
		if(ps==null){
			return null;
		}
		return ps.getSession().getDepartmentPathCodes();
	}

	public String getTenancyName() {
		PandaSession ps = PandaSessionPersister.get();
		if(ps==null){
			return null;
		}
		return ps.getSession().getTenancyName();
	}

	public String getDepartmentName() {
		PandaSession ps = PandaSessionPersister.get();
		if(ps==null){
			return null;
		}
		return ps.getSession().getDepartmentName();
	}
	
	public Integer getPostId() {
		PandaSession ps = PandaSessionPersister.get();
		if(ps==null){
			return null;
		}
		return ps.getSession().getPostId();
	}

	public String getPostName() {
		PandaSession ps = PandaSessionPersister.get();
		if(ps==null){
			return null;
		}
		return ps.getSession().getPostName();
	}

	public NetsharpSession getSession() {
		PandaSession ps = PandaSessionPersister.get();
		if(ps==null){
			return null;
		}
		return ps.getSession();
	}
}
