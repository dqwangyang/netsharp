package org.netsharp.job.core;

/*
 * 作业接口，所有作业任务需实现此接口
 * */
public interface IJob {
	
	void execute(String par);
}
