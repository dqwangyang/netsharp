package org.netsharp.persistence.oql.parser.set;

import java.util.ArrayList;

import org.netsharp.persistence.oql.parser.FieldNode;
import org.netsharp.persistence.oql.parser.OqlContex;
import org.netsharp.util.StringManager;

public class MainOqlGenerator extends OqlGenerator {
	private boolean canGroupby;

	@Override
	public void generate(OqlContex oqlContex, FieldNode fileNode) {
		//
		// Initialize
		//
		super.generate(oqlContex, fileNode);

		fileNode = oqlContex.Select.FieldNode;

		//
		// OqlStruct
		//
		fileNode.OqlStruct = new OqlStruct();

		fileNode.OqlStruct.Mtable = fileNode.Mtable;
		fileNode.OqlStruct.IsMain = true;
		fileNode.OqlStruct.Level = 0;

		OqlStruct os = fileNode.OqlStruct;

		OqlStructs oss = new OqlStructs();
		oss.add(os);

		oqlContex.OqlStructs.put(os.Mtable.getEntityId(), oss);

		//
		// Build SQL
		//
		os.Joins = getJoins();
		os.OrderbyJoins = getOrderbyJoins();
		os.Selects = getSelect();
		os.Wheres = oqlContex.Where.Result;

		boolean isOrderby = StringManager.isNullOrEmpty(oqlContex.Orderby.Result);
		boolean isTableOrderby = !StringManager.isNullOrEmpty(fileNode.Mtable.getOrderby());
		if (isOrderby && isTableOrderby) {
			String[] ss = fileNode.Mtable.getOrderby().split("\\.");//为什么不是使用逗号分隔多个orderby字段

			for (int i = 0; i < ss.length; i++) {
				ss[i] = fileNode.Mtable.getCode() + "." + ss[i];
			}

			os.Orderby = StringManager.join(",", ss);
		} else {
			os.Orderby = oqlContex.Orderby.Result;
		}
	}

	protected String getSelect() {
		ArrayList<FieldNode> properties = this.oqlNode.getSubProperties();

		ArrayList<String> ss = new ArrayList<String>();
		for (FieldNode p : properties) {
			ss.add(oqlNode.Mtable.getCode() + "." + p.Name);
		}
		String select = StringManager.join(",", ss);

		return select;
	}

	private String getJoins() {
		if (oqlContex.Where == null) {
			return null;
		}
		ArrayList<String> joins = new ArrayList<String>();
		oqlContex.Where.FieldNode.join(joins);
		oqlContex.Orderby.FieldNode.join(joins);

		String join = StringManager.join(StringManager.NewLine, joins);

		return join;
	}

	private String getOrderbyJoins() {
		if (oqlContex.Where == null) {
			return null;
		}
		ArrayList<String> joins = new ArrayList<String>();
		oqlContex.Orderby.FieldNode.join(joins);

		String join = StringManager.join(StringManager.NewLine, joins);

		return join;
	}

	protected String getOrderby() {
		if (!StringManager.isNullOrEmpty(oqlNode.Mtable.getOrderby())) {
			String[] orderbyes = oqlNode.Mtable.getOrderby().split(",");
			String[] tt = new String[orderbyes.length];

			for (int i = 0; i < orderbyes.length; i++) {
				tt[i] = oqlNode.Mtable.getCode() + "." + orderbyes[i];
			}

			return StringManager.join(",", tt);
		} else {
			return null;
		}
	}

	public boolean isCanGroupby() {
		return canGroupby;
	}

	public void setCanGroupby(boolean canGroupby) {
		this.canGroupby = canGroupby;
	}
}
