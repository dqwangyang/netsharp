package org.netsharp.persistence.oql.parser;

import java.util.HashMap;

import org.netsharp.persistence.oql.parser.set.OqlStructs;

public class OqlContex
{
    public Oqlpart Select ;

    public Oqlpart Where ;

    public Oqlpart Orderby ;

    public Oqlpart Groupby ;

    public HashMap<String, org.netsharp.persistence.oql.parser.set.OqlStructs> OqlStructs = new HashMap<String, OqlStructs>();
}
