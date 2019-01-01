package org.netsharp.persistence.oql.parser.set;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.core.Column;
import org.netsharp.persistence.oql.OqlParseException;
import org.netsharp.persistence.oql.parser.FieldNode;
import org.netsharp.persistence.oql.parser.OqlContex;

public abstract class OqlGenerator implements IOqlGenerator {
	protected final Log logger = LogFactory.getLog(this.getClass());
	//
	protected OqlContex oqlContex;
	protected FieldNode oqlNode = null;
	public static String FieldName = "{FieldName}";

	public void generate(OqlContex oqlContex, FieldNode oqlNode) {
		this.oqlContex = oqlContex;
		this.oqlNode = oqlNode;

		FieldNode idNode = this.firstOrDefault(oqlNode.Subs);

		if (idNode == null) {
			try {
				Column column = oqlNode.Mtable.getKeyColumn();
				oqlNode.addSub(column.getPropertyName());

			} catch (OqlParseException e) {
				
				throw e;
			}
		}
	}

	private FieldNode firstOrDefault(ArrayList<FieldNode> subs) {

		for (FieldNode node : subs) {
			if (node.Name == "Id") {
				return node;
			}
		}

		return null;
	}
}
