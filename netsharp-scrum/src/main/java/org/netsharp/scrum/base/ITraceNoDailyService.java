package org.netsharp.scrum.base;

import org.netsharp.base.IPersistableService;
import org.netsharp.scrum.entity.TraceNoDaily;

public interface ITraceNoDailyService extends IPersistableService<TraceNoDaily> {
	
	void generate(int year,int month,int day);
	void generate(int year,int month);
	void generateToday();
	void generateYestoday();
}
