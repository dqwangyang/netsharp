package org.netsharp.panda.core;

import org.netsharp.application.Application;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.log.base.INLogService;
import org.netsharp.log.entity.NLog;
import org.netsharp.log.entity.NLogType;
import org.netsharp.panda.authorization.AuthorizationException;
import org.netsharp.panda.base.IPWorkspaceService;
import org.netsharp.panda.core.comunication.IRequest;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.panda.session.PermissionHelper;
import org.netsharp.util.ReflectManager;
import org.netsharp.util.StringManager;

public class HtmlPageFactory {

	public static HtmlPage create(String url) {

		IPWorkspaceService workspaceService = ServiceFactory.create(IPWorkspaceService.class);
		PWorkspace workspace = workspaceService.byUrl(url);

		if (workspace == null) {
			throw new PandaException("未能发现工作区：" + url);
		}

		boolean isPermission = PermissionHelper.isPermission(workspace.getResourceNode(), workspace.getOperationId(),
				workspace.getOperationType(), workspace.getOperationType2());

		if (!isPermission) {
			throw new AuthorizationException();
		}

		String redirectUrl = workspace.getRedirectUrl();

		if (!StringManager.isNullOrEmpty(redirectUrl)) {
			IRequest request = HttpContext.getCurrent().getRequest();
			request.sendRedirect(redirectUrl);
		}

		String controller = workspace.getServiceController();
		if (StringManager.isNullOrEmpty(controller)) {
			controller = Workspace.class.getName();
		}

		HtmlPage page = (HtmlPage) ReflectManager.newInstance(controller);
		{
			page.setPworkspace(workspace);
			
			if(StringManager.isNullOrEmpty(workspace.getName())){
				page.title = Application.getInstance().getName();
			}else {
				page.title = workspace.getName();
			}

		}

		log(workspace);

		return page;
	}

	private static void log(PWorkspace workspace) {

		INLogService logService = ServiceFactory.create(INLogService.class);
		NLog log = new NLog();
		{
			log.toNew();
			log.setOperaitonName("打开工作区");
			log.setCode(workspace.getCode());
			log.setName(workspace.getName());
			log.setMemoto(workspace.getResourceNode().getPathName());
			log.setMessage(HttpContext.getCurrent().getRequest().getRequestURI());
			log.setAssociateId(workspace.getId().toString());
			log.setLogType(NLogType.info);
		}
		logService.save(log);
	}
}
