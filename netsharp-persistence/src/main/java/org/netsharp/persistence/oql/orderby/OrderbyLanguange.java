package org.netsharp.persistence.oql.orderby;

import org.netsharp.persistence.oql.languangeEngine.LanguangeEngine;
import org.netsharp.persistence.oql.select.SelectCompiler;

/// <summary>
/// Order By 解析引擎
/// </summary>
public class OrderbyLanguange extends LanguangeEngine
{
    /// <summary>
    /// 生成Token
    /// </summary>
    /// <returns></returns>
    @Override
    protected  void lexer()
    {
        OrderByLexer lexer=new OrderByLexer(); 
    	lexer.setEngine(this);
    	lexer.execute();
    }

    /// <summary>
    /// 生成表达式
    /// </summary>
    @Override
    protected  void syntaxer()
    {
        OrderBySyntaxer syntaxer=new OrderBySyntaxer(); 
    	syntaxer.setEngine(this);
    	syntaxer.execute();
    }

    /// <summary>
    /// 编译
    /// </summary>
    @Override
    protected  void compile()
    {
    	SelectCompiler compioler=new SelectCompiler();
    	compioler.setEngine(this);
    	compioler.execute();
    }

    /// <summary>
    /// 执行
    /// </summary>
    @Override
    protected  void execute()
    {
    }
}
