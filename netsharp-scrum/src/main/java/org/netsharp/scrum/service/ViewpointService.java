package org.netsharp.scrum.service;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.organization.entity.Employee;
import org.netsharp.scrum.base.IViewpointService;
import org.netsharp.scrum.entity.Viewpoint;
import org.netsharp.scrum.entity.ViewpointFans;
import org.netsharp.service.BizService;
import org.netsharp.session.SessionManager;
import org.netsharp.util.StringManager;

@Service
public class ViewpointService extends BizService<Viewpoint> implements IViewpointService {

	public ViewpointService() {
		super();
		this.type = Viewpoint.class;
	}
	
	@Override
	public Viewpoint newInstance() {
		
		Viewpoint entity = super.newInstance();
		
		entity.setOwnerId(SessionManager.getUserId());
		
		Employee e = new Employee();
		{
			e.setId(SessionManager.getUserId());
			e.setName(SessionManager.getUserName());
		}
		
		entity.setOwner(e);
		
		return entity;
	}
	
	public Viewpoint attachFans(Viewpoint entity) {
		
		Integer userId = SessionManager.getUserId();
		for(ViewpointFans fans : entity.getFans()) {
			if(userId.equals( fans.getFansId())) {
				fans.setReadTimes(fans.getReadTimes().intValue()+1);
				fans.toPersist();
				break;
			}
		}
		
		ViewpointFans fans = new ViewpointFans();
		{
			fans.toNew();
			fans.setViewpointId(entity.getId());
			fans.setReadTimes(1);
			fans.setFansId(userId);
			fans.setIsRead(true);
			
			Employee e = new Employee();
			{
				e.setId(userId);
				e.setName(SessionManager.getUserName());
			}
			
			fans.setFans(e);
		}
		
		entity.getFans().add(fans);
		
		List<String> strs = new ArrayList<String>();
		for(ViewpointFans f : entity.getFans()) {
			strs.add(f.getFans().getName());
		}
		
		entity.setFansDisplay(StringManager.join(",", strs));
		entity.toPersist();
		
		this.pm.save(entity);
		
		return entity;
	}

}
