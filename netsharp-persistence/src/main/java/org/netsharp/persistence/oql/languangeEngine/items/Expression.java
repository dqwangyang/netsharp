package org.netsharp.persistence.oql.languangeEngine.items;

import org.netsharp.persistence.oql.OqlParseException;
import org.netsharp.persistence.oql.languangeEngine.EngineContext;
import org.netsharp.persistence.oql.operation.select.OperationType;
import org.netsharp.util.StringManager;

public class Expression extends LexerToken {
	private Expression left;
	private EngineContext context;
	public Operation Operation;
	public int OpenIndex;
	public int CloseIndex;
	public String Text;
	// [Exclusive, SerializeIgnore]
	public Expression Parent;

	public Expression getLeft() {
		return left;
	}

	public void setLeft(Expression left) {
		this.left = left;
		if (this.left != null) {
			this.left.Parent = this;
		}
	}

	private Expression right;

	public Expression getRight() {
		return right;
	}

	public void setRight(Expression right) {
		this.right = right;
		if (this.right != null) {
			this.right.Parent = this;
		}
	}

	// [Exclusive, SerializeIgnore]
	public Expression getTop() {
		if (Parent == null) {
			return this;
		} else {
			return Parent.getTop();
		}
	}

	// [Exclusive, SerializeIgnore]
	public Boolean hasChild() {
		return this.left != null && this.right != null;
	}

	// [Exclusive, SerializeIgnore]
	public EngineContext getContext() {
		return this.context;
	}

	public void setContext(EngineContext context) {
		this.context = context;
	}

	public Boolean isParse() {
		return true;
	}

	// out
	public Expression match(String source, int index){
		
		throw new OqlParseException("");
	}

	@Override
	public String toString() {
		if (hasChild()) {
			return "中间节点";
		}

		return Text;
	}

	public String generate() {
		
		if (Text != null) {
			return this.getResult();
		}

		String ss = null;

		// 第一步先处理树的左节点
		if (this.left != null) {
			
			ss = this.left.generate();

			if (Operation != null) {
				ss += Operation.getLeftSplit();
			}
		}

		// 第二步处理树的操作符
		if (Operation != null) {
			if (Operation.getOperationType() == OperationType.Left) {
				ss = Operation.getValue() + Operation.getRightSplit() + ss;
			} else if (Operation.getOperationType() == OperationType.Right) {
				ss += Operation.getLeftSplit() + Operation.getValue();
			} else {
				ss += Operation.getLeftSplit() + Operation.getValue()
						+ Operation.getLeftSplit();
			}
		}

		// 第三步处理树的右节点
		if (this.right != null) {
			ss += this.right.generate();
		}

		return ss;
	}

	private String result;

	public void parse() {
	}

	public String getResult() {

		if (StringManager.isNullOrEmpty(result)) {
			return Text;
		} else {
			return result;
		}
	}

	public void setResult(String result) {
		this.result = result;
	}
}
