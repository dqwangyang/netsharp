package org.netsharp.entity;

import java.util.Date;

public interface IEntity extends IPersistable {
	
	public Integer getId();
	public void setId(Integer id);
	
	public Date getCreateTime();
	public void setCreateTime(Date createTime);
	
	public Date getUpdateTime();
	public void setUpdateTime(Date updateTime);
	
	public String getCreator() ;
	public void setCreator(String creator);
	
	public String getUpdator() ;
	public void setUpdator(String updator);
	
	public Integer getCreatorId() ;
	public void setCreatorId(Integer idCreator);
	
	public Integer getUpdatorId();
	public void setUpdatorId(Integer idUpdator) ;
	
//	public Date getTs();
//	public void setTs(Date ts);
	
}
