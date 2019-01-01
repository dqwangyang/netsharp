package org.netsharp.scrum.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.EntityState;
import org.netsharp.scrum.base.IActivityPlanService;
import org.netsharp.scrum.entity.ActivityPlan;
import org.netsharp.service.BizService;
import org.netsharp.util.DateManage;
import org.netsharp.util.StringManager;
import org.netsharp.wx.ea.base.IEaMessageService;

@Service
public class ActivityPlanService extends BizService<ActivityPlan> implements IActivityPlanService {
	
	public ActivityPlanService() {
		super();
		this.type = ActivityPlan.class;
	}

	@Override
	public ActivityPlan save(ActivityPlan entity) {

		EntityState state = entity.getEntityState();

		super.save(entity);

		entity = this.pm.byId(entity);

		IEaMessageService eMessageService = ServiceFactory.create(IEaMessageService.class);

		if (state != EntityState.Deleted) {
			
			List<String> ss = new ArrayList<String>();
			ss.add("【活动计划】"+entity.getCreator() + state.getText() + "了活动计划");
			ss.add(entity.getName());
			ss.add(DateManage.toLongString(new Date()));
			try {
				ss.add(URLDecoder.decode(entity.getContent(), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			String content = StringManager.join( ss );

			eMessageService.sendAll("SCRUM", content);
		}

		return entity;
	}
}
