package org.netsharp.persistence.oql.parser.table;

import org.netsharp.core.MtableManager;
import org.netsharp.core.Oql;
import org.netsharp.core.Paging;
import org.netsharp.dataccess.DatabaseProperty;
import org.netsharp.dataccess.IPagingGenerator;
import org.netsharp.dataccess.PagingGeneratorFactory;
import org.netsharp.dataccess.PagingObject;
import org.netsharp.persistence.oql.parser.OqlParser;
import org.netsharp.persistence.oql.parser.SbManager;
import org.netsharp.persistence.oql.parser.set.MainOqlGenerator;
import org.netsharp.persistence.oql.parser.set.OqlStruct;
import org.netsharp.util.StringManager;

public class TableParser extends OqlParser
    {
        public String parseTable(Oql oql)
        {
            OqlStruct os = parseMain(oql);

            return generate(os);
        }

        public OqlStruct parseMain(Oql oql)
        {
            this.oql = oql;
            meta = MtableManager.getMtable(oql.getEntityId());

            if (oql.getSelects() == "*")
            {
                oql.setSelects( meta.getCode() + ".*");
            }

            ParseWhere();
            ParseSelect();
            ParseOrderby();
            ParseGroupby();

            MainOqlGenerator mainGenrator = new MainOqlGenerator();
            mainGenrator.setCanGroupby(true);

            mainGenrator.generate(oqlContex, oqlContex.Select.FieldNode);

            OqlStruct os = oqlContex.OqlStructs.get(oql.getEntityId()).get(0);
            os.Selects = oqlContex.Select.Result;
            os.Joins = this.ParseJoins();

            return os;
        }

        public String generate(OqlStruct os)
        {
            Paging p = this.oql.getPaging();
            if(p!=null) {
            	PagingObject paging = new PagingObject();
    			{
    				paging.PageSize = p.getPageSize();
    				paging.PageIndex = p.getPageNo();
    				paging.TableName = meta.getTableName() + " AS " + meta.getCode() + Oql.WithNolock();
    				if(!StringManager.isNullOrEmpty(os.Joins)) {
    					paging.TableName += " "+os.Joins;
    				}
    				paging.Selects = os.Selects;
    				paging.Filter = os.Wheres;
    				paging.Orderby = this.oqlContex.Orderby.Result;
    			}
    			
    			IPagingGenerator g = PagingGeneratorFactory.create(DatabaseProperty.defaultDatabaseProperty().getType());
    			String cmdText = g.generatePaging(paging);
    			
    			return cmdText;
            }
            else {
            	
            	 StringBuilder builder = new StringBuilder();

                 builder.append("SELECT ").append(os.Selects).append(StringManager.NewLine)
                 .append("FROM ").append(meta.getTableName() + " AS " + meta.getCode()).append(Oql.WithNolock()).append(StringManager.NewLine);
                 
                 SbManager.appendLineReqired(builder, null, os.Joins);
                 SbManager.appendLineReqired(builder,"WHERE ", os.Wheres);
                 SbManager.appendLineReqired(builder,"GROUP BY ", oqlContex.Groupby.Result);
                 SbManager.appendLineReqired(builder,"ORDER BY ", this.oqlContex.Orderby.Result);
                 
            	 return builder.toString();
            }
        }
    }
