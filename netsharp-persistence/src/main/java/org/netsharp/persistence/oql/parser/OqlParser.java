package org.netsharp.persistence.oql.parser;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.core.Mtable;
import org.netsharp.core.Oql;
import org.netsharp.persistence.oql.OqlParseException;
import org.netsharp.persistence.oql.expression.ExpressionManager;
import org.netsharp.persistence.oql.languangeEngine.EngineContext;
import org.netsharp.persistence.oql.languangeEngine.ILanguange;
import org.netsharp.persistence.oql.operation.OqlOperationManager;
import org.netsharp.persistence.oql.orderby.OrderbyLanguange;
import org.netsharp.persistence.oql.select.SelectLanguange;
import org.netsharp.persistence.oql.where.WhereLanguange;
import org.netsharp.util.StringManager;

public abstract class OqlParser
{
	protected final Log logger = LogFactory.getLog(this.getClass());
	//
    protected Oql oql;
    protected OqlContex oqlContex = new OqlContex();
    protected Mtable meta;

    protected EngineContext selectContext;
    protected EngineContext whereContex;
    protected EngineContext orderbyContext;
    protected EngineContext groupbyContext;

    protected void ParseSelect()
    {
        oqlContex.Select = new Oqlpart();
        
        oqlContex.Select.setOqlparts(Oqlparts.Select);
    	oqlContex.Select.Mtable = meta;
    	oqlContex.Select.FieldNode = new FieldNode();
    	
    	oqlContex.Select.FieldNode.Level=-1;
    	oqlContex.Select.FieldNode.Mtable=meta;
        

        //COUNT查询时Selectes可以为空
        if (StringManager.isNullOrEmpty(oql.getSelects()))
        {
            return;
        }

        ILanguange selectEngine = new SelectLanguange();
        selectContext = new EngineContext();
        
        selectContext.SourceCode = oql.getSelects();
		selectContext.OperationTokens = OqlOperationManager.getSelectOperations();
		selectContext.ExpressionTokens = ExpressionManager.getSelectExps();

        selectContext.States.put(Oql.class.getSimpleName(), oql);
        selectContext.States.put(Oqlpart.class.getSimpleName(), oqlContex.Select);

        try {
			selectEngine.run(selectContext);
		} catch (OqlParseException e) {
			logger.error("OQL SELECT 异常"+ StringManager.NewLine+oql.toString());
			throw e;
		}

        //Table查询和Set查询不一样
        //这个字段暂时只在Table查询中使用
        oqlContex.Select.Result = selectContext.ExpressionTree.generate();
    }

    protected void ParseWhere()
    {
    	oqlContex.Where = new Oqlpart();
        oqlContex.Where.setOqlparts(Oqlparts.Where);
		oqlContex.Where.Mtable = meta;
		oqlContex.Where.FieldNode = new FieldNode();
				
		oqlContex.Where.FieldNode.Level=-1;
		oqlContex.Where.FieldNode.Mtable=meta;
				

        if (StringManager.isNullOrEmpty(oql.getFilter()))
        {
            return;
        }

        ILanguange whereEngine = new WhereLanguange();

        whereContex = new EngineContext();
        whereContex.SourceCode = oql.getFilter();
		whereContex.OperationTokens = OqlOperationManager.getWhereOperations();
		whereContex.ExpressionTokens = ExpressionManager.getWhereExps();

        whereContex.States.put(Oql.class.getSimpleName(), oql);
        whereContex.States.put(Oqlpart.class.getSimpleName(), oqlContex.Where);

        try {
			whereEngine.run(whereContex);
		} catch (OqlParseException e) {
			logger.error("OQL WHERE 异常"+ StringManager.NewLine + oql.toString());
			throw e;
		}

        oqlContex.Where.Result = whereContex.ExpressionTree.generate();
    }

    protected String ParseJoins()
    {
        ArrayList<String> joins = new ArrayList<String>();
        oqlContex.Select.FieldNode.join(joins);

        if (oqlContex.Where != null)
        {
            ArrayList<String> whereJoins = new ArrayList<String>();
            oqlContex.Where.FieldNode.join(whereJoins);

            for (String s : whereJoins)
            {
                if (!joins.contains(s))
                {
                    joins.add(s);
                }
            }
        }

        String join = StringManager.join(StringManager.NewLine, joins);

        return join;
    }

    protected void ParseOrderby()
    {
        oqlContex.Orderby = new Oqlpart();
        oqlContex.Orderby.setOqlparts( Oqlparts.Orderby );
        oqlContex.Orderby.Mtable = meta;
        oqlContex.Orderby.FieldNode = new FieldNode();
        
        oqlContex.Orderby.FieldNode.Level=-1;
        oqlContex.Orderby.FieldNode.Mtable=meta;
        

        if (StringManager.isNullOrEmpty(oql.getOrderby()))
        {
            return;
        }

        ILanguange orderByEngine = new OrderbyLanguange();
        orderbyContext = new EngineContext();
        orderbyContext.SourceCode = oql.getOrderby();
		orderbyContext.OperationTokens = OqlOperationManager.getOrderByOperations();
		orderbyContext.ExpressionTokens = ExpressionManager.getOrderbyExps();

        orderbyContext.States.put(Oql.class.getSimpleName(), oql);
        orderbyContext.States.put(Oqlpart.class.getSimpleName(), oqlContex.Orderby);

        try {
			orderByEngine.run(orderbyContext);
		} catch (OqlParseException e) {
			logger.error("OQL Orderby 异常"+ StringManager.NewLine+oql.toString());
			throw e;
		}

        oqlContex.Orderby.Result = orderbyContext.ExpressionTree.generate();
    }

    protected void ParseGroupby()
    {
        oqlContex.Groupby = new Oqlpart();
        oqlContex.Groupby.setOqlparts(Oqlparts.Groupby);
        oqlContex.Groupby.Mtable = meta;
        oqlContex.Groupby.FieldNode = new FieldNode();
        
        oqlContex.Groupby.FieldNode.Level=-1;
        oqlContex.Groupby.FieldNode.Mtable=meta;

        oqlContex.Groupby.Result = oql.getGroupby();
    }
}
