package org.netsharp.scrum.base;

import org.netsharp.base.IPersistableService;
import org.netsharp.scrum.entity.IteratorStatistics;

public interface IIteratorStatisticsService extends IPersistableService<IteratorStatistics> {

	/**
	 * 生成报表数据
	 */
	void run();
}
