package org.netsharp.persistence.oql.parser.set;

import org.netsharp.core.MtableManager;
import org.netsharp.core.Oql;
import org.netsharp.dataccess.OrmType;
import org.netsharp.persistence.oql.parser.FieldNode;
import org.netsharp.persistence.oql.parser.OqlContex;
import org.netsharp.persistence.oql.parser.OqlParser;

public class SetParser extends OqlParser
{
    public OqlContex parseSet(Oql oql)
    {
        //
        this.oql = oql;

        meta = MtableManager.getMtable(oql.getEntityId());

        if (oql.getSelects() == "*")
        {
            oql.setSelects(meta.getCode() + ".*");
        }

        //
        ParseWhere();
        ParseSelect();
        ParseOrderby();
        ParseGroupby();

        //
        MainOqlGenerator mainGenrator = new MainOqlGenerator();
        mainGenrator.setCanGroupby(false);

        mainGenrator.generate(oqlContex, oqlContex.Select.FieldNode);

        for (FieldNode fieldNode : oqlContex.Select.FieldNode.Subs)
        {
            if (fieldNode.OrmType != OrmType.Property)
            {
                IOqlGenerator subordinateGenrator = new SubordinateOqlGenerator();
                subordinateGenrator.generate(oqlContex, fieldNode);
            }
        }

        return oqlContex;
    }

    public OqlStruct parseMain(Oql oql)
    {
        //
        this.oql = oql;

        meta = MtableManager.getMtable(oql.getEntityId());

        if (oql.getSelects() == "*")
        {
            oql.setSelects(meta.getCode() + ".*");
        }

        //
        ParseWhere();
        ParseSelect();
        ParseOrderby();
        ParseGroupby();

        //
        IOqlGenerator mainGenrator = new MainOqlGenerator();
        mainGenrator.generate(oqlContex, oqlContex.Select.FieldNode);

        return oqlContex.OqlStructs.get(oql.getEntityId()).get(0);
    }

    public OqlContex parseSubs()
    {
        for (FieldNode fieldNode : oqlContex.Select.FieldNode.Subs)
        {
            if (fieldNode.OrmType != OrmType.Property)
            {
                IOqlGenerator subordinateGenrator = new SubordinateOqlGenerator();
                subordinateGenrator.generate(oqlContex, fieldNode);
            }
        }

        return oqlContex;
    }
}
