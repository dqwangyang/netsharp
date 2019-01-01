package org.netsharp.panda.base;

import java.util.List;

import org.netsharp.base.IPersistableService;
import org.netsharp.cache.base.annotation.EntityCache;
import org.netsharp.resourcenode.entity.Plugin;

@EntityCache(prefix="plugin")
public interface IPluginService extends IPersistableService<Plugin> {
	/*导出插件sql脚本*/
	List<String> export(Integer id);
}
