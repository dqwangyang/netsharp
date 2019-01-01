package org.netsharp.persistence.oql.where;

import org.netsharp.persistence.oql.languangeEngine.LanguangeEngine;

public class WhereLanguange extends LanguangeEngine
{
    /// <summary>
    /// 生成Token
    /// </summary>
    /// <returns></returns>
    @Override
    protected  void lexer()
    {
        WhereLexer lexer=new WhereLexer(); 
    	lexer.setEngine(this);
    	lexer.execute();
    }

    /// <summary>
    /// 生成表达式
    /// </summary>
    @Override
    protected  void syntaxer()
    {
        WhereSyntaxer syntaxer=new WhereSyntaxer(); 
    	syntaxer.setEngine(this);
    	syntaxer.execute();
    }

    @Override
    protected  void compile()
    {
        WhereCompiler compioler=new WhereCompiler(); 
    	compioler.setEngine(this);
    	compioler.execute();
    }

    @Override
    protected  void execute()
    {
    }
}
