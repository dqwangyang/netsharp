package org.netsharp.resourcenode;

import java.util.List;

import org.netsharp.base.IPersistableService;
import org.netsharp.resourcenode.entity.ResourceNode;

public interface IResourceNodeService extends IPersistableService<ResourceNode> {
	
    ResourceNode byCode(String code);
    
	/*导出资源相关的元数据脚本*/
    List<String> export(Long resourceNodeId);
    
}
