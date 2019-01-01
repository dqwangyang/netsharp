package org.netsharp.persistence.oql.parser;

import org.netsharp.util.StringManager;

public class SbManager {
	public static void appendLine(StringBuilder builder, String str) {
		builder.append(str).append(StringManager.NewLine);
	}

	public static void appendReqired(StringBuilder builder, String join, String str) {
		if (!StringManager.isNullOrEmpty(str)) {
			builder.append(join).append(str);
		}
	}

	public static void appendLineReqired(StringBuilder builder, String join, String str) {
		if (join != null && !StringManager.isNullOrEmpty(str)) {
			builder.append(join).append(str);
		}

		builder.append(StringManager.NewLine);
	}
}
