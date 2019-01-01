package org.netsharp.persistence.oql.languangeEngine.items;

import org.netsharp.persistence.oql.operation.select.OperationType;

public abstract class Operation extends LexerToken {
	/// 操作名称
	public abstract String getValue();

	/// 操作符是否需要空格
	public abstract Boolean isSpace();

	/// 操作符
	public OperationType getOperationType() {
		return OperationType.Two;
	}

	public String getLeftSplit() {
		return " ";
	}

	public String getRightSplit() {
		return " ";
	}

	public Object execute() {
		return null;
	}

	public int getPriority() {
		return 10;
	}

	@Override
	public String toString() {
		return this.getValue();
	}
}
