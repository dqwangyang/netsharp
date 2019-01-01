package org.netsharp.persistence.oql.languangeEngine.items;

import org.netsharp.persistence.oql.languangeEngine.LaguangeStep;
import org.netsharp.util.StringManager;


public class Lexer extends LaguangeStep
    {
        protected String arranggSelect;

        @Override
        public  void execute()
        {
            arrange();

            this.getEngine().getContext().LexerCode = arranggSelect;
        }

        public void arrange()
        {
            String selectsOql = this.getEngine().getContext().SourceCode;

            if (selectsOql == null || StringManager.isNullOrEmpty(selectsOql.trim()))
            {
                return;
            }

            this.arranggSelect = removeNoUseToken(selectsOql);
        }

        public String removeNoUseToken(String str)
        {
            if (str == null) return null;
            for (String s : Whitespaces)
            {
                str = str.replace(s, " ");
            }
            str = mergeWhiteSpace(str);
            return str;
        }

        public String mergeWhiteSpace(String str)
        {
            if (str.indexOf("  ") == -1)
            {
                return str;
            }
            else
            {
                str = str.replace("  ", " ");
                return mergeWhiteSpace(str);
            }
        }

        public static String[] Whitespaces = new String[] { "\n\r", "\r\n", "\r", "\n", "\f", "\t" };
        public static String[] Dotspaces = new String[] { " .", ". ", " . " };
    }
