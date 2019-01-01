package org.netsharp.persistence.oql.select;

import org.netsharp.persistence.oql.languangeEngine.LanguangeEngine;

    /*
     * 暂时不支持边界的解析
     */

    public class SelectLanguange extends LanguangeEngine
    {
        /// <summary>
        /// 生成Token
        /// </summary>
        /// <returns></returns>
        @Override
        protected  void lexer()
        {
        	SelectLexer lexer=new SelectLexer(); 
        	lexer.setEngine(this);
        	lexer.execute();
        	
        }

        /// <summary>
        /// 生成表达式
        /// </summary>
        @Override
        protected  void syntaxer()
        {
        	SelectSyntaxer syntaxer=new SelectSyntaxer(); 
        	syntaxer.setEngine(this);
        	syntaxer.execute();
        }

        @Override
        protected  void compile()
        {
        	SelectCompiler compioler=new SelectCompiler(); 
        	compioler.setEngine(this);
        	compioler.execute();
        }

        @Override
        protected  void execute()
        {
        }
    }
