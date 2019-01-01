package org.netsharp.persistence.oql.parser;

import java.util.ArrayList;

import org.netsharp.core.Column;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.Oql;
import org.netsharp.core.TableCompositeOne;
import org.netsharp.core.TableReference;
import org.netsharp.core.TableRelation;
import org.netsharp.core.TableSubs;
import org.netsharp.persistence.oql.OqlParseException;
import org.netsharp.util.StringManager;

public class FieldNode {
	public String Name;
	public int Level;
	public org.netsharp.dataccess.OrmType OrmType;
	public Column PropertyMeta;
	public TableRelation RelationMeta;
	public Mtable Mtable;
	public ArrayList<FieldNode> Subs = new ArrayList<FieldNode>();
	public FieldNode Parent;
	public org.netsharp.persistence.oql.parser.set.OqlStruct OqlStruct;

	public void add(String[] paths) {
		if ((this.Level + 1) >= paths.length) {
			return;
		}
		String subName = paths[Level + 1];

		FieldNode sub = null;
		for (FieldNode node : Subs) {

			if (StringManager.equals(subName, node.Name, true)) {

				sub = node;
				break;
			}
		}

		if (sub == null) {
			sub = addSub(subName);
		}

		if (Level < paths.length) {
			sub.add(paths);
		}
	}

	public FieldNode addSub(String subName) {
		//
		// compositeOne
		//
		TableCompositeOne compositeOne = Mtable.getCompositeOne(subName);

		if (compositeOne != null) {
			FieldNode subNode = new FieldNode();
			subNode.Parent = this;
			subNode.Level = this.Level + 1;
			subNode.RelationMeta = compositeOne;
			subNode.OrmType = org.netsharp.dataccess.OrmType.CompositeOne;
			subNode.Name = compositeOne.getReferenceCode();

			if (subNode.Mtable == null) {
				MtableManager.getMtable(compositeOne.getToEntityId());
				subNode.Mtable = (Mtable) compositeOne.getToTable();
			}

			Subs.add(subNode);

			// 添加外键引用
			FieldNode fkNode = null;

			for (FieldNode node : Subs) {
				if (node.Name == compositeOne.getForeignProperty()) {
					fkNode = node;
					break;
				}
			}
			if (fkNode == null) {
				fkNode = this.addSub(compositeOne.getForeignProperty());
				fkNode.PropertyMeta = null;// ?,功能授权弹出此界面是getmainselect得到的RelationInfo为空
			}

			return subNode;
		}

		//
		// 引用
		//
		TableReference reference = Mtable.getReference(subName);

		if (reference != null) {
			FieldNode subNode = new FieldNode();
			subNode.Parent = this;
			subNode.Level = this.Level + 1;
			subNode.RelationMeta = reference;
			subNode.OrmType = org.netsharp.dataccess.OrmType.Reference;
			subNode.Name = reference.getReferenceCode();

			if (subNode.Mtable == null) {
				MtableManager.getMtable(reference.getToEntityId());
				subNode.Mtable = (Mtable) reference.getToTable();
			}

			Subs.add(subNode);

			// 添加外键引用
			FieldNode fkNode = null;

			for (FieldNode node : Subs) {
				if (node.Name == reference.getForeignProperty()) {
					fkNode = node;
					break;
				}
			}
			if (fkNode == null) {
				fkNode = this.addSub(reference.getForeignProperty());
				fkNode.PropertyMeta = null;// ?,功能授权弹出此界面是getmainselect得到的RelationInfo为空
			}

			return subNode;
		}

		//
		// 组合
		//
		TableSubs composite = Mtable.getSub(subName);
		if (composite != null) {
			FieldNode subNode = new FieldNode();
			subNode.Parent = this;
			subNode.Level = this.Level + 1;
			subNode.RelationMeta = composite;
			subNode.OrmType = org.netsharp.dataccess.OrmType.Subs;
			subNode.Name = composite.getSubCode();

			if (subNode.Mtable == null) {
				MtableManager.getMtable(composite.getToEntityId());
				subNode.Mtable = (Mtable) composite.getToTable();
			}

			Subs.add(subNode);

			// 添加外键引用
			FieldNode fkNode = subNode.addSub(composite.getForeignProperty());
			fkNode.PropertyMeta = null;

			return subNode;
		}

		//
		// 基本属性或者ForigenKey
		//
		Column propertyMeta = Mtable.getPropertyOrColumn(subName);
		if (propertyMeta != null) {
			FieldNode subNode = new FieldNode();
			subNode.Parent = this;
			subNode.Level = this.Level + 1;
			subNode.PropertyMeta = propertyMeta;
			subNode.OrmType = org.netsharp.dataccess.OrmType.Property;
			subNode.Name = propertyMeta.getColumnName();

			Subs.add(subNode);

			return subNode;
		}

		String pathName = this.getPathName() + Spliter + subName;
		pathName = "/" + pathName.replace("_", "/");

		throw new OqlParseException("未能解析实体关系路径：" + pathName);
	}

	public FieldNode get(String[] paths) {
		if (Level == paths.length - 1) {
			return this;
		} else if ((this.Level + 1) >= paths.length) {
			return null;
		} else {
			FieldNode sub = null;

			for (FieldNode node : this.Subs) {
				if (StringManager.equals(node.Name, paths[Level + 1], true)) {
					sub = node;
					break;
				}
			}

			if (sub != null) {
				return sub.get(paths);
			} else {
				return null;
			}
		}
	}

	@SuppressWarnings("static-access")
	public void join(ArrayList<String> joins) {
		if (Level == -1) {
			for (FieldNode sub : Subs) {
				sub.join(joins);
			}
		} else if (OrmType == OrmType.Reference || OrmType == OrmType.CompositeOne) {
			TableReference r = (TableReference) this.RelationMeta;

			// String join =
			// "LEFT JOIN {ReferenceTableName} AS {ReferenceRole} " +
			// Oql.WithNolock() + " ON {MainRole}.{FK}={ReferenceRole}.Id";
			String join = "LEFT JOIN {ReferenceTableName} AS {ReferenceRole} " + Oql.WithNolock() + " ON {MainRole}.{FK}={ReferenceRole}." + r.getPriamyKey();
			if (!StringManager.isNullOrEmpty(r.getConstraint())) {
				// 添加引用约束
				join = join + "AND " + r.getConstraint();
			}

			join = join.replace("{ReferenceTableName}", r.getToTable().getTableName());
			join = join.replace("{ReferenceRole}", this.getPathName().replace("/", "_"));
			join = join.replace("{FK}", r.getFkColumn().getColumnName());
			if (Level == 0) {
				join = join.replace("{MainRole}", this.Parent.Mtable.getCode());
			} else {
				join = join.replace("{MainRole}", Parent.getPathName());
			}

			if (!joins.contains(join)) {
				joins.add(join);
			}

			for (FieldNode sub : Subs) {
				sub.join(joins);
			}
		} else if (OrmType == OrmType.Subs) {
			TableSubs r = (TableSubs) this.RelationMeta;

			String join = "LEFT JOIN {SubTableName} AS {SubRole} " + Oql.WithNolock() + "ON {SubRole}.{FK}={MainRole}."+r.getPriamyKey();

			join = join.replace("{SubTableName}", r.getToTable().getTableName());
			join = join.replace("{SubRole}", this.getPathName().replace("/", "_"));
			join = join.replace("{FK}", r.getFkColumn().getColumnName());
			if (Level == 0) {
				join = join.replace("{MainRole}", Parent.Mtable.getCode());
			} else {
				join = join.replace("{MainRole}", Parent.getPathName());
			}

			if (!joins.contains(join)) {
				joins.add(join);
			}

			for (FieldNode sub : Subs) {
				sub.join(joins);
			}
		} else {
		}
	}

	@SuppressWarnings("static-access")
	public ArrayList<FieldNode> getSubProperties() {
		if (Subs == null) {
			return null;
		}

		ArrayList<FieldNode> properties = new ArrayList<FieldNode>();
		for (FieldNode node : this.Subs) {
			if (node.OrmType == OrmType.Property) {
				properties.add(node);
			}
		}

		return properties;
	}

	public String getPathName() {
		String s = this.Name;
		FieldNode parent = this.Parent;

		while (parent != null && parent.Level > -1) {
			s = parent.Name + Spliter + s;

			parent = parent.Parent;
		}

		return s;
	}

	@SuppressWarnings("static-access")
	public boolean getHasCompositeWhere() {
		if (Subs == null) {
			return false;
		}

		for (FieldNode sub : Subs) {
			if (sub.OrmType == OrmType.Subs) {
				return true;
			} else {
				if (sub.getHasCompositeWhere()) {
					return true;
				}
			}
		}

		return false;
	}

	public static String Spliter = "_";

	@Override
	public String toString() {
		return this.Name;
	}
}
