package org.netsharp.panda.core;

import org.netsharp.util.StringManager;

public class PandaUrlHelper {

	private static String prefix = "/panda";

	public static String getUrl(String url) {

		if(StringManager.isNullOrEmpty(url)){
			
			return url;
		}
		
		if (url.startsWith(prefix)) {
			return url;
		}

		if (!url.startsWith("/")) {
			url = "/" + url;
		}

		return prefix + url;
	}
}
