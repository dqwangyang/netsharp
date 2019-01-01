package org.netsharp.core;


/// <summary>
/// 实体状态
/// </summary>
public enum EntityState
{
	 /// <summary>
    /// 瞬时状态,不受数据库管理的状态
    /// 或者是事务提交后不需要更新的实体
    /// </summary>
    Transient("瞬时"),
    /// <summary>
    /// 事务提交后将被新增
    /// </summary>
    New("新增"),

    /// <summary>
    /// 事务提交后将被修更新
    /// </summary>
    Persist("修改"),

    /// <summary>
    /// 事务提交后将被删除
    /// </summary>
    Deleted("删除");
    
	private String text;
	private EntityState(String text) {
		this.text = text;
	}

	public String getText() {
		return this.text;
	}
}
