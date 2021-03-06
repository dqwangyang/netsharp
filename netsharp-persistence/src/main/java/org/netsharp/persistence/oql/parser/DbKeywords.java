package org.netsharp.persistence.oql.parser;

    //[StructLayout(LayoutKind.Sequential, Size = 1)]
    public class DbKeywords
    {
        public static String[] SQLServerWordsnew=new String[]
    			{
			"ADD",
			"EXCEPT",
			"PERCENT",
			"ALL",
			"EXEC",
			"PLAN",
			"ALTER",
			"EXECUTE",
			"PRECISION",
			"AND",
			"EXISTS",
			"PRIMARY",
			"ANY",
			"EXIT",
			"PRINT",
			"AS",
			"FETCH",
			"PROC",
			"ASC",
			"FILE",
			"PROCEDURE",
			"AUTHORIZATION",
			"FILLFACTOR",
			"PUBLIC",
			"BACKUP",
			"FOR",
			"RAISERROR",
			"BEGIN",
			"FOREIGN",
			"READ",
			"BETWEEN",
			"FREETEXT",
			"READTEXT",
			"BREAK",
			"FREETEXTTABLE",
			"RECONFIGURE",
			"BROWSE",
			"FROM",
			"REFERENCES",
			"BULK",
			"FULL",
			"REPLICATION",
			"BY",
			"FUNCTION",
			"RESTORE",
			"CASCADE",
			"GOTO",
			"RESTRICT",
			"CASE",
			"GRANT",
			"RETURN",
			"CHECK",
			"GROUP",
			"REVOKE",
			"CHECKPOINT",
			"HAVING",
			"RIGHT",
			"CLOSE",
			"HOLDLOCK",
			"ROLLBACK",
			"CLUSTERED",
			"IDENTITY",
			"ROWCOUNT",
			"COALESCE",
			"IDENTITY_INSERT",
			"ROWGUIDCOL",
			"COLLATE",
			"IDENTITYCOL",
			"RULE",
			"COLUMN",
			"IF",
			"SAVE",
			"COMMIT",
			"IN",
			"SCHEMA",
			"COMPUTE",
			"INDEX",
			"SELECT",
			"CONSTRAINT",
			"INNER",
			"SESSION_USER",
			"CONTAINS",
			"INSERT",
			"SET",
			"CONTAINSTABLE",
			"INTERSECT",
			"SETUSER",
			"CONTINUE",
			"INTO",
			"SHUTDOWN",
			"CONVERT",
			"IS",
			"SOME",
			"CREATE",
			"JOIN",
			"STATISTICS",
			"CROSS",
			"KEY",
			"SYSTEM_USER",
			"CURRENT",
			"KILL",
			"TABLE",
			"CURRENT_DATE",
			"LEFT",
			"TEXTSIZE",
			"CURRENT_TIME",
			"LIKE",
			"THEN",
			"CURRENT_TIMESTAMP",
			"LINENO",
			"TO",
			"CURRENT_USER",
			"LOAD",
			"TOP",
			"CURSOR",
			"NATIONAL",
			"TRAN",
			"DATABASE",
			"NOCHECK",
			"TRANSACTION",
			"DBCC",
			"NONCLUSTERED",
			"TRIGGER",
			"DEALLOCATE",
			"NOT",
			"TRUNCATE",
			"DECLARE",
			"NULL",
			"TSEQUAL",
			"DEFAULT",
			"NULLIF",
			"UNION",
			"DELETE",
			"OF",
			"UNIQUE",
			"DENY",
			"OFF",
			"UPDATE",
			"DESC",
			"OFFSETS",
			"UPDATETEXT",
			"DISK",
			"ON",
			"USE",
			"DISTINCT",
			"OPEN",
			"USER",
			"DISTRIBUTED",
			"OPENDATASOURCE",
			"VALUES",
			"DOUBLE",
			"OPENQUERY",
			"VARYING",
			"DROP",
			"OPENROWSET",
			"VIEW",
			"DUMMY",
			"OPENXML",
			"WAITFOR",
			"DUMP",
			"OPTION",
			"WHEN",
			"ELSE",
			"OR",
			"WHERE",
			"END",
			"ORDER",
			"WHILE",
			"ERRLVL",
			"OUTER",
			"WITH",
			"ESCAPE",
			"OVER",
			"WRITETEXT",
			"ABSOLUTE",
			"FOUND",
			"PRESERVE",
			"ACTION",
			"FREE",
			"PRIOR",
			"ADMIN",
			"GENERAL",
			"PRIVILEGES",
			"AFTER",
			"GET",
			"READS",
			"AGGREGATE",
			"GLOBAL",
			"REAL",
			"ALIAS",
			"GO",
			"RECURSIVE",
			"ALLOCATE",
			"GROUPING",
			"REF",
			"ARE",
			"HOST",
			"REFERENCING",
			"ARRAY",
			"HOUR",
			"RELATIVE",
			"ASSERTION",
			"IGNORE",
			"RESULT",
			"AT",
			"IMMEDIATE",
			"RETURNS",
			"BEFORE",
			"INDICATOR",
			"ROLE",
			"BINARY",
			"INITIALIZE",
			"ROLLUP",
			"BIT",
			"INITIALLY",
			"ROUTINE",
			"BLOB",
			"INOUT",
			"ROW",
			"BOOLEAN",
			"INPUT",
			"ROWS",
			"BOTH",
			"INT",
			"SAVEPOINT",
			"BREADTH",
			"INTEGER",
			"SCROLL",
			"CALL",
			"INTERVAL",
			"SCOPE",
			"CASCADED",
			"ISOLATION",
			"SEARCH",
			"CAST",
			"ITERATE",
			"SECOND",
			"CATALOG",
			"LANGUAGE",
			"SECTION",
			"CHAR",
			"LARGE",
			"SEQUENCE",
			"CHARACTER",
			"LAST",
			"SESSION",
			"CLASS",
			"LATERAL",
			"SETS",
			"CLOB",
			"LEADING",
			"SIZE",
			"COLLATION",
			"LESS",
			"SMALLINT",
			"COMPLETION",
			"LEVEL",
			"SPACE",
			"CONNECT",
			"LIMIT",
			"SPECIFIC",
			"CONNECTION",
			"LOCAL",
			"SPECIFICTYPE",
			"CONSTRAINTS",
			"LOCALTIME",
			"SQL",
			"CONSTRUCTOR",
			"LOCALTIMESTAMP",
			"NetsharpException",
			"CORRESPONDING",
			"LOCATOR",
			"SQLSTATE",
			"CUBE",
			"MAP",
			"SQLWARNING",
			"CURRENT_PATH",
			"MATCH",
			"START",
			"CURRENT_ROLE",
			"MINUTE",
			"STATE",
			"CYCLE",
			"MODIFIES",
			"STATEMENT",
			"DATA",
			"MODIFY",
			"STATIC",
			"DATE",
			"MODULE",
			"STRUCTURE",
			"DAY",
			"MONTH",
			"TEMPORARY",
			"DEC",
			"NAMES",
			"TERMINATE",
			"DECIMAL",
			"NATURAL",
			"THAN",
			"DEFERRABLE",
			"NCHAR",
			"TIME",
			"DEFERRED",
			"NCLOB",
			"TIMESTAMP",
			"DEPTH",
			"NEW",
			"TIMEZONE_HOUR",
			"DEREF",
			"NEXT",
			"TIMEZONE_MINUTE",
			"DESCRIBE",
			"NO",
			"TRAILING",
			"DESCRIPTOR",
			"NONE",
			"TRANSLATION",
			"DESTROY",
			"NUMERIC",
			"TREAT",
			"DESTRUCTOR",
			"OBJECT",
			"TRUE",
			"DETERMINISTIC",
			"OLD",
			"UNDER",
			"DICTIONARY",
			"ONLY",
			"UNKNOWN",
			"DIAGNOSTICS",
			"OPERATION",
			"UNNEST",
			"DISCONNECT",
			"ORDINALITY",
			"USAGE",
			"DOMAIN",
			"OUT",
			"USING",
			"DYNAMIC",
			"OUTPUT",
			"VALUE",
			"EACH",
			"PAD",
			"VARCHAR",
			"END-EXEC",
			"PARAMETER",
			"VARIABLE",
			"EQUALS",
			"PARAMETERS",
			"WHENEVER",
			"EVERY",
			"PARTIAL",
			"WITHOUT",
			"EXCEPTION",
			"PATH",
			"WORK",
			"EXTERNAL",
			"POSTFIX",
			"WRITE",
			"FLASE",
			"PREFIX",
			"YEAR",
			"FIRST",
			"PREORDER",
			"ZONE",
			"FLOAT",
			"PREPARE"
		};
        
        public static String[] MYSqlWords = new String[]
		{
			"ADD",
			"ALL",
			"ALTER",
			"ANALYZE",
			"AND",
			"AS",
			"ASC",
			"AUTO_INCREMENT",
			"BDB",
			"BEFORE",
			"BERKELEYDB",
			"BETWEEN",
			"BIGINT",
			"BINARY",
			"BLOB",
			"BOTH",
			"BTREE",
			"BY",
			"CASCADE",
			"CASE",
			"CHANGE",
			"CHAR",
			"CHARACTER",
			"CHECK",
			"COLLATE",
			"COLUMN",
			"COLUMNS",
			"CONSTRAINT",
			"CREATE",
			"CROSS",
			"CURRENT_DATE",
			"CURRENT_TIME",
			"CURRENT_TIMESTAMP",
			"DATABASE",
			"DATABASES",
			"DAY_HOUR",
			"DAY_MINUTE",
			"DAY_SECOND",
			"DEC",
			"DECIMAL",
			"DEFAULT",
			"DELAYED",
			"DELETE",
			"DESC",
			"DESCRIBE",
			"DISTINCT",
			"DISTINCTROW",
			"DIV",
			"DOUBLE",
			"DROP",
			"ELSE",
			"ENCLOSED",
			"ERRORS",
			"ESCAPED",
			"EXISTS",
			"EXPLAIN",
			"FALSE",
			"FIELDS",
			"FLOAT",
			"FOR",
			"FORCE",
			"FOREIGN",
			"FROM",
			"FULLTEXT",
			"FUNCTION",
			"GRANT",
			"GROUP",
			"HASH",
			"HAVING",
			"HIGH_PRIORITY",
			"HOUR_MINUTE",
			"HOUR_SECOND",
			"IF",
			"IGNORE",
			"IN",
			"INDEX",
			"INFILE",
			"INNER",
			"INNODB",
			"INSERT",
			"INT",
			"INTEGER",
			"INTERVAL",
			"INTO",
			"IS",
			"JOIN",
			"KEY",
			"KEYS",
			"KILL",
			"LEADING",
			"LEFT",
			"LIKE",
			"LIMIT",
			"LINES",
			"LOAD",
			"LOCALTIME",
			"LOCALTIMESTAMP",
			"LOCK",
			"LONG",
			"LONGBLOB",
			"LONGTEXT",
			"LOW_PRIORITY",
			"MASTER_SERVER_ID",
			"MATCH",
			"MEDIUMBLOB",
			"MEDIUMINT",
			"MEDIUMTEXT",
			"MIDDLEINT",
			"MINUTE_SECOND",
			"MOD",
			"MRG_MYISAM",
			"NATURAL",
			"NOT",
			"NULL",
			"NUMERIC",
			"ON",
			"OPTIMIZE",
			"OPTION",
			"OPTIONALLY",
			"OR",
			"ORDER",
			"OUTER",
			"OUTFILE",
			"PRECISION",
			"PRIMARY",
			"PRIVILEGES",
			"PROCEDURE",
			"PURGE",
			"READ",
			"REAL",
			"REFERENCES",
			"REGEXP",
			"RENAME",
			"REPLACE",
			"REQUIRE",
			"RESTRICT",
			"RETURNS",
			"REVOKE",
			"RIGHT",
			"RLIKE",
			"RTREE",
			"SELECT",
			"SET",
			"SHOW",
			"SMALLINT",
			"SOME",
			"SONAME",
			"SPATIAL",
			"SQL_BIG_RESULT",
			"SQL_CALC_FOUND_ROWS",
			"SQL_SMALL_RESULT",
			"SSL",
			"STARTING",
			"STRAIGHT_JOIN",
			"STRIPED",
			"TABLE",
			"TABLES",
			"TERMINATED",
			"THEN",
			"TINYBLOB",
			"TINYINT",
			"TINYTEXT",
			"TO",
			"TRAILING",
			"TRUE",
			"TYPES",
			"UNION",
			"UNIQUE",
			"UNLOCK",
			"UNSIGNED",
			"UPDATE",
			"USAGE",
			"USE",
			"USER_RESOURCES",
			"USING",
			"VALUES",
			"VARBINARY",
			"VARCHAR",
			"VARCHARACTER",
			"VARYING",
			"WARNINGS",
			"WHEN",
			"WHERE",
			"WITH",
			"WRITE",
			"XOR",
			"YEAR_MONTH",
			"ZEROFILL"
		};

        public static String[] CsharpWords= new String[]
    			{
			"abstract",
			"event",
			"new",
			"struct",
			"as",
			"explicit",
			"null",
			"switch",
			"base",
			"extern",
			"object",
			"this",
			"Boolean",
			"false",
			"operator",
			"throw",
			"break",
			"finally",
			"out",
			"true",
			"byte",
			"fixed",
			"overrid",
			"",
			"try",
			"case",
			"float",
			"params",
			"typeof",
			"catch",
			"for",
			"private",
			"uint",
			"char",
			"for",
			"protected",
			"ulong",
			"checked",
			"goto",
			"public",
			"unchecked",
			"class",
			"if",
			"readonly",
			"unsafe",
			"const",
			"implicit",
			"ref",
			"ushort",
			"continue",
			"in",
			"return",
			"using",
			"BigDecimal",
			"int",
			"sbyte",
			"virtual",
			"default",
			"interface",
			"sealed",
			"volatile",
			"delegate",
			"public",
			"short",
			"void",
			"do",
			"is",
			"sizeof",
			"while",
			"double",
			"lock",
			"stackalloc",
			"else",
			"long",
			"static",
			"enum",
			"namespace",
			"String",
			"get",
			"partial",
			"set",
			"value",
			"where",
			"yield"
		};;
    }
