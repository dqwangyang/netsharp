package org.netsharp.core;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import org.netsharp.entity.DataType;

public class DatatypeManager
{
	public static DataType ById(UUID id)
    {
        return dataTypeIds.get(id);
    }

    public static DataType ByCode(String code)
    {
        for (DataType dt : getTable())
        {
            if (dt.getCode().equalsIgnoreCase( code)) {
                return dt;
            }
        }

        return null;
    }

    public static DataType ByJavaType(Class<?> type)
    {
        if (type == boolean.class || type==Boolean.class)
        {
            return DataTypes.Boolean;
        }
        else if (type == int.class || type == Long.class)
        {
            return DataTypes.Long;
        }
        else if (type == short.class || type == Short.class)
        {
            return DataTypes.Short;
        }
        else if (type == long.class || type == Long.class)
        {
            return DataTypes.Long;
        }
        else if (type == Date.class || type == Timestamp.class)
        {
            return DataTypes.DateTime;
        }
        else if (type == UUID.class)
        {
            return DataTypes.Guid;
        }
        else if (type == BigDecimal.class)
        {
            return DataTypes.Decimal;
        }
        else if (type == float.class || type==Float.class)
        {
            return DataTypes.Float;
        }
        else if (type == double.class || type==Double.class)
        {
            return DataTypes.Double;
        }
        else if (type == byte[].class || type==Byte[].class)
        {
            return DataTypes.Binary;
        }
        else if (type.isEnum())
        {
            return DataTypes.Long;
        }
        else
        {
            return DataTypes.String;
        }
    }

	public  static void initialize()
    {
        table =new ArrayList<DataType>();// (Table<DataType>) SysEntityManager.createTable(DataType.class.getName(),nul);

        DataType dataType = null;

        //
		dataType = new DataType();table.add(dataType);
		DataTypes.Long = dataType;
		dataType.setPrecisionDb((short)(short)0);
		dataType.setPrecisionDisplay((short)0);
		dataType.setPrecision(false);
		dataType.setSizeDb((short)0);
		dataType.setSizeDisplay((short)0);
		dataType.setControlType("NumberBox");
		dataType.setSize(false);
		dataType.setDbType("Int32");
		dataType.setCsharp("System.Int32");
		dataType.setSqlServer("int");
		dataType.setAccess("int");
		dataType.setMySql("INT");
		dataType.setOracle("INT");
		dataType.setDb2("");
		dataType.setIcon("Icons.16x16.DataSource.Number.png");
		dataType.setSplitter(true);
		dataType.setCode("Long");
		dataType.setName("整数(32)");
		dataType.setJava("java.lang.Long");
		dataType.setJdbcType(0);
		dataType.setId(1L);
		dataTypeIds.put(dataType.getId(), dataType);
		
		dataType = new DataType();table.add(dataType);
		DataTypes.Short = dataType;
		dataType.setPrecisionDb((short)0);
		dataType.setPrecisionDisplay((short)0);
		dataType.setPrecision(false);
		dataType.setSizeDb((short)0);
		dataType.setSizeDisplay((short)0);
		dataType.setControlType("TextBox");
		dataType.setSize(false);
		dataType.setDbType("Int16");
		dataType.setCsharp("System.Int16");
		dataType.setSqlServer("SMALLINT");
		dataType.setAccess("int");
		dataType.setMySql("SMALLINT");
		dataType.setOracle("SMALLINT");
		dataType.setDb2("");
		dataType.setIcon("Icons.16x16.DataSource.Number.png");
		dataType.setSplitter(true);
		dataType.setCode("Short");
		dataType.setName("短整数(16)");
		dataType.setJava("java.lang.Short");
		dataType.setJdbcType(0);
		dataType.setId(2L);
		dataTypeIds.put(dataType.getId(), dataType);
		
		dataType = new DataType();table.add(dataType);
		DataTypes.Long = dataType;
		dataType.setPrecisionDb((short)0);
		dataType.setPrecisionDisplay((short)0);
		dataType.setPrecision(false);
		dataType.setSizeDb((short)0);
		dataType.setSizeDisplay((short)0);
		dataType.setControlType("TextBox");
		dataType.setSize(false);
		dataType.setDbType("Int64");
		dataType.setCsharp("System.Int64");
		dataType.setSqlServer("BIGINT");
		dataType.setAccess("int");
		dataType.setMySql("BIGINT");
		dataType.setOracle("BIGINT");
		dataType.setDb2("");
		dataType.setIcon("Icons.16x16.DataSource.Number.png");
		dataType.setSplitter(true);
		dataType.setCode("Long");
		dataType.setName("长整数(64)");
		dataType.setJava("java.lang.Long");
		dataType.setJdbcType(0);
		dataType.setId(3L);
		dataTypeIds.put(dataType.getId(), dataType);
		
		dataType = new DataType();table.add(dataType);
		DataTypes.Char = dataType;
		dataType.setPrecisionDb((short)0);
		dataType.setPrecisionDisplay((short)0);
		dataType.setPrecision(false);
		dataType.setSizeDb((short)10);
		dataType.setSizeDisplay((short)0);
		dataType.setControlType("TextBox");
		dataType.setSize(true);
		dataType.setDbType("String");
		dataType.setCsharp("System.String");
		dataType.setSqlServer("char");
		dataType.setAccess("varchar");
		dataType.setMySql("CHAR");
		dataType.setOracle("CHAR");
		dataType.setDb2("");
		dataType.setIcon("Icons.16x16.DataSource.String.png");
		dataType.setSplitter(false);
		dataType.setCode("Char");
		dataType.setName("定长文本");
		dataType.setJava("java.lang.String");
		dataType.setJdbcType(0);
		dataType.setId(4L);
		dataTypeIds.put(dataType.getId(), dataType);
		
		dataType = new DataType();table.add(dataType);
		DataTypes.Binary = dataType;
		dataType.setPrecisionDb((short)0);
		dataType.setPrecisionDisplay((short)0);
		dataType.setPrecision(false);
		dataType.setSizeDb((short)200);
		dataType.setSizeDisplay((short)0);
		dataType.setControlType("TextBox");
		dataType.setSize(false);
		dataType.setDbType("Binary");
		dataType.setCsharp("System.Byte[]");
		dataType.setSqlServer("Image");
		dataType.setAccess("Byte");
		dataType.setMySql("BLOB");
		dataType.setOracle("BLOB");
		dataType.setDb2("");
		dataType.setIcon("Icons.16x16.Report.Image.png");
		dataType.setSplitter(true);
		dataType.setCode("Binary");
		dataType.setName("二进制");
		dataType.setJava("java.lang.Byte");
		dataType.setJdbcType(0);
		dataType.setId(5L);
		dataTypeIds.put(dataType.getId(), dataType);
		
		dataType = new DataType();table.add(dataType);
		DataTypes.Timestamp = dataType;
		dataType.setPrecisionDb((short)0);
		dataType.setPrecisionDisplay((short)0);
		dataType.setPrecision(false);
		dataType.setSizeDb((short)0);
		dataType.setSizeDisplay((short)0);
		dataType.setControlType("TextBox");
		dataType.setSize(false);
		dataType.setDbType("Binary");
		dataType.setCsharp("System.Byte[]");
		dataType.setSqlServer("timestamp");
		dataType.setAccess("varchar");
		dataType.setMySql("TIMESTAMP");
		dataType.setOracle("TIMESTAMP");
		dataType.setDb2("");
		dataType.setIcon("Icons.16x16.System.Bullet_orange.PNG");
		dataType.setSplitter(true);
		dataType.setCode("Timestamp");
		dataType.setName("时间戳");
		dataType.setJava("java.lang.Byte");
		dataType.setJdbcType(0);
		dataType.setId(6L);
		dataTypeIds.put(dataType.getId(), dataType);
		
		dataType = new DataType();table.add(dataType);
		DataTypes.Time = dataType;
		dataType.setPrecisionDb((short)0);
		dataType.setPrecisionDisplay((short)0);
		dataType.setPrecision(false);
		dataType.setSizeDb((short)0);
		dataType.setSizeDisplay((short)0);
		dataType.setControlType("Time");
		dataType.setSize(false);
		dataType.setDbType("DateTime");
		dataType.setCsharp("System.DateTime");
		dataType.setSqlServer("Time");
		dataType.setAccess("datetime");
		dataType.setMySql("TIME");
		dataType.setOracle("DATE");
		dataType.setDb2("");
		dataType.setIcon("Icons.16x16.DataSource.DateTime.png");
		dataType.setSplitter(true);
		dataType.setCode("Time");
		dataType.setName("时间");
		dataType.setJava("java.util.Date");
		dataType.setJdbcType(0);
		dataType.setId(7L);
		dataTypeIds.put(dataType.getId(), dataType);
		
		dataType = new DataType();table.add(dataType);
		DataTypes.Varchar = dataType;
		dataType.setPrecisionDb((short)0);
		dataType.setPrecisionDisplay((short)0);
		dataType.setPrecision(false);
		dataType.setSizeDb((short)50);
		dataType.setSizeDisplay((short)0);
		dataType.setControlType("TextBox");
		dataType.setSize(true);
		dataType.setDbType("String");
		dataType.setCsharp("System.String");
		dataType.setSqlServer("varchar");
		dataType.setAccess("varchar");
		dataType.setMySql("VARCHAR");
		dataType.setOracle("NVARCHAR2");
		dataType.setDb2("");
		dataType.setIcon("Icons.16x16.DataSource.String.png");
		dataType.setSplitter(false);
		dataType.setCode("Varchar");
		dataType.setName("非中文字符");
		dataType.setJava("java.lang.String");
		dataType.setJdbcType(0);
		dataType.setId(8L);
		dataTypeIds.put(dataType.getId(), dataType);
		
		dataType = new DataType();table.add(dataType);
		DataTypes.Guid = dataType;
		dataType.setPrecisionDb((short)0);
		dataType.setPrecisionDisplay((short)0);
		dataType.setPrecision(false);
		dataType.setSizeDb((short)0);
		dataType.setSizeDisplay((short)0);
		dataType.setControlType("TextBox");
		dataType.setSize(false);
		dataType.setDbType("Guid");
		dataType.setCsharp("System.Guid");
		dataType.setSqlServer("uniqueidentifier");
		dataType.setAccess("varchar");
		dataType.setMySql("CHAR(36)");
		dataType.setOracle("CHAR(38)");
		dataType.setDb2("");
		dataType.setIcon("Icons.16x16.System.BlueDot.PNG");
		dataType.setSplitter(true);
		dataType.setCode("Guid");
		dataType.setName("GUID");
		dataType.setJava("java.util.UUID");
		dataType.setJdbcType(0);
		dataType.setId(9L);
		dataTypeIds.put(dataType.getId(), dataType);
		
		dataType = new DataType();table.add(dataType);
		DataTypes.Byte = dataType;
		dataType.setPrecisionDb((short)0);
		dataType.setPrecisionDisplay((short)0);
		dataType.setPrecision(false);
		dataType.setSizeDb((short)200);
		dataType.setSizeDisplay((short)0);
		dataType.setControlType("TextBox");
		dataType.setSize(true);
		dataType.setDbType("Byte");
		dataType.setCsharp("System.Byte");
		dataType.setSqlServer("tinyint");
		dataType.setAccess("Byte");
		dataType.setMySql("TINYINT");
		dataType.setOracle("TINYINT");
		dataType.setDb2("");
		dataType.setIcon("Icons.16x16.Report.Image.png");
		dataType.setSplitter(true);
		dataType.setCode("Byte");
		dataType.setName("字节");
		dataType.setJava("java.lang.Byte");
		dataType.setJdbcType(0);
		dataType.setId(10L);
		dataTypeIds.put(dataType.getId(), dataType);
		
		dataType = new DataType();table.add(dataType);
		DataTypes.Float = dataType;
		dataType.setPrecisionDb((short)14);
		dataType.setPrecisionDisplay((short)0);
		dataType.setPrecision(true);
		dataType.setSizeDb((short)28);
		dataType.setSizeDisplay((short)0);
		dataType.setControlType("TextBox");
		dataType.setSize(true);
		dataType.setDbType("Single");
		dataType.setCsharp("System.Single");
		dataType.setSqlServer("Float");
		dataType.setAccess("float");
		dataType.setMySql("FLOAT");
		dataType.setOracle("NUMBER");
		dataType.setDb2("");
		dataType.setIcon("Icons.16x16.DataSource.Number.png");
		dataType.setSplitter(true);
		dataType.setCode("Float");
		dataType.setName("浮点小数");
		dataType.setJava("java.lang.Float");
		dataType.setJdbcType(0);
		dataType.setId(11L);
		dataTypeIds.put(dataType.getId(), dataType);
		
		dataType = new DataType();table.add(dataType);
		DataTypes.Double = dataType;
		dataType.setPrecisionDb((short)14);
		dataType.setPrecisionDisplay((short)0);
		dataType.setPrecision(true);
		dataType.setSizeDb((short)28);
		dataType.setSizeDisplay((short)0);
		dataType.setControlType("TextBox");
		dataType.setSize(true);
		dataType.setDbType("Double");
		dataType.setCsharp("System.Double");
		dataType.setSqlServer("Double");
		dataType.setAccess("double");
		dataType.setMySql("Double");
		dataType.setOracle("Double");
		dataType.setDb2("");
		dataType.setIcon("Icons.16x16.DataSource.Number.png");
		dataType.setSplitter(true);
		dataType.setCode("Float");
		dataType.setName("浮点小数");
		dataType.setJava("java.lang.Double");
		dataType.setJdbcType(0);
		dataType.setId(11L);
		dataTypeIds.put(dataType.getId(), dataType);
		
		dataType = new DataType();table.add(dataType);
		DataTypes.DateTime = dataType;
		dataType.setPrecisionDb((short)0);
		dataType.setPrecisionDisplay((short)0);
		dataType.setPrecision(false);
		dataType.setSizeDb((short)0);
		dataType.setSizeDisplay((short)0);
		dataType.setControlType("DateTime");
		dataType.setSize(false);
		dataType.setDbType("DateTime");
		dataType.setCsharp("System.DateTime");
		dataType.setSqlServer("datetime");
		dataType.setAccess("datetime");
		dataType.setMySql("DATETIME");
		dataType.setOracle("DATE");
		dataType.setDb2("");
		dataType.setIcon("Icons.16x16.DataSource.DateTime.png");
		dataType.setSplitter(true);
		dataType.setCode("DateTime");
		dataType.setName("日期+时间");
		dataType.setJava("java.util.Date");
		dataType.setJdbcType(0);
		dataType.setId(12L);
		dataTypeIds.put(dataType.getId(), dataType);
		
		dataType = new DataType();table.add(dataType);
		DataTypes.Boolean = dataType;
		dataType.setPrecisionDb((short)0);
		dataType.setPrecisionDisplay((short)0);
		dataType.setPrecision(false);
		dataType.setSizeDb((short)0);
		dataType.setSizeDisplay((short)0);
		dataType.setControlType("CheckBox");
		dataType.setSize(false);
		dataType.setDbType("Boolean");
		dataType.setCsharp("System.Boolean");
		dataType.setSqlServer("BIT");
		dataType.setAccess("bit");
		dataType.setMySql("BOOL");
		dataType.setOracle("CHAR(1)");
		dataType.setDb2("");
		dataType.setIcon("Icons.16x16.DataSource.Boolean.png");
		dataType.setSplitter(true);
		dataType.setCode("Boolean");
		dataType.setName("是否");
		dataType.setJava("java.lang.Boolean");
		dataType.setJdbcType(0);
		dataType.setId(13L);
		dataTypeIds.put(dataType.getId(), dataType);
		
		dataType = new DataType();table.add(dataType);
		DataTypes.EnumItem = dataType;
		dataType.setPrecisionDb((short)0);
		dataType.setPrecisionDisplay((short)0);
		dataType.setPrecision(false);
		dataType.setSizeDb((short)0);
		dataType.setSizeDisplay((short)0);
		dataType.setControlType("EnumBox");
		dataType.setSize(false);
		dataType.setDbType("Guid");
		dataType.setCsharp("Netsharp.Core.EnumItem");
		dataType.setSqlServer("uniqueidentifier");
		dataType.setAccess("varchar");
		dataType.setMySql("CHAR(36)");
		dataType.setOracle("CHAR(38)");
		dataType.setDb2("");
		dataType.setIcon("Icons.16x16.Report.Field.png");
		dataType.setSplitter(true);
		dataType.setCode("EnumItem");
		dataType.setName("枚举");
		dataType.setJava("org.netsharp.entity.EnumItem");
		dataType.setJdbcType(0);
		dataType.setId(14L);
		dataTypeIds.put(dataType.getId(), dataType);
		
		dataType = new DataType();table.add(dataType);
		DataTypes.LongString = dataType;
		dataType.setPrecisionDb((short)0);
		dataType.setPrecisionDisplay((short)0);
		dataType.setPrecision(false);
		dataType.setSizeDb((short)8000);
		dataType.setSizeDisplay((short)0);
		dataType.setControlType("TextBox");
		dataType.setSize(false);
		dataType.setDbType("String");
		dataType.setCsharp("System.String");
		dataType.setSqlServer("NTEXT");
		dataType.setAccess("varchar");
		dataType.setMySql("MEDIUMTEXT ");
		dataType.setOracle("CLOB");
		dataType.setDb2("");
		dataType.setIcon("Icons.16x16.DataSource.String.png");
		dataType.setSplitter(false);
		dataType.setCode("LongString");
		dataType.setName("长文本");
		dataType.setJava("java.lang.String");
		dataType.setJdbcType(0);
		dataType.setId(15L);
		dataTypeIds.put(dataType.getId(), dataType);
		
		dataType = new DataType();table.add(dataType);
		DataTypes.Date = dataType;
		dataType.setPrecisionDb((short)0);
		dataType.setPrecisionDisplay((short)0);
		dataType.setPrecision(false);
		dataType.setSizeDb((short)0);
		dataType.setSizeDisplay((short)0);
		dataType.setControlType("Date");
		dataType.setSize(false);
		dataType.setDbType("DateTime");
		dataType.setCsharp("System.DateTime");
		dataType.setSqlServer("smalldatetime");
		dataType.setAccess("datetime");
		dataType.setMySql("DATE ");
		dataType.setOracle("DATE");
		dataType.setDb2("");
		dataType.setIcon("Icons.16x16.DataSource.DateTime.png");
		dataType.setSplitter(true);
		dataType.setCode("Date");
		dataType.setName("日期");
		dataType.setJava("java.util.Date");
		dataType.setJdbcType(0);
		dataType.setId(16L);
		dataTypeIds.put(dataType.getId(), dataType);
		
		dataType = new DataType();table.add(dataType);
		DataTypes.String = dataType;
		dataType.setPrecisionDb((short)0);
		dataType.setPrecisionDisplay((short)0);
		dataType.setPrecision(false);
		dataType.setSizeDb((short)50);
		dataType.setSizeDisplay((short)0);
		dataType.setControlType("TextBox");
		dataType.setSize(true);
		dataType.setDbType("String");
		dataType.setCsharp("System.String");
		dataType.setSqlServer("nvarchar");
		dataType.setAccess("varchar");
		dataType.setMySql("VARCHAR");
		dataType.setOracle("NVARCHAR2");
		dataType.setDb2("");
		dataType.setIcon("Icons.16x16.DataSource.String.png");
		dataType.setSplitter(false);
		dataType.setCode("String");
		dataType.setName("文本");
		dataType.setJava("java.lang.String");
		dataType.setJdbcType(0);
		dataType.setId(17L);
		dataTypeIds.put(dataType.getId(), dataType);
		
		dataType = new DataType();table.add(dataType);
		DataTypes.Decimal = dataType;
		dataType.setPrecisionDb((short)14);
		dataType.setPrecisionDisplay((short)2);
		dataType.setPrecision(true);
		dataType.setSizeDb((short)28);
		dataType.setSizeDisplay((short)15);
		dataType.setControlType("DecimalBox");
		dataType.setSize(true);
		dataType.setDbType("Decimal");
		dataType.setCsharp("System.Decimal");
		dataType.setSqlServer("Decimal");
		dataType.setAccess("Currency");
		dataType.setMySql("DECIMAL");
		dataType.setOracle("NUMBER");
		dataType.setDb2("");
		dataType.setIcon("Icons.16x16.DataSource.Number.png");
		dataType.setSplitter(true);
		dataType.setCode("Decimal");
		dataType.setName("数值");
		dataType.setJava("java.math.BigDecimal");
		dataType.setJdbcType(0);
		dataType.setId(18L);
		dataTypeIds.put(dataType.getId(), dataType);
		
		dataType = new DataType();table.add(dataType);
		DataTypes.Amount = dataType;
		dataType.setPrecisionDb((short)14);
		dataType.setPrecisionDisplay((short)2);
		dataType.setPrecision(true);
		dataType.setSizeDb((short)28);
		dataType.setSizeDisplay((short)15);
		dataType.setControlType("CurrencyBox");
		dataType.setSize(true);
		dataType.setDbType("Decimal");
		dataType.setCsharp("System.Decimal");
		dataType.setSqlServer("decimal");
		dataType.setAccess("Currency");
		dataType.setMySql("DECIMAL");
		dataType.setOracle("NUMBER");
		dataType.setDb2("");
		dataType.setIcon("Icons.16x16.System.Mony.PNG");
		dataType.setSplitter(true);
		dataType.setCode("Amount");
		dataType.setName("金额");
		dataType.setJava("java.math.BigDecimal");
		dataType.setJdbcType(0);
		dataType.setId(19L);
		dataTypeIds.put(dataType.getId(), dataType);
		
		dataType = new DataType();table.add(dataType);
		DataTypes.Quantity = dataType;
		dataType.setPrecisionDb((short)14);
		dataType.setPrecisionDisplay((short)2);
		dataType.setPrecision(true);
		dataType.setSizeDb((short)28);
		dataType.setSizeDisplay((short)15);
		dataType.setControlType("DecimalBox");
		dataType.setSize(true);
		dataType.setDbType("Decimal");
		dataType.setCsharp("System.Decimal");
		dataType.setSqlServer("decimal");
		dataType.setAccess("Currency");
		dataType.setMySql("DECIMAL");
		dataType.setOracle("NUMBER");
		dataType.setDb2("");
		dataType.setIcon("Icons.16x16.DataSource.Number.png");
		dataType.setSplitter(true);
		dataType.setCode("Quantity");
		dataType.setName("数量");
		dataType.setJava("java.math.BigDecimal");
		dataType.setJdbcType(0);
		dataType.setId(20L);
		dataTypeIds.put(dataType.getId(), dataType);
		
		dataType = new DataType();table.add(dataType);
		DataTypes.Price = dataType;
		dataType.setPrecisionDb((short)14);
		dataType.setPrecisionDisplay((short)2);
		dataType.setPrecision(true);
		dataType.setSizeDb((short)28);
		dataType.setSizeDisplay((short)15);
		dataType.setControlType("CurrencyBox");
		dataType.setSize(true);
		dataType.setDbType("Decimal");
		dataType.setCsharp("System.Decimal");
		dataType.setSqlServer("decimal");
		dataType.setAccess("Currency");
		dataType.setMySql("DECIMAL");
		dataType.setOracle("NUMBER");
		dataType.setDb2("");
		dataType.setIcon("Icons.16x16.System.Mony.PNG");
		dataType.setSplitter(true);
		dataType.setCode("Price");
		dataType.setName("单价");
		dataType.setJava("java.math.BigDecimal");
		dataType.setJdbcType(0);
		dataType.setId(21L);
		dataTypeIds.put(dataType.getId(), dataType);
		
		dataType = new DataType();table.add(dataType);
		DataTypes.ChangeRate = dataType;
		dataType.setPrecisionDb((short)14);
		dataType.setPrecisionDisplay((short)6);
		dataType.setPrecision(true);
		dataType.setSizeDb((short)28);
		dataType.setSizeDisplay((short)15);
		dataType.setControlType("DecimalBox");
		dataType.setSize(true);
		dataType.setDbType("Decimal");
		dataType.setCsharp("System.Decimal");
		dataType.setSqlServer("decimal");
		dataType.setAccess("Currency");
		dataType.setMySql("DECIMAL");
		dataType.setOracle("NUMBER");
		dataType.setDb2("");
		dataType.setIcon("Icons.16x16.DataSource.Number.png");
		dataType.setSplitter(true);
		dataType.setCode("ChangeRate");
		dataType.setName("换算率");
		dataType.setJava("java.math.BigDecimal");
		dataType.setJdbcType(0);
		dataType.setId(22L);
		dataTypeIds.put(dataType.getId(), dataType);
		
		dataType = new DataType();table.add(dataType);
		DataTypes.InvoicePrice = dataType;
		dataType.setPrecisionDb((short)14);
		dataType.setPrecisionDisplay((short)4);
		dataType.setPrecision(true);
		dataType.setSizeDb((short)28);
		dataType.setSizeDisplay((short)15);
		dataType.setControlType("NumberBox");
		dataType.setSize(true);
		dataType.setDbType("Decimal");
		dataType.setCsharp("System.Decimal");
		dataType.setSqlServer("Decimal");
		dataType.setAccess("Currency");
		dataType.setMySql("DECIMAL");
		dataType.setOracle("NUMBER");
		dataType.setDb2("");
		dataType.setIcon("Icons.16x16.System.Mony.PNG");
		dataType.setSplitter(true);
		dataType.setCode("InvoicePrice");
		dataType.setName("发票单价");
		dataType.setJava("java.math.BigDecimal");
		dataType.setJdbcType(0);
		dataType.setId(23L);
		dataTypeIds.put(dataType.getId(), dataType);
		
		dataType = new DataType();table.add(dataType);
		DataTypes.Percent = dataType;
		dataType.setPrecisionDb((short)14);
		dataType.setPrecisionDisplay((short)2);
		dataType.setPrecision(true);
		dataType.setSizeDb((short)28);
		dataType.setSizeDisplay((short)15);
		dataType.setControlType("NumberBox");
		dataType.setSize(true);
		dataType.setDbType("Decimal");
		dataType.setCsharp("System.Decimal");
		dataType.setSqlServer("Decimal");
		dataType.setAccess("Currency");
		dataType.setMySql("DECIMAL");
		dataType.setOracle("NUMBER");
		dataType.setDb2("");
		dataType.setIcon("Icons.16x16.DataSource.Number.png");
		dataType.setSplitter(true);
		dataType.setCode("Percent");
		dataType.setName("百分比");
		dataType.setJava("java.math.BigDecimal");
		dataType.setJdbcType(0);
		dataType.setId(24L);
		dataTypeIds.put(dataType.getId(), dataType);
		
		dataType = new DataType();
		table.add(dataType);
		DataTypes.ExchangeRage = dataType;
		dataType.setPrecisionDb((short)14);
		dataType.setPrecisionDisplay((short)2);
		dataType.setPrecision(true);
		dataType.setSizeDb((short)28);
		dataType.setSizeDisplay((short)15);
		dataType.setControlType("NumberBox");
		dataType.setSize(true);
		dataType.setDbType("Decimal");
		dataType.setCsharp("System.Decimal");
		dataType.setSqlServer("Decimal");
		dataType.setAccess("Currency");
		dataType.setMySql("DECIMAL");
		dataType.setOracle("NUMBER");
		dataType.setDb2("");
		dataType.setIcon("Icons.16x16.DataSource.Number.png");
		dataType.setSplitter(true);
		dataType.setCode("ExchangeRage");
		dataType.setName("汇率");
		dataType.setJava("java.math.BigDecimal");
		dataType.setJdbcType(0);
		dataType.setId(25L);
		dataTypeIds.put(dataType.getId(), dataType);

    }

    private static ArrayList<DataType> table;

    /**
     * @return
     */
    public static ArrayList<DataType> getTable()
    {
    	return table;
    }

    private static HashMap<Long, DataType> dataTypeIds = new HashMap<Long, DataType>();
}