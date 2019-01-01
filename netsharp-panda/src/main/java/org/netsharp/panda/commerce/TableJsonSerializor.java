package org.netsharp.panda.commerce;
//package org.netsharp.panda.commerce;
//
//import java.util.ArrayList;
//
//import org.netsharp.core.Column;
//import org.netsharp.core.IRow;
//import org.netsharp.core.ITable;
//import org.netsharp.core.convertor.ITypeConvertor;
//import org.netsharp.core.convertor.TypeConvertorFactory;
//import org.netsharp.core.util.StringManager;
//
//public class TableJsonSerializor
//{
//    public String Seralize(ITable<?> table)
//    {
//        if (table == null)
//        {
//            return "";
//        }
//
//        String SerlizeType = table.getEntityId();
//
//        StringBuilder builder = new StringBuilder();
//
//        int totalCount = table.getTotalCount() > 0 ? table.getTotalCount() : table.size();
//
//        builder.append("{\"total\":" +totalCount  + ",").append(StringManager.NewLine);
//        builder.append("\"rows\":[").append(StringManager.NewLine);
//
//        for (int i = 0; i < table.size(); i++){
//        	
//            IRow row = table.get(i);
//
//            builder.append("{").append(StringManager.NewLine);
//
//            builder.append("\"SerlizeType\" :\"" + SerlizeType + "\",").append(StringManager.NewLine);
//
//            ArrayList<String> properties = new ArrayList<String>();
//            for (Column column : table.getColumns()){
//                Object propertyValue = column.getProperty().field(row);
//
//                if (propertyValue == null)
//                {
//                    continue;
//                }
//
//                ITypeConvertor tc = TypeConvertorFactory.create(column.getType());
//
//                properties.add("\"" + column.getPropertyName() + "\":" + tc.toJson(propertyValue));
//            }
//
//            builder.append(StringManager.join(",", properties)).append(StringManager.NewLine);
//
//            builder.append("}").append(StringManager.NewLine);
//
//            if (table.size() - 1 > i)
//            {
//                builder.append(",").append(StringManager.NewLine);
//            }
//        }
//        builder.append("]").append(StringManager.NewLine);
//        builder.append("}").append(StringManager.NewLine);
//
//        return builder.toString();
//    }
//}
//