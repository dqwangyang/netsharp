package org.netsharp.persistence.sql;

/// <summary>
/// ISqlGenerator Summary。
/// </summary>
public enum SqlGeneratorType {
	SimpleSelect, FullSelect, CompositeSelect, Ts, SelectByKey, Insert, Update, Delete, StoredProcedure, IsExsit
}