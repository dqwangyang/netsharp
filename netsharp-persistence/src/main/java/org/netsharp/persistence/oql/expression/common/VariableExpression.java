package org.netsharp.persistence.oql.expression.common;

import java.lang.reflect.Field;

import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.annotations.Exclusive;
import org.netsharp.persistence.oql.languangeEngine.items.Expression;
import org.netsharp.persistence.oql.parser.FieldNode;
import org.netsharp.util.StringManager;

public class VariableExpression extends AbstractVariableExpression {
	
	@Override
	public Expression match(String source, int index) {
		int opIndex = nextOperationIndex(source, index);
		String text = source.substring(index, opIndex);

		if (StringManager.isNullOrEmpty(text)) {
			return null;
		}

		Expression exp = (Expression) this.newInstance();
		exp.Text = text.trim();
		exp.OpenIndex = index;
		exp.CloseIndex = opIndex - 1;

		return exp;
	}

	@Override
	public void parse() {
		// 普通的属性
		String[] ss = Text.split("\\.");
		boolean isExclusive = true;
		if (ss.length == 1) {
			// 无引用前缀
			Mtable mtable = MtableManager.getMtable(this.getOql().getEntityId());
			isExclusive = this.isExclusive(mtable.getType(), Text);
			if (!isExclusive) {
				this.setResult(mtable.getCode() + "." + Text);
			}

		} else {
			// 有引用前缀
			String[] paths = copyArrange(ss, 0, ss.length - 1);
			FieldNode relation = getRelationNode(paths);
			isExclusive = this.isExclusive(relation.Mtable.getType(), ss[ss.length - 1]);
			if (!isExclusive) {
				String res = joinRelationAndProperty(relation, ss[ss.length - 1]);
				this.setResult(res);
			}
		}
		if (!isExclusive) {
			getRelationNode(ss);
		}

	}

	/**
	 * 判断是否是非持久化字段
	 */
	private boolean isExclusive(Class<?> type, String property) {

		Field fs[] = type.getDeclaredFields();
		boolean hasProperty = false;
		for (Field f : fs) {
			if (f.getName().toLowerCase().equals(property.toLowerCase())) {
				hasProperty = true;
				return f.getAnnotation(Exclusive.class) != null;
			}
		}

		if (!hasProperty && !type.getSuperclass().equals(Object.class)) {
			return isExclusive(type.getSuperclass(), property);
		}
		return false;
	}

	@Override
	public Boolean isParse() {
		if (Parent == null) {
			return false;
		} else if (Parent.Operation == null) {
			return false;
		} else if (StringManager.equals(Parent.Operation.getValue(), "AS", true)) {
			return false;
		} else {
			return true;
		}
	}
}
