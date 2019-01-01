package org.netsharp.scrum.web;

import org.netsharp.core.Oql;
import org.netsharp.core.Pagination;
import org.netsharp.panda.commerce.ListPart;
import org.netsharp.session.SessionManager;
import org.netsharp.util.StringManager;

public class MyStoryListPart extends ListPart {

	@Override
	public Pagination doQuery(Oql oql) {
		String filter = oql.getFilter();
		String defaultFilter = "1=2";
		
		Integer userId = SessionManager.getUserId();
		if (StringManager.isNullOrEmpty(filter)) {
			defaultFilter = "iteration.isCurrent=1 or (Project.ownerId=" + userId+" and Project.idCreator="+userId+")";
		} else {
			defaultFilter = " and iteration.isCurrent=1 or (Project.ownerId=" + userId+" and Project.idCreator="+userId+")";
		}

		oql.setFilter(filter + defaultFilter);
		return super.doQuery(oql);
	}
}
