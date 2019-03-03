package org.netsharp.cache.service.db;

import java.util.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Id;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Persistable;

/*数据库缓存时的缓存对象*/
@Table(name = "sys_cache_item")
public class CacheItem extends Persistable {

	private static final long serialVersionUID = 1365000849669061565L;

	@Id
	@Column(size = 200)
	private String id;// 缓存key

	@Column(size = 4000)
	// 暂时只有微信的token需要缓存，使用varchar，根据需要可以改造成Text或者Blocb类型
	private byte[] value;// 缓存值
	
	private String type;//overtime
	
	private Integer timout=5000;//秒
	
	@Column(name="start_time")
	private Date startTime;
	
	@Column(name="end_time")
	private Date endTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public byte[] getValue() {
		return value;
	}
	public void setValue(byte[] value) {
		this.value = value;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getTimout() {
		return timout;
	}
	public void setTimout(Integer timout) {
		this.timout = timout;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}
