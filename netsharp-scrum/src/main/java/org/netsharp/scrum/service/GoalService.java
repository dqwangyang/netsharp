package org.netsharp.scrum.service;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.EntityState;
import org.netsharp.scrum.base.IGoalService;
import org.netsharp.scrum.entity.Goal;
import org.netsharp.service.BizService;
import org.netsharp.session.SessionManager;
import org.netsharp.util.StringManager;
import org.netsharp.wx.ea.base.IEaMessageService;

@Service
public class GoalService extends BizService<Goal> implements IGoalService {
	public GoalService() {
		super();
		this.type = Goal.class;
	}

	@Override
	public Goal save(Goal entity) {

		EntityState state = entity.getEntityState();

		super.save(entity);

		entity = this.pm.byId(entity);

		if (state != EntityState.Deleted) {

			IEaMessageService eMessageService = ServiceFactory.create(IEaMessageService.class);
			List<String> ss = new ArrayList<String>();
			String executor = SessionManager.getUserName();

			ss.add("【目标管理】"+executor + state.getText() + "了目标管理");
			ss.add(entity.getName());

			ss.add("提出人:" + entity.getCreator());
			ss.add(entity.getContent());

			String content = StringManager.join(StringManager.NewLine, ss);
			eMessageService.sendAll("SCRUM", content);
		}

		return entity;
	}
}
