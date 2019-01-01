package org.netsharp.persistence.oql.languangeEngine.items;

import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.persistence.oql.OqlParseException;
import org.netsharp.persistence.oql.operation.select.OperationType;
import org.netsharp.util.StringManager;

public class TokenizerEnumator implements Iterator<LexerToken> {
	
	protected final Log logger = LogFactory.getLog(this.getClass());
	private Tokenizer tokenizer;
	private int currentIndex = 0; //按照字符的遍历要解析的内容时，用来标记当前要分析的字符的位置

	public TokenizerEnumator(Tokenizer t) {
		tokenizer = t;
	}

	public LexerToken next(){

		boolean isSpace = false;// 当前词之前是否有空格
		while (currentIndex < tokenizer.Input.length()) {
			char c = tokenizer.Input.charAt(currentIndex);
			if (c == ' ') {
				isSpace = true;
				currentIndex++;
			} else {
				break;
			}
		}

		//越界判断
		if (currentIndex >= tokenizer.Input.length()) {
			return null;
		}

		//带空格的匹配操作符
		for (Operation operation : tokenizer.Context.OperationTokens.values()) {
			// 长度判断
			int operationedIndex = currentIndex + operation.getValue().length();
			if (tokenizer.Input.length() < operationedIndex) {
				continue;
			}

			boolean isMatched = false;

			// 双目操作符
			if (operation.getOperationType() != OperationType.Two) {
				isMatched = singleOperation(operation);
			}
			// 两边为空格的操作符
			else if (isSpace && operation.isSpace()) {
				isMatched = spacableOperation(operation);
			}
			// 非两边为空格操作符
			else if (!isSpace && !operation.isSpace()) {
				isMatched = unSpacableOperation(operation);
			}

			if (isMatched) {
				return operation;
			}
		}

		//匹配表达式
		for (Expression exp : tokenizer.Context.ExpressionTokens) {
			if (exp == null) {
				continue;
			}
			exp.setContext(tokenizer.Context);
			try {
				Expression matchexp = exp.match(tokenizer.Input, currentIndex);
				if (matchexp != null) {
					matchexp.setContext(tokenizer.Context);
					currentIndex = matchexp.CloseIndex + 1;
					return matchexp;
				}
			} catch (OqlParseException ex) {
				logger.error(ex);
			}
		}
		return null;
	}

	public Boolean singleOperation(Operation operation) {
		if (operation.getOperationType() == OperationType.Right) {
			int endindex = currentIndex + operation.getValue().length();
			String mayOperation = tokenizer.Input.substring(currentIndex, endindex);
			if (StringManager.equals(mayOperation, operation.getValue(), operation.isIgnoreCase())) {
				this.currentIndex += operation.getValue().length();

				return true;
			}
		} else {
			return spacableOperation(operation);
		}

		return false;
	}

	public Boolean spacableOperation(Operation operation) {
		int operationedIndex = currentIndex + operation.getValue().length();

		if (tokenizer.Input.length() <= operationedIndex + 1) {
			return false;
		}

		int endIndex = currentIndex + operation.getValue().length() + 1;
		String mayOperation = tokenizer.Input.substring(currentIndex, endIndex);
		if (!mayOperation.endsWith(" ")) {
			return false;
		}

		mayOperation = StringManager.trimEnd(mayOperation, ' ');

		if (StringManager.equals(mayOperation, operation.getValue(), operation.isIgnoreCase())) {
			this.currentIndex = operationedIndex + 1;

			return true;
		}

		return false;
	}

	public Boolean unSpacableOperation(Operation operation) {
		int endIndex = currentIndex + operation.getValue().length();
		String mayOperation = tokenizer.Input.substring(currentIndex, endIndex);

		if (operation.isIgnoreCase()) {
			if (mayOperation.equals(operation.getValue())) {
				this.currentIndex += operation.getValue().length();

				return true;
			}
		} else {
			if (mayOperation.equalsIgnoreCase(operation.getValue())) {
				this.currentIndex += operation.getValue().length();

				return true;
			}
		}
		return false;
	}

	public boolean hasNext() {
		return currentIndex < tokenizer.Input.length();
	}

	public void reset() {
		currentIndex = 0;
	}

	public void Dispose() {
		// do nothing
	}

	@Override
	public void remove() {

		// ?
	}
}
