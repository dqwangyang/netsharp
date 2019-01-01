package org.netsharp.scrum.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.EntityState;
import org.netsharp.organization.base.IOrganizationService;
import org.netsharp.organization.entity.Organization;
import org.netsharp.scrum.base.IIterationService;
import org.netsharp.scrum.base.IStoryService;
import org.netsharp.scrum.entity.Iteration;
import org.netsharp.scrum.entity.Story;
import org.netsharp.scrum.entity.StoryParticipant;
import org.netsharp.service.BizService;
import org.netsharp.session.SessionManager;
import org.netsharp.util.DateManage;
import org.netsharp.util.StringManager;
import org.netsharp.wx.ea.base.IEaMessageService;

@Service
public class StoryService extends BizService<Story> implements IStoryService {

	public StoryService() {
		super();
		this.type = Story.class;
	}

	@Override
	public Story save(Story entity) {

		EntityState state = entity.getEntityState();
		
		if(entity.getEntityState() != EntityState.Deleted){
			
			//保存任务部门
			if(entity.getOwnerId()!=null) {
				IOrganizationService service = ServiceFactory.create(IOrganizationService.class);
				Organization department = service.getMainDepartment(entity.getOwnerId());
				entity.setOrganization(department);
			}
		

			//显示参与人
			if(entity.getParticipants()!= null && entity.getParticipants().size() >0){
				
				List<String> ss = new ArrayList<String>();
				for(StoryParticipant sp :entity.getParticipants()){
					
					ss.add(sp.getParticipant().getName());
				}
				
				String participants = StringManager.join(",",ss);
				entity.setParticipantStr(participants);
			}
		}
		

		super.save(entity);

		entity = this.pm.byId(entity);

		if(state != EntityState.Deleted){

			List<String> ss = new ArrayList<String>();

			String executor = SessionManager.getUserName();

			ss.add("【任务】"+executor + state.getText() + "了用户任务");
			ss.add(entity.getName());
			ss.add(entity.getStatus().getText());
			ss.add(DateManage.toLongString(new Date()));
			ss.add("迭代:" + entity.getIteration().getName());
			ss.add("负责人:" + entity.getOwner().getName());
			ss.add("客户:" + entity.getBizUser().getName());
			ss.add("提出人:" + entity.getCreator());
			ss.add("紧急程度:" + entity.getUrgency().getText());
			ss.add("估时:" + entity.getEstimateHours()+"小时");
			ss.add(entity.getContent());

			{
				String content = StringManager.join(StringManager.NewLine, ss);
				List<String> ls = new ArrayList<String>();
				ls.add(entity.getOwner().getMobile());
				ls.add(entity.getBizUser().getMobile());
				ls.add(entity.getBizUser().getMobile());	
				ls.add(SessionManager.getMobile());
				for (StoryParticipant pp : entity.getParticipants()) {
					ls.add(pp.getParticipantId().toString());
				}			
				IEaMessageService eMessageService = ServiceFactory.create(IEaMessageService.class);
				eMessageService.send("SCRUM", content, StringManager.join("|", ls));
			}
		}
		return entity;
	}
	
	public Story newInstance() {
		
		Story entity = super.newInstance();
		IIterationService terationService = ServiceFactory.create(IIterationService.class);
		Iteration iteration = terationService.getCurrentIteration();
		if(iteration != null){
			
			entity.setIteration(iteration);
			entity.setIterationId(iteration.getId());
		}
		return entity;
	}
}
