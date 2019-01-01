package org.netsharp.plugin.entity;

import java.util.List;

import org.netsharp.core.annotations.Column;
import org.netsharp.resourcenode.entity.ResourceBizEntity;

//插件路径
public abstract class Pathable extends ResourceBizEntity{

	private static final long serialVersionUID = -7327985953251412517L;
	
	private String path;        // 扩展点
	
	@Column(name="base_path")
    private String basePath;    // 基类扩展点

    protected Pathable(){
        super();
    }
    
    public abstract List<Codonable> getCodons();
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getBasePath() {
		return basePath;
	}
	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}
}