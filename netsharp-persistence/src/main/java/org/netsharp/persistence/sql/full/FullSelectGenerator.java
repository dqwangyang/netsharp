package org.netsharp.persistence.sql.full;

import java.util.ArrayList;

import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.TableCompositeOne;
import org.netsharp.core.TableReference;
import org.netsharp.core.TableSubs;
import org.netsharp.util.StringManager;


/// <summary>
/// 平台提供的完整查询生成器
/// 三种场景：
/// 1.FullQuery
/// 2.组合的FullQuery
/// 3.Set的FullQuery
/// </summary>
public class FullSelectGenerator {
	
	private String selectedFields;
	private Mtable mtable;
	private ArrayList<String> fieldPaths;
	private ArrayList<String> rolePaths;
	private ArrayList<String> entityIds;
	private boolean isCompositeOnly;
	
	public FullSelectGenerator(Class<?> type) {
		
		this(MtableManager.getMtable(type),"*");
		
	}
	
	public FullSelectGenerator(Mtable mtable) {

		this(mtable,"*");
		
	}

	public FullSelectGenerator(Mtable mtable, String selectedFields) {
		this.mtable = mtable;
		this.selectedFields = selectedFields;
		this.fieldPaths = new ArrayList<String>();
		this.rolePaths = new ArrayList<String>();
		this.entityIds = new ArrayList<String>();
	}

	public String generate() {
		FullSelectNode top = new FullSelectNode();

		top.setMtable(mtable);
		top.setPath(mtable.getCode());
		top.setOrmType(OrmType.Property);

		this.parseTree(top);

		if (!this.isCompositeOnly
				&& StringManager.isNullOrEmpty(mtable.getFullSelected())
				&& !StringManager.isNullOrEmpty(this.selectedFields)) {
			for (String fieldPath : fieldPaths) {
				if (fieldPath.endsWith(".")) {
					// throw new NetsharpException("查询没有设置选择的字段");
				}
			}
//			mtable.setFullSelected(StringManager.join("," + StringManager.NewLine, fieldPaths));
		}
		
		String selects = StringManager.join("," + StringManager.NewLine, fieldPaths);
		mtable.setFullSelected(selects);
		
		return selects;
	}

	public String generateSql() {
		if (this.isCompositeOnly) {
			this.generate();

			String selects = StringManager.join("," + StringManager.NewLine,
					fieldPaths);

			return selects;
		} else {
			if (StringManager.isNullOrEmpty(mtable.getFullSelected())) {
				this.generate();
			}

			return mtable.getFullSelected();
		}
	}

	private void parseTree(FullSelectNode node) {
		if (isRecursive(node)) {
			return;
		}

		fieldPaths.add(node.getPath() + "." + selectedFields);
		rolePaths.add(node.getPath());
		if (!entityIds.contains(node.getMtable().getEntityId())) {
			entityIds.add(node.getMtable().getEntityId());
		}

		// 当前节点是组合，查询引用和组合
		// 当前节点是引用，如果为关联查询或者持久化，则查询引用和组合
		// 否则终止

		for (TableSubs composite : node.getMtable().getSubs().values()) {
			FullSelectNode subNode = new FullSelectNode();
			subNode.setMtable( (Mtable) composite.getToTable());
			subNode.setPath(node.getPath() + "." + composite.getSubCode());
			subNode.setTableRelation(composite);
			subNode.setParent(node);
			subNode.setOrmType(OrmType.Subs);

			parseTree(subNode);
		}

		if (!this.isCompositeOnly) {
			for (TableReference refernece : node.getMtable().getReferences().values()) {
				if (isPathComposite(node) || refernece.getIsQuery()) {
					FullSelectNode subNode = new FullSelectNode();

					subNode.setMtable((Mtable) refernece.getToTable());
					subNode.setPath(node.getPath() + "." + refernece.getReferenceCode());
					subNode.setTableRelation(refernece);
					subNode.setParent(node);
					subNode.setOrmType(OrmType.Reference);

					parseTree(subNode);
				}
			}
		}
		
		for (TableCompositeOne refernece : node.getMtable().getCompositeOnes().values()) {
			
			FullSelectNode subNode = new FullSelectNode();

			subNode.setMtable((Mtable) refernece.getToTable());
			subNode.setPath(node.getPath() + "." + refernece.getReferenceCode());
			subNode.setTableRelation(refernece);
			subNode.setParent(node);
			subNode.setOrmType(OrmType.CompositeOne);

			parseTree(subNode);
		}
	}

	private boolean isRecursive(FullSelectNode node) {
		FullSelectNode parent = node.getParent();

		while (parent != null) {
			if (parent.getMtable() == node.getMtable()) {
				return true;
			}

			parent = parent.getParent();
		}

		return false;
	}

	boolean isPathComposite(FullSelectNode node) {
		
		FullSelectNode parent = node;

		while (parent != null) {
			if (parent.getOrmType() == OrmType.Reference) {
				return false;
			}

			parent = parent.getParent();
		}

		return true;
	}

	public String getSelectedFields() {
		return selectedFields;
	}

	public void setSelectedFields(String selectedFields) {
		this.selectedFields = selectedFields;
	}

	public Mtable getMtable() {
		return mtable;
	}

	public void setMtable(Mtable mtable) {
		this.mtable = mtable;
	}

	public void setRolePaths(ArrayList<String> rolePaths) {
		this.rolePaths = rolePaths;
	}

	public boolean getIsCompositeOnly() {
		return isCompositeOnly;
	}

	public void setIsCompositeOnly(boolean isCompositeOnly) {
		this.isCompositeOnly = isCompositeOnly;
	}

	public ArrayList<String> getRolePaths() {
		return rolePaths;
	}

	public ArrayList<String> getEntityIds() {
		return entityIds;
	}

	public ArrayList<String> getFieldPaths() {
		return fieldPaths;
	}
}