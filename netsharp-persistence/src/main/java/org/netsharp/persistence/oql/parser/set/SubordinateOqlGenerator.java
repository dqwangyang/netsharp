package org.netsharp.persistence.oql.parser.set;

import java.util.ArrayList;

import org.netsharp.core.TableRelation;
import org.netsharp.dataccess.OrmType;
import org.netsharp.persistence.oql.parser.FieldNode;
import org.netsharp.persistence.oql.parser.OqlContex;
import org.netsharp.util.StringManager;

public class SubordinateOqlGenerator extends OqlGenerator {
	// private ArrayList<OqlStruct> oqlStructs = new ArrayList<OqlStruct>();
	private TableRelation ri;

	@Override
	public void generate(OqlContex oqlContex, FieldNode fieldNode) {
		//
		// Initialize
		//
		super.generate(oqlContex, fieldNode);
		ri = fieldNode.RelationMeta;

		if (ri == null) {
			return;
		}

		// SingleQuery
		OqlStruct parentOqlStruct = fieldNode.Parent.OqlStruct;
		OqlStruct os = new OqlStruct();
		os.Mtable = fieldNode.Mtable;
		os.Level = parentOqlStruct.Level + 1;
		os.Parent = parentOqlStruct;

		// IN外TableName
		@SuppressWarnings("unused")
		String tableName = null;

		if (fieldNode.OrmType == OrmType.Reference || fieldNode.OrmType == OrmType.CompositeOne) {
			
			os.OutInId = ri.getPriamyKey();//xfb 2017
			os.InInIdField = ri.getForeignkey();
			tableName = fieldNode.Mtable.getTableName();
			
		} else if (fieldNode.OrmType == OrmType.Subs) {
			
			os.OutInId = ri.getForeignkey();
			os.InInIdField = ri.getPriamyKey();//xfb 2017

			// 使用此判断方式可以避免实体继承的问题
			if (fieldNode.Mtable.getEntityId() == ri.getToEntityId()) {
				// 正向组合查询：主实体为AddIn查Codon
				tableName = ri.getToTable().getTableName();
			} else {
				// 逆向组合查询：主实体为Codon查AddIn
				tableName = ri.getFromTable().getTableName();
			}
			
		}

		// OqlStruct
		os.Selects = getSelect();
		os.Orderby = getOrderby();

		fieldNode.OqlStruct = os;

		//
		OqlStructs oss = oqlContex.OqlStructs.get(os.Mtable.getEntityId());
		if (oss != null) {
			oss.add(os);
		} else {
			oss = new OqlStructs();
			oss.add(os);

			oqlContex.OqlStructs.put(os.Mtable.getEntityId(), oss);
		}

		// Iterateor
		for (FieldNode subNode : fieldNode.Subs) {
			if (subNode.OrmType != OrmType.Property) {
				IOqlGenerator subordinateGenrator = new SubordinateOqlGenerator();
				subordinateGenrator.generate(oqlContex, subNode);
			}
		}
	}

	protected String getSelect() {
		
		ArrayList<FieldNode> properties = this.oqlNode.getSubProperties();

		ArrayList<String> ss = new ArrayList<String>();
		for (FieldNode p : properties) {
			ss.add(p.Name);
		}
		String select = StringManager.join("," + StringManager.NewLine, ss);

		return select;
	}

	protected String getOrderby() {
		
		String orderby = oqlNode.Mtable.getOrderby();
		
		return orderby;
	}
}
