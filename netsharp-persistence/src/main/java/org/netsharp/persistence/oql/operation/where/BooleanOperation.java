package org.netsharp.persistence.oql.operation.where;

import java.util.ArrayList;

import org.netsharp.persistence.oql.languangeEngine.items.Operation;
import org.netsharp.util.StringManager;

public class BooleanOperation extends Operation {
	private String value;
	private Boolean isSpace = false;

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public Boolean isSpace() {
		return isSpace;
	}

	public static ArrayList<BooleanOperation> create() {
		ArrayList<BooleanOperation> boperations = new ArrayList<BooleanOperation>();

		for (String key : BoolOperations) {
			BooleanOperation bo = new BooleanOperation();
			bo.value = key;

			if (bo.getValue().startsWith(" ")) {
				bo.isSpace = true;
				bo.value = StringManager.trim(bo.value, ' ');
			}

			boperations.add(bo);
		}

		return boperations;
	}

	public static String[] BoolOperations = new String[] { "<=", ">=", "=>", "=<", "!=", "<>", "!#", "!~", "!<", "!>", ",", "<", "=", ">", "#", "~", " LIKE ", " ILIKE ", " IS NOT ", " IS ", " IN ", " ANY ", " SOME ", " ALL ", " EXISTS ", " BETWEEN ", "+", "-", "*", "/",
			// "%",
			" NOT LIKE ", " NOT ILIKE ", " NOT IN ", " NOT BETWEEN ", " NOT EXSISTS ", " HAVING " };
}
