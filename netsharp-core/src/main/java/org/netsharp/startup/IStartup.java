package org.netsharp.startup;

/*webserver启动时启动项接口*/
public interface IStartup {
	
	/*启动项启动条件*/
	boolean startCondition();
	
	/*执行启动项*/
	void startup();
	
	/*启动项注销*/
	void shutdown();
	
	/*启动项名称*/
	String getName();
}
