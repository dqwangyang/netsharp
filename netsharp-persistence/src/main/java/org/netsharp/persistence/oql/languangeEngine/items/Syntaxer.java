package org.netsharp.persistence.oql.languangeEngine.items;

import org.netsharp.persistence.oql.OqlParseException;
import org.netsharp.persistence.oql.operation.LanguangeStep;
import org.netsharp.util.StringManager;

/// <summary>
/// 语法分析器
/// </summary>
public class Syntaxer extends LanguangeStep {
	protected String LexerCode;

	protected Expression currentExpression = new Expression();

	@Override
	public void execute() {
		Tokenizer tokenizer = new Tokenizer(getEngine().getContext());

		for (LexerToken token : tokenizer) {
			
			// #region 表达式的处理
			if (token instanceof Expression) {      
				Expression expression = (Expression) token;
				
//				System.out.println("expression token : "+expression.Text + " : " + expression.getClass().getName());

				if (currentExpression.getLeft() == null) {
					currentExpression.setLeft(expression);
				} else {
					if (currentExpression.getRight() != null) {
						StringBuilder builder = new StringBuilder();
						builder.append("OQL解析失败(" + this.getClass().getName() + "):").append(StringManager.NewLine)
						.append("OQL:").append(StringManager.NewLine)
						.append(this.getOql().toString()).append(StringManager.NewLine)
						.append("已解析：").append(StringManager.NewLine)
						.append(currentExpression.getTop().toString()).append(StringManager.NewLine)
						.append("当前节点：").append(StringManager.NewLine)
						.append(expression.toString()).append(StringManager.NewLine);

						throw new OqlParseException(builder.toString());
					}
					currentExpression.setRight(expression);
				}
				continue;
			}

			//操作符的处理

			if (token instanceof Operation) {
				Operation operation = (Operation) token;
				
//				System.out.println("operation token : "+operation.getValue());
				
				if (currentExpression.Operation != null) {
					Expression extendExpression = new Expression();
					if (operation.getPriority() <= currentExpression.Operation.getPriority()) {
						// 场景：新的优先级相同或者更低
						// 规则：
						// 1，扩展节点放在当前节点的左侧
						// 2，扩展节点其主、子、操作符从当前节点复制
						// 3，当前节点操作符为弹出的操作符
						// 4,Current不变
						extendExpression.setLeft(currentExpression.getLeft());
						extendExpression.setRight(currentExpression.getRight());
						extendExpression.Operation = currentExpression.Operation;

						currentExpression.setLeft(extendExpression);
						currentExpression.Operation = operation;
						currentExpression.setRight(null);
					} else {
						// 场景：新的操作优先级更高
						// 规则：
						// 1，扩展节点的左=当前节点的右
						// 2，扩展节点作为当前节点的右
						// 3，当前节点的操作符不变
						// 4，扩展节点操作符为弹出操作符
						extendExpression.setLeft(currentExpression.getRight());
						currentExpression.setRight(extendExpression);
						extendExpression.Operation = operation;

						currentExpression = extendExpression;
					}
				}

				// 把解析到的操作符赋值给当前表达式
				currentExpression.Operation = operation;
				continue;
			}
		}

		getEngine().getContext().ExpressionTree = currentExpression.getTop();

		// new Serializer().Serialize(Engine.Context.ExpressionTree, @"c:\ExpressionTree.xml", false);
	}
}
