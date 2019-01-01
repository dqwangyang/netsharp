package org.netsharp.session;

public interface ISessionManager {
	
	Integer getUserId();
	String getUserName();
	String getMobiles();
	Integer getPostId();
	String getPostName();
	Integer getDepartmentId();
	String getDepartmentPathCode();
	String getDepartmentName();	
	String getDepartmentIds();
	String[] getDepartmentPathCodes();
	Integer getTenancyId();
	String getTenancyName();
	
	NetsharpSession getSession();
	
}