package org.netsharp.communication.consumer;

public interface IClientFilter {
	/**
	 * 拦截前置处理
	 * @param ctx 服务调用上下文
	 */
	void invoking(CiContext ctx);
	
	
    /** 拦截后置处理
     * @param ctx 服务调用上下文
     */
    void invoked(CiContext ctx);
}
