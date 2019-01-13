package org.netsharp.entity;

import java.util.Date;

public interface IEntity extends IPersistable {
	
	public Long getId();
	public void setId(Long id);
	
	public Date getCreateTime();
	public void setCreateTime(Date createTime);
	
	public Date getUpdateTime();
	public void setUpdateTime(Date updateTime);
	
	public String getCreator() ;
	public void setCreator(String creator);
	
	public String getUpdator() ;
	public void setUpdator(String updator);
	
	public Long getCreatorId() ;
	public void setCreatorId(Long idCreator);
	
	public Long getUpdatorId();
	public void setUpdatorId(Long idUpdator) ;
	
//	public Date getTs();
//	public void setTs(Date ts);
	
}
