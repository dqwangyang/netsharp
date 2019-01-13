package org.netsharp.scrum.entity;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

@Table(name="scrum_bug_log",header="缺陷记录")
public class BugLog extends Entity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -140405091305208370L;
	private String content;
	
	@Column(name="bug_id",header="缺陷Id")
	private Long bugId;
	
	@JsonBackReference
	@Reference(foreignKey="bugId")
	private Bug bug;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getBugId() {
		return bugId;
	}

	public void setBugId(Long bugId) {
		this.bugId = bugId;
	}

	public Bug getBug() {
		return bug;
	}

	public void setBug(Bug bug) {
		this.bug = bug;
	}
}
