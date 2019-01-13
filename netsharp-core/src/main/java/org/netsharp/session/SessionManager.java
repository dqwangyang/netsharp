package org.netsharp.session;

/*
netsharp中对session的设计，主要是用户身份确认的设计

对于session的处理主要分成两种场景
1.web服务器的session
   要跟serverlet的的session保持一致，并考虑分布式缓存
2.rpc和数据库处理时候的session
  不维护session的状态，每次rpc的请求都把web的session信息传递到rpc的服务端

对于这两种的session的处理都是平台封装的，对于开发者而言，他们使用的方法是一样的，如下所示：
Object userId = SessionManager.getUserId();
Object departmentId = SessionManager.getDepartmentId();

netsharp封装了实现的细节，那么这些细节是什么呢？

 * */

/* rpc以下session管理器
 * 如果tcp调用则内部实现为：RpcSessionManager
 * 如果local调用则内部实现为：PandaSessionManager
 * */
public class SessionManager{

	private static ISessionManager manager;

	public static void initialize(ISessionManager sm) {
		manager = sm;
	}

	public static Long getUserId() {
		if(manager == null){
			return  null;
		}
		else {
			return manager.getUserId();
		}
	}

	public static String getUserName() {
		if(manager == null){
			return  null;
		}
		else {
			return manager.getUserName();
		}
	}
	
	public static String getMobile() {
		if(manager == null){
			return  null;
		}
		else {
			return manager.getMobiles();
		}
	}
	
	public static Long getPostId()
	{
		if(manager == null){
			return  null;
		}
		else {
			return manager.getPostId();
		}
	}
	public static  String getPostName()
	{
		if(manager == null){
			return  null;
		}
		else {
			return manager.getPostName();
		}
	}
	
	public static Long getDepartmentId()
	{
		if(manager == null){
			return  null;
		}
		else {
			return manager.getDepartmentId();
		}
	}
	
	public static String getDepartmentName()
	{
		if(manager == null){
			return  null;
		}
		else {
			return manager.getDepartmentName();
		}
	}
	
	public static String getDepartmentIds() {
		if(manager == null){
			return  null;
		}
		else {
			return manager.getDepartmentIds();
		}
	}
	
	public static  String getDepartmentPathCode()
	{
		if(manager == null){
			return  null;
		}
		else {
			return manager.getDepartmentPathCode();
		}
	}
	public static String[] getDepartmentPathCodes()
	{
		if(manager == null){
			return  null;
		}
		else {
			return manager.getDepartmentPathCodes();
		}
	}
	
	public static Long getTenancyId()
	{
		if(manager == null){
			return  null;
		}
		else {
			return manager.getTenancyId();
		}
	}
	
	public static String getTenancyName()
	{
		if(manager == null){
			return  null;
		}
		else {
			return manager.getTenancyName();
		}
	}
	
	public static NetsharpSession getSession()
	{
		if(manager == null){
			return  null;
		}
		else {
			return manager.getSession();
		}
	}
}
