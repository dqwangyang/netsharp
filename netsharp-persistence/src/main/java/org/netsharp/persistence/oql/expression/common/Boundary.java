package org.netsharp.persistence.oql.expression.common;

import java.util.ArrayList;
import java.util.Stack;

import org.netsharp.persistence.oql.OqlParseException;
import org.netsharp.persistence.oql.languangeEngine.items.Expression;
import org.netsharp.util.StringManager;

/// <summary>
/// 词法分析的边界
/// </summary>
public abstract class Boundary extends Expression {
	
	public abstract String openToken();
	public abstract String closeToken();
	public ArrayList<Boundary> Subs;
	public Boundary ParentBoundary;
	public String Source;

	@Override
	public Expression match(String source, int startIndex) {
		if (startIndex >= source.length()) {
			return null;
		}

		String start = source.substring(startIndex, startIndex + openToken().length());
		if (StringManager.equals(start, openToken())) {
			Boundary boundary = (Boundary) newInstance();
			boundary.OpenIndex = startIndex;
			boundary.Source = source;
			boundary.parse();

			return boundary;
		} else {
			return null;
		}
	}

	@Override
	public void parse() {
		// 已知一个开始的边界了
		// 寻找关闭边界
		int tempCloseIndex = Source.indexOf(closeToken(), OpenIndex + 1);

		// 寻找下一个新的开始的边界
		int nextOpenIndex = Source.indexOf(openToken(), OpenIndex + 1);

		if (tempCloseIndex < 0) {
			throw new OqlParseException("有未闭合的边界:" + closeToken()+StringManager.NewLine+ this.Source );
		}

		// 没有下一个开始的边界,或者关闭的边界在下一个新的开始边界之前, 说明该表达式不是嵌套的表达式,只是个简单的
		// 由一个开始边界和关闭边界组成的简单的表达式
		if ((nextOpenIndex < 0) || (tempCloseIndex < nextOpenIndex)) {
			// 无子级边界
			this.CloseIndex = tempCloseIndex;
			getText();
			return;
		}

		// 关闭的边界在下一个开始的边界之后，说明表达式是一个嵌套的表达式
		if (tempCloseIndex > nextOpenIndex) {
			int tempIndex = findCloseIndex();
			if (tempIndex == -1) {
				throw new OqlParseException("未能发现闭合节点" + closeToken()+StringManager.NewLine+ this.Source );
			} else {
				this.CloseIndex = tempIndex;
				getText();
			}
		}
	}

	// / <summary>
	// / 寻找对应的关闭边界的索引
	// / </summary>
	// / <returns></returns>
	private int findCloseIndex() {
		String s = this.Source;
		int start = this.OpenIndex + 1;

		Stack<String> stack = new Stack<String>();

		// 把开始边界压栈
		stack.push(this.openToken());

		while (start < s.length()) {
			int nextOpenIndex = s.indexOf(this.openToken(), start);
			int nextClostIndex = s.indexOf(this.closeToken(), start);
			if (nextClostIndex < 0) {
				throw new OqlParseException("未能发现闭合节点" + closeToken()+StringManager.NewLine+ this.Source );
			}

			// 任然存在下一个开始边界
			if ((nextOpenIndex < nextClostIndex) && (nextOpenIndex >= 0)) {
				stack.push(this.openToken());
				start = nextOpenIndex + this.openToken().length();
			} else {
				if (stack.size() > 1) {
					stack.pop();
					start = nextClostIndex + this.closeToken().length();
				} else {
					return nextClostIndex;
				}
			}
		}
		return -1;
	}

	public Boundary getLittleBrother(Boundary largeBrother) {
		int nextCloseIndex = Source.indexOf(closeToken(),largeBrother.CloseIndex + 1);
		int nextOpenIndex = Source.indexOf(openToken(),largeBrother.CloseIndex + 1);

		if (nextOpenIndex < 0) {
			// 无小弟
			return null;
		}

		if (nextOpenIndex < nextCloseIndex) {
			// 匹配到兄弟
			Boundary littleBrother = (Boundary) newInstance();
			littleBrother.ParentBoundary = largeBrother.ParentBoundary;

			littleBrother.OpenIndex = nextOpenIndex;
			littleBrother.ParentBoundary = largeBrother.ParentBoundary;
			littleBrother.Source = largeBrother.ParentBoundary.Source;
			littleBrother.parse();
			return littleBrother;
		} else {
			return null;
		}
	}

	protected void getText() {
		this.Text = Source.substring(OpenIndex + 1, CloseIndex);// CloseIndex -
																// OpenIndex -
																// 1);
		this.setResult(openToken() + " " + Text + " " + closeToken());
	}
}
