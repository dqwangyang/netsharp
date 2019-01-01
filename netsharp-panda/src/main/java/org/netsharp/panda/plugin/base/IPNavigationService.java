package org.netsharp.panda.plugin.base;

import org.netsharp.base.IPersistableService;
import org.netsharp.panda.plugin.entity.PNavigation;

public interface IPNavigationService extends IPersistableService<PNavigation> {

	/**   
	 * @Title: byPath   
	 * @Description: 根据路径获取树
	 * @param: @param path
	 * @param: @return      
	 * @return: PTree      
	 * @throws   
	 */
	PNavigation byPath(String path);
}
