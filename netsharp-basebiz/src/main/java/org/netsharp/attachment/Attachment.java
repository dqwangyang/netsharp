package org.netsharp.attachment;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.BizEntity;

//附件
@Table(name = "sys_attachment")
public class Attachment extends BizEntity {

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -5735611613454870506L;

//	@Column(name = "content", header = "附件内容")
//	protected byte content;

	@Column(name = "path", header = "路径")
	protected String path;

	@Column(name = "entity_id", header = "实体名称")
	protected String entityId;

	@Column(name = "table_name", header = "表名称")
	protected String tableName;

	@Column(name = "file_extend", header = "文件扩展名")
	protected String fileExtend;

	@Column(name = "view_count", header = "查看次数")
	protected Integer viewCount;

	@Column(name = "downLoad_count", header = "下载次数")
	protected Integer downLoadCount;

	@Column(name = "foreign_key", header = "外键")
	protected Integer foreignKey;

	@Column(name = "alias", header = "别名")
	protected String alias;

//	public byte getContent() {
//		return this.content;
//	}
//
//	public Attachment setContent(byte content) {
//		this.content = content;
//		return this;
//	}

	public String getPath() {
		return this.path;
	}

	public Attachment setPath(String path) {
		this.path = path;
		return this;
	}

	public String getEntityId() {
		return this.entityId;
	}

	public Attachment setEntityId(String entityId) {
		this.entityId = entityId;
		return this;
	}

	public String getTableName() {
		return this.tableName;
	}

	public Attachment setTableName(String tableName) {
		this.tableName = tableName;
		return this;
	}

	public String getFileExtend() {
		return this.fileExtend;
	}

	public Attachment setFileExtend(String fileExtend) {
		this.fileExtend = fileExtend;
		return this;
	}

	public Integer getViewCount() {
		return this.viewCount;
	}

	public Attachment setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
		return this;
	}

	public Integer getDownLoadCount() {
		return this.downLoadCount;
	}

	public Attachment setDownLoadCount(Integer downLoadCount) {
		this.downLoadCount = downLoadCount;
		return this;
	}

	public Integer getForeignKey() {
		return this.foreignKey;
	}

	public Attachment setForeignKey(Integer foreignKey) {
		this.foreignKey = foreignKey;
		return this;
	}

	public String getAlias() {
		return this.alias;
	}

	public Attachment setAlias(String alias) {
		this.alias = alias;
		return this;
	}
}