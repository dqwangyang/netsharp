package org.netsharp.panda.servlet.invoke;

import org.netsharp.panda.core.HttpContext;
import org.netsharp.panda.core.comunication.IRequest;
import org.netsharp.panda.servlet.invoke.comboxtree.PandaComboxInvoke;
import org.netsharp.panda.servlet.invoke.enumbox.PandaEnumInvoke;
import org.netsharp.panda.servlet.invoke.html.PandaHtmlInvoke;
import org.netsharp.panda.servlet.invoke.nav.PandaNavInvoke;
import org.netsharp.panda.servlet.invoke.reference.PandaReferneceInvokde;
import org.netsharp.panda.servlet.invoke.rest.easyui.PandaEasyuiRestInvoke;
import org.netsharp.panda.servlet.invoke.rest.rest.PandaRestInvoke;
import org.netsharp.util.StringManager;

public class PandaInvokeFactory {
	
	public static PandaInvoke crate() {
		
		IRequest request = HttpContext.getCurrent().getRequest();
		
		String uri = request.getRequestURI();
		if(uri.startsWith("/nav/")) {
			return new PandaNavInvoke();
		}
		
		if (uri.equals("/panda/rest/service")) {
			
			String vid = request.getParameter("vid");
			
			if (StringManager.isNullOrEmpty(vid)) {
				return new PandaRestInvoke();
			} else {
				return new PandaEasyuiRestInvoke();
			}
		}

		if (uri.equals("/panda/rest/reference")) {

			return new PandaReferneceInvokde();
		}

		if (uri.equals("/panda/rest/comboxtree")) {

			return new PandaComboxInvoke();
		}
		
		if (uri.equals("/panda/rest/enum")) {

			return new PandaEnumInvoke();
		}

		return new PandaHtmlInvoke();
	}

}
