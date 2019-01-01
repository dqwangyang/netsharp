package org.netsharp.action;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.netsharp.core.EntityState;

//action执行的上下文信息
public class ActionContext implements Serializable{
	
	private static final long serialVersionUID = 4118521146984786515L;
	
	//插件路径
	private String path;
	//当前实体
	private Object item;
	//实体持久化状态
	private EntityState state;
	//上下文状态
	private Map<String,Object> status = new HashMap<String,Object>();
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public EntityState getState() {
		return state;
	}
	public void setState(EntityState state) {
		this.state = state;
	}
	public Object getItem() {
		return item;
	}
	public void setItem(Object item) {
		this.item = item;
	}
	public Map<String, Object> getStatus() {
		return status;
	}
	public void setStatus(Map<String, Object> status) {
		this.status = status;
	}
	
	
}
