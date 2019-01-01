package org.netsharp.wx.pa.entity;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

@Table(name = "wx_pa_article")
public class NArticle extends NLinkBase {

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 4254667411870211851L;

	@Column(name = "url", header = "地址")
    private String  url;
	
	@Column(name = "header", header = "标题")
    private String  header;
	
	@Column(name = "description",size = 1000, header = "介绍")
    private String  description;
	
	@Column(name = "image_url",size = 500,header = "图片地址")
    private String  imageUrl;
	
	@Column(name = "reply_id", header = "回复Id")
    private Integer replyId;
	
	@Column(name = "attatch_fid", header = "attatchFid")
    private Boolean attatchFid = false;
	
	@Column(name = "attatch_pid", header = "attatchPid")
    private Boolean attatchPid = false;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getAttatchFid() {
        return attatchFid;
    }

    public void setAttatchFid(Boolean attatchFid) {
        this.attatchFid = attatchFid;
    }

    public Boolean getAttatchPid() {
        return attatchPid;
    }

    public void setAttatchPid(Boolean attatchPid) {
        this.attatchPid = attatchPid;
    }

	public Integer getReplyId() {
		return replyId;
	}

	public void setReplyId(Integer replyId) {
		this.replyId = replyId;
	}
}
