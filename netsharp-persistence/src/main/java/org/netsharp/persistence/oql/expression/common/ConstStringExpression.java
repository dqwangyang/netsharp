package org.netsharp.persistence.oql.expression.common;

import org.netsharp.persistence.oql.OqlParseException;
import org.netsharp.persistence.oql.languangeEngine.items.Expression;

public class ConstStringExpression extends Expression {
	@Override
	public Boolean isParse() {
		return false;
	}

	@Override
	public Expression match(String source, int index) {
		if (index >= source.length()) {
			return null;
		}

		while (true) {
			char c = source.charAt(index);
			if (c == ' ') {
				index++;
				continue;
			} else if (c != '\'') {
				return null;
			}

			break;
		}

		int closeIndex = source.indexOf("\'", index + 1);

		if (closeIndex <= index) {
			throw new OqlParseException("检测到未闭合的字符！");
		} else {
			Expression exp = (Expression) this.newInstance();
			exp.OpenIndex = index;
			exp.CloseIndex = closeIndex;

			int endIndex = exp.CloseIndex + 1;
			exp.Text = source.substring(index, endIndex);

			return exp;
		}
	}
}
