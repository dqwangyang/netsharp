package org.netsharp.persistence.oql.expression.common;

import org.netsharp.persistence.oql.languangeEngine.items.Expression;
import org.netsharp.util.StringManager;

public class MethodExpression extends AbstractVariableExpression {

	private static char START_QUTO = '(';
	private static char END_QUTO = ')';

	@Override
	public Boolean isParse() {
		return false;
	}

	@Override
	public Expression match(String source, int index) {
		
		if (StringManager.equals(source.substring(index, index + 1), "(")) {
			return null;
		}

		int opIndex = nextOperationIndex(source, index);

		int startQuto = source.indexOf("(", index);
		if (startQuto < 0 || startQuto > opIndex) {
			return null;
		}

		int startQutoCount = 0;
		int endQutoCount = 0;
		boolean isMatch = false;

		for (int i = index + 1; i < source.length(); i++) {

			char c = source.charAt(i);
			if (c == START_QUTO) {
				startQutoCount++;
			} else if (c == END_QUTO) {
				endQutoCount++;
			} else {
				// do nothing
			}

			if (startQutoCount > 0 && startQutoCount == endQutoCount) {
				opIndex = i;
				isMatch = true;
				break;
			}
		}
		if (!isMatch) {
			return null;
		}

		//
		String text = source.substring(index, opIndex + 1);

		Expression exp = (Expression) this.newInstance();
		exp.Text = text.trim();

		exp.OpenIndex = index;
		exp.CloseIndex = opIndex;

		return exp;
	}
}
