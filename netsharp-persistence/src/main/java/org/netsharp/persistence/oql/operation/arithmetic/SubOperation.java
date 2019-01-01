package org.netsharp.persistence.oql.operation.arithmetic;

import org.netsharp.persistence.oql.languangeEngine.items.Operation;

public class SubOperation extends Operation {
	@Override
	public String getValue() {
		return "-";
	}

	@Override
	public Boolean isSpace() {
		return false;
	}

	@Override
	public int getPriority() {
		return 99;
	}
}
