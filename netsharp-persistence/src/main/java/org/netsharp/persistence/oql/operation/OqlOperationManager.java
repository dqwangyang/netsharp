package org.netsharp.persistence.oql.operation;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.netsharp.persistence.oql.languangeEngine.items.Operation;
import org.netsharp.persistence.oql.operation.arithmetic.AddOperation;
import org.netsharp.persistence.oql.operation.arithmetic.DivisionOperation;
import org.netsharp.persistence.oql.operation.arithmetic.MultiOperation;
import org.netsharp.persistence.oql.operation.arithmetic.SubOperation;
import org.netsharp.persistence.oql.operation.orderby.AscOrderOperation;
import org.netsharp.persistence.oql.operation.orderby.DescOrderOperation;
import org.netsharp.persistence.oql.operation.select.ALLOperation;
import org.netsharp.persistence.oql.operation.select.ASOperation;
import org.netsharp.persistence.oql.operation.select.DistinctOperation;
import org.netsharp.persistence.oql.operation.select.FieldJoinOperation;
import org.netsharp.persistence.oql.operation.select.TopOperation;
import org.netsharp.persistence.oql.operation.where.AndOperation;
import org.netsharp.persistence.oql.operation.where.BooleanOperation;
import org.netsharp.persistence.oql.operation.where.OROperation;


public class OqlOperationManager
{
    private static LinkedHashMap<String, Operation> selectOperations;
    private static LinkedHashMap<String, Operation> whereOperations;
    private static LinkedHashMap<String, Operation> orderByOperations;

    static {
        OqlOperationManager.getOrderByOperations();
        OqlOperationManager.getSelectOperations();
        OqlOperationManager.getWhereOperations();
    }

    public static LinkedHashMap<String, Operation> getSelectOperations()
    {
    	if (selectOperations == null)
        {
            selectOperations = new LinkedHashMap<String, Operation>();

            Operation operation = new AddOperation();
            selectOperations.put(operation.getValue(), operation);

            operation = new ALLOperation();
            selectOperations.put(operation.getValue(), operation);

            operation = new ASOperation();
            selectOperations.put(operation.getValue(), operation);

            operation = new DistinctOperation();
            selectOperations.put(operation.getValue(), operation);

            operation = new DivisionOperation();
            selectOperations.put(operation.getValue(), operation);

            operation = new FieldJoinOperation();
            selectOperations.put(operation.getValue(), operation);

            operation = new MultiOperation();
            selectOperations.put(operation.getValue(), operation);

            operation = new SubOperation();
            selectOperations.put(operation.getValue(), operation);

            operation = new TopOperation();
            selectOperations.put(operation.getValue(), operation);
        }

        return selectOperations;
    }

    public static LinkedHashMap<String, Operation> getWhereOperations()
    {
    	if (whereOperations == null)
        {
            whereOperations = new LinkedHashMap<String, Operation>();

            Operation operation = new AndOperation();
            whereOperations.put(operation.getValue(), operation);

            operation = new OROperation();
            whereOperations.put(operation.getValue(), operation);

            ArrayList<BooleanOperation> operations = BooleanOperation.create();
            for (Operation op : operations)
            {
                whereOperations.put(op.getValue(), op);
            }

            //operation = new IsNotOperation();
            //whereOperations.put(operation.value(), operation);

            //operation = new IsOperation();
            //whereOperations.put(operation.value(), operation);
        }

        return whereOperations;
    }

    /// <summary>
    /// 排序操作符字典
    /// </summary>
    public static LinkedHashMap<String, Operation> getOrderByOperations()
    {
    	if (orderByOperations == null)
        {
            orderByOperations = new LinkedHashMap<String, Operation>();

            //Operation operation = new OrderByOperation();
            //orderByOperations.put(operation.value(), operation);

            Operation operation = new FieldJoinOperation();
            orderByOperations.put(operation.getValue(), operation);

            operation = new AscOrderOperation();
            orderByOperations.put(operation.getValue(), operation);

            operation = new DescOrderOperation();
            orderByOperations.put(operation.getValue(), operation);
        }
        return orderByOperations;
    }
}
