package org.netsharp.persistence.sql.full;

import org.netsharp.core.Mtable;
import org.netsharp.core.TableRelation;

public class FullSelectNode
{
    private String path;
    private Mtable mtable;
    private TableRelation tableRelation;
    private FullSelectNode parent;
    private OrmType ormType;

    @Override
    public String toString()
    {
        return this.path;
    }

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Mtable getMtable() {
		return mtable;
	}

	public void setMtable(Mtable mtable) {
		this.mtable = mtable;
	}

	public TableRelation getTableRelation() {
		return tableRelation;
	}

	public void setTableRelation(TableRelation tableRelation) {
		this.tableRelation = tableRelation;
	}

	public FullSelectNode getParent() {
		return parent;
	}

	public void setParent(FullSelectNode parent) {
		this.parent = parent;
	}

	public OrmType getOrmType() {
		return ormType;
	}

	public void setOrmType(OrmType ormType) {
		this.ormType = ormType;
	}
}