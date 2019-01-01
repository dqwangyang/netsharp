package org.netsharp.persistence.oql.expression;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

import org.netsharp.persistence.oql.boundary.SQuotaBoundary;
import org.netsharp.persistence.oql.expression.common.ConstDecimalExpression;
import org.netsharp.persistence.oql.expression.common.ConstStringExpression;
import org.netsharp.persistence.oql.expression.common.MethodExpression;
import org.netsharp.persistence.oql.expression.common.SubSelectionExpression;
import org.netsharp.persistence.oql.expression.orderby.OrderbyFieldExpression;
import org.netsharp.persistence.oql.expression.select.ASExpresstion;
import org.netsharp.persistence.oql.expression.select.FullFieldsExpresstion;
import org.netsharp.persistence.oql.expression.select.MultiVariable2Expression;
import org.netsharp.persistence.oql.expression.select.MultiVariableExpression;
import org.netsharp.persistence.oql.expression.select.SelectFieldExpression;
import org.netsharp.persistence.oql.expression.where.NullExpression;
import org.netsharp.persistence.oql.expression.where.ParameterExpression;
import org.netsharp.persistence.oql.expression.where.WhereFieldExpression;
import org.netsharp.persistence.oql.languangeEngine.items.Expression;

public class ExpressionManager {

	private static ReentrantLock lock = new ReentrantLock();
	private static ArrayList<Expression> selectExps;
	private static ArrayList<Expression> whereExps;
	private static ArrayList<Expression> orderByExps;

	public static ArrayList<Expression> getSelectExps() {
		
		try {
			lock.lock();

			selectExps = new ArrayList<Expression>();

			selectExps.add(new SubSelectionExpression());
			// selectExps.add(new MethodExpression());
			selectExps.add(new MultiVariable2Expression());
			selectExps.add(new MultiVariableExpression());
			selectExps.add(new ASExpresstion());
			selectExps.add(new ConstDecimalExpression());
			selectExps.add(new ConstStringExpression());
			selectExps.add(new SQuotaBoundary());
			selectExps.add(new FullFieldsExpresstion());
			selectExps.add(new SelectFieldExpression());
			// selectExps.add(new VariableExpression());
		} finally {
			lock.unlock();
		}
		return selectExps;
	}

	public static ArrayList<Expression> getWhereExps() {
		if (whereExps == null) {
			whereExps = new ArrayList<Expression>();

			whereExps.add(new NullExpression());
			whereExps.add(new SubSelectionExpression());
			whereExps.add(new ConstDecimalExpression());
			whereExps.add(new ConstStringExpression());
			whereExps.add(new MethodExpression());
			whereExps.add(new SQuotaBoundary());
			whereExps.add(new ParameterExpression());
			whereExps.add(new WhereFieldExpression());
			// whereExps.add(new VariableExpression());
		}

		return whereExps;
	}

	// / <summary>
	// / 排序表达式字典
	// / </summary>
	public static ArrayList<Expression> getOrderbyExps() {
		if (orderByExps == null) {
			orderByExps = new ArrayList<Expression>();
			orderByExps.add(new OrderbyFieldExpression());
			// orderByExps.add(new VariableExpression());
		}
		return orderByExps;
	}
}
