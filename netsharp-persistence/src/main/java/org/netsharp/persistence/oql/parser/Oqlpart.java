package org.netsharp.persistence.oql.parser;

import org.netsharp.core.Mtable;

/// <summary>
/// OQL解析部件上下文
/// 部件指的是为SELECT/WHERE/ORDER BY的一种
/// </summary>
public class Oqlpart
{
    public Mtable Mtable ;

    public FieldNode FieldNode ;

    private Oqlparts oqlparts ;

    /// <summary>
    /// 编译后的结果
    /// </summary>
    public String Result ;

    public FieldNode get(String path)
    {
        String[] paths = path.split("\\.");

        return FieldNode.get(paths);
    }

	public Oqlparts getOqlparts() {
		return oqlparts;
	}

	public void setOqlparts(Oqlparts oqlparts) {
		this.oqlparts = oqlparts;
	}
}


