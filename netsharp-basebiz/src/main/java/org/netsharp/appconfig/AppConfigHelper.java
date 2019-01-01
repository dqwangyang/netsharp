package org.netsharp.appconfig;

import java.util.Date;

import org.netsharp.util.DateManage;

public class AppConfigHelper {

	private static Long staticResourceVersion = null;

	public static Long getStaticResourceVersion() {

		if (staticResourceVersion == null) {

			staticResourceVersion = DateManage.toLong(new Date());
		}

		return staticResourceVersion;
	}
}
