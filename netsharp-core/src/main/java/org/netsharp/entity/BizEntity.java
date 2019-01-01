package org.netsharp.entity;

import org.netsharp.core.annotations.Column;

/*业务实体:包括编码、名称、备注三个字段*/
public abstract class BizEntity extends Entity {

    private static final long serialVersionUID = 5573301840301273889L;
    
    @Column(name = "code",header="编码")
    private String code;
    
    @Column(name = "name",header="名称", size = 200)
    private String name;
    
    @Column(name = "memoto",header="备注", size = 1000)
    private String memoto;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemoto() {
        return memoto;
    }

    public void setMemoto(String memoto) {
        this.memoto = memoto;
    }
}
