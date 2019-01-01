package org.netsharp.persistence.oql.languangeEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.netsharp.persistence.oql.languangeEngine.items.Expression;
import org.netsharp.persistence.oql.languangeEngine.items.Operation;


public class EngineContext
{
    public String SourceCode;
    public String LexerCode;
    public Expression ExpressionTree;
    public String CompileCode;
    public LinkedHashMap<String, Operation> OperationTokens;
    public ArrayList<Expression> ExpressionTokens;

    public HashMap<String, Object> States = new HashMap<String, Object>();

    @SuppressWarnings("unchecked")
	public <T> T GetState(String key)
    {
        return (T)States.get(key);
    }
}