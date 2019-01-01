package org.netsharp.persistence.oql.parser.set;

import org.netsharp.persistence.oql.parser.FieldNode;
import org.netsharp.persistence.oql.parser.OqlContex;

public interface IOqlGenerator
{
    void generate(OqlContex oqlContex, FieldNode oqlNode);
}
