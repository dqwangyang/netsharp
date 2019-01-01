package org.netsharp.scrum.service;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.EntityState;
import org.netsharp.core.MtableManager;
import org.netsharp.core.Oql;
import org.netsharp.scrum.base.IIterationService;
import org.netsharp.scrum.entity.Iteration;
import org.netsharp.service.BizService;
import org.netsharp.session.SessionManager;
import org.netsharp.util.DateManage;
import org.netsharp.util.StringManager;
import org.netsharp.util.sqlbuilder.UpdateBuilder;
import org.netsharp.wx.ea.base.IEaMessageService;

@Service
public class IterationService extends BizService<Iteration> implements
		IIterationService {

	public IterationService() {
		super();
		this.type = Iteration.class;
	}

	@Override
	public Iteration save(Iteration entity) {

		EntityState state = entity.getEntityState();

		super.save(entity);

		entity = this.pm.byId(entity);
		if(entity.getIsCurrent() == true){
			
			//更新其它迭代为：非当前
			UpdateBuilder updator = UpdateBuilder.getInstance();{
				
				updator.update(MtableManager.getMtable(this.type).getTableName());
				updator.set("is_current", false);
				updator.where(" is_current=1 ","id <>" + entity.getId());
			}
			String cmdText = updator.toSQL();
			pm.executeNonQuery(cmdText, null);
		}

		if (state != EntityState.Deleted) {
			IEaMessageService eMessageService = ServiceFactory
					.create(IEaMessageService.class);

			List<String> ss = new ArrayList<String>();

			String executor = SessionManager.getUserName();
			ss.add("【迭代】" + executor + state.getText() + "了迭代");
			ss.add(entity.getName());
			ss.add(DateManage.toLongString(new Date()));
			ss.add("起始日期:" + DateManage.toLongString(entity.getStartTime()));
			ss.add("结束日期:" + DateManage.toLongString(entity.getEndTime()));
			ss.add("当前迭代:" + entity.getIsCurrent());
			ss.add("迭代目标:" + entity.getContent());
			ss.add(entity.getContent());
			{
				String content = StringManager.join(StringManager.NewLine, ss);

				eMessageService.sendAll("SCRUM", content);
			}
		}

		return entity;
	}

	@Override
	public Iteration getCurrentIteration() {
		
		Oql oql = new Oql();{
			oql.setType(this.type);
			oql.setSelects("*");
			oql.setFilter("isCurrent=?");
			oql.getParameters().add("isCurrent", true, Types.BOOLEAN);
		}
		return this.queryFirst(oql);
	}
}
