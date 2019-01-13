package org.netsharp.entity;

import java.util.Date;

import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.annotations.Auto;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Id;

/**
 * 默认实体，包括id，创建人，修改人、时间戳字段
 */
public abstract class Entity extends Persistable implements IEntity {

	private static final long serialVersionUID = -3763283870698919626L;

	@Id
	@Auto
	@Column(name = "id", header = "主键")
	private Long id;

	@Column(name = "creator_id", header = "创建人ID")
	private Long creatorId;

	@Column(name = "creator", header = "创建人名称")
	private String creator;

	@Column(name = "create_time", header = "创建时间")
	private Date createTime;

	@Column(name = "updator_id", header = "更新人ID")
	private Long updatorId;

	@Column(name = "updator", header = "更新人名称")
	private String updator;

	@Column(name = "update_time", header = "更新时间")
	private Date updateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdator() {
		return updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public Long getUpdatorId() {
		return updatorId;
	}

	public void setUpdatorId(Long updatorId) {
		this.updatorId = updatorId;
	}

	public boolean hasId() {
		Mtable meta = MtableManager.getMtable(this.getClass());
		return !meta.getId().isEmpty(this.id);
	}
}
