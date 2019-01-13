package org.netsharp.session;

public interface ISessionManager {
	
	Long getUserId();
	String getUserName();
	String getMobiles();
	Long getPostId();
	String getPostName();
	Long getDepartmentId();
	String getDepartmentPathCode();
	String getDepartmentName();	
	String getDepartmentIds();
	String[] getDepartmentPathCodes();
	Long getTenancyId();
	String getTenancyName();
	
	NetsharpSession getSession();
	
}