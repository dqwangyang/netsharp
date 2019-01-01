package org.netsharp.log.entity;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.BizEntity;

//NLog
@Table(name="sys_log",header="操作日志")
public class NLog extends BizEntity{

	private static final long serialVersionUID = 8355969975978079701L;
	
	@Column(name="operaiton_name",header="操作名称")
	private String operaitonName;
	
	@Column(size=1000)
    private String message;               //Message
	
	@Column(name="associate_id",header="关联对象ID")
    private String associateId;           //关联对象ID
    
    @Column(name="entity_id",size=100,header="实体")
    private String entityId;  
    
    @Column(name="log_type",header="日志类型")
    private NLogType logType;
    
    @Column(name="table_name",header="数据库表名")
    private String tableName;

	public String getMessage(){
        return this.message;
    }
    public NLog setMessage(String message){
        this.message=message;
        return this;
    }
    public String getAssociateId(){
        return this.associateId;
    }
    public NLog setAssociateId(String associateId){
        this.associateId=associateId;
        return this;
    }
    public String getEntityId(){
        return this.entityId;
    }
    public NLog setEntityId(String entityId){
        this.entityId=entityId;
        return this;
    }

	public NLogType getLogType() {
		return logType;
	}

	public void setLogType(NLogType logType) {
		this.logType = logType;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getOperaitonName() {
		return operaitonName;
	}
	public void setOperaitonName(String operaiton) {
		this.operaitonName = operaiton;
	}
}