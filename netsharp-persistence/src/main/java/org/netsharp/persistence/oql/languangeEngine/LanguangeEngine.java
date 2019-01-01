package org.netsharp.persistence.oql.languangeEngine;

import org.netsharp.persistence.oql.OqlParseException;

    public abstract class LanguangeEngine implements ILanguange
    {
        private EngineContext context;
        protected Object state;

        public void run(EngineContext context)
        {
            if (context == null)
            {
                throw new OqlParseException("参数不能为空，必须初始化LaguangeEngineContext");
            }

            this.context = context;
            
            //词法分析
            this.lexer();

            //语法分析
            this.syntaxer();

            //编译
            this.compile();

            //执行
            this.execute();
        }

        /// <summary>
        /// 词法分析
        /// </summary>
        protected abstract void lexer();

        /// <summary>
        /// 语法分析
        /// </summary>
        protected abstract void syntaxer();

        /// <summary>
        /// 编译
        /// </summary>
        protected abstract void compile();

        /// <summary>
        /// 执行编译后的代码
        /// </summary>
        protected abstract void execute();

        public EngineContext getContext()
        {
        	return context;
        }
    }
