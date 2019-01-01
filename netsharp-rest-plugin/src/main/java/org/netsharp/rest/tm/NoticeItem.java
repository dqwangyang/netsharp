package org.netsharp.rest.tm;

import java.util.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Exclusive;
import org.netsharp.core.annotations.Id;
import org.netsharp.core.annotations.Table;
import org.netsharp.core.id.SnowflakeId;
import org.netsharp.entity.Persistable;

/*
page_no	1
tm_name	WEIR VALVES CONTROLS
ann_type_code	TMZCSQ
tmname	WEIR VALVES CONTROLS
reg_name	伟尔工程服务有限公司
ann_type	商标初步审定公告
ann_num	1600
reg_num	5745343
id	e48b9208632afadb0163685d146c364c
rn	1
ann_date	2018-05-20
regname	伟尔工程服务有限公司
*/

@Table(name="ged_tm_notice_item",header="商标公告-商标",idType=SnowflakeId.class)
public class NoticeItem extends Persistable {
	
	private static final long serialVersionUID = -5215581038303650226L;
	
	@Id
//	@Auto
	private Long uid;
	
	@Column(header="GUID")
	private String id;
	@Column(header="序号")
	private Integer rn;
	
	@Column(header="页码")//每页20条
	private Integer page_no;
	
//	@Column(header="商标名称",size=500)
	@Exclusive
	private String tm_name;
	@Column(header="商标名称",size=500)
	private String tmname;
	
	
	@Column(header="公告日期")
	private Date ann_date;
	@Column(header="公告类型")
	private String ann_type;
	@Column(header="公告期号")
	private Integer ann_num;
	@Column(header="公告类型编码")
	private String ann_type_code;
	
	@Column(header="注册号")
	private Long reg_num;
//	@Column(header="申请人",size=1000)
	@Exclusive
	private String reg_name;
	@Column(header="申请人",size=1000)
	private String regname;
	
	@Column(name="int_cls",size=500,header="商标大类,从1到45,多个商标时逗号隔开")
	private String intCls;
	
//	@Column(name="int_cls_count",header="商标大类个数")
//	private Integer intClsCount;
	
//	@Subs(foreignKey="tmId",subType=NoticeItemL1.class,header="商标大类")
//	private List<NoticeItemL1> level1s;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getRn() {
		return rn;
	}
	public void setRn(Integer rn) {
		this.rn = rn;
	}
	public Integer getPage_no() {
		return page_no;
	}
	public void setPage_no(Integer page_no) {
		this.page_no = page_no;
	}
	public String getTm_name() {
		return tm_name;
	}
	public void setTm_name(String tm_name) {
		this.tm_name = tm_name;
	}
	public String getTmname() {
		return tmname;
	}
	public void setTmname(String tmname) {
		this.tmname = tmname;
	}
	public Date getAnn_date() {
		return ann_date;
	}
	public void setAnn_date(Date ann_date) {
		this.ann_date = ann_date;
	}
	public String getAnn_type() {
		return ann_type;
	}
	public void setAnn_type(String ann_type) {
		this.ann_type = ann_type;
	}
	public Integer getAnn_num() {
		return ann_num;
	}
	public void setAnn_num(Integer ann_num) {
		this.ann_num = ann_num;
	}
	public String getAnn_type_code() {
		return ann_type_code;
	}
	public void setAnn_type_code(String ann_type_code) {
		this.ann_type_code = ann_type_code;
	}
	public Long getReg_num() {
		return reg_num;
	}
	public void setReg_num(Long reg_num) {
		this.reg_num = reg_num;
	}
	public String getReg_name() {
		return reg_name;
	}
	public void setReg_name(String reg_name) {
		this.reg_name = reg_name;
	}
	public String getRegname() {
		return regname;
	}
	public void setRegname(String regname) {
		this.regname = regname;
	}
	public String getIntCls() {
		return intCls;
	}
	public void setIntCls(String intCls) {
		this.intCls = intCls;
	}
//	public Integer getIntClsCount() {
//		return intClsCount;
//	}
//	public void setIntClsCount(Integer intClsCount) {
//		this.intClsCount = intClsCount;
//	}
//	public List<NoticeItemL1> getLevel1s() {
//		if(this.level1s==null) {
//			this.level1s = new ArrayList<NoticeItemL1>();
//		}
//		return level1s;
//	}
//	public void setLevel1s(List<NoticeItemL1> level1s) {
//		this.level1s = level1s;
//	}
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
}
