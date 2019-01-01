package org.netsharp.communication.provider;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.communication.core.CommunicationException;
import org.netsharp.communication.core.InvokeRequest;
import org.netsharp.communication.core.InvokeResponse;
import org.netsharp.session.ThreadContext;

public class SiContext extends ThreadContext {

	private static final ThreadLocal<SiContext> threadLocal = new ThreadLocal<SiContext>();

	private List<SiContext> subs;

	public String Name; // 接口名称.方法名称
	public Class<?> InterfaceType; // 服务接口类型
	public Object ServiceObject; // 服务对象
	public Method Method; // 服务方法
	public Method InterfaceMethod; // 接口方法声明

	public SiContext Top; // 本次服务调用的顶级调用
	public boolean IsTransaction; // 服务是否启用了数据库事务
	public SiContext Parent; // 调用者，记录同一个线程内服务的调用关系
	public boolean IsCached; // 命中缓存

	public InvokeRequest Request;
	public InvokeResponse Response;

	// 当前调用的子级调用
	// 记录同一个线程内服务的调用关系
	public List<SiContext> getSubs() {

		if (this.subs == null) {
			this.subs = new ArrayList<SiContext>();
		}
		return this.subs;
	}

	public static SiContext getCurrent() {

		return threadLocal.get();
	}

	public static void setCurrent(SiContext ctx) {

		if (ctx == null) {
			throw new CommunicationException("SiContext不能设置为空");
		}

		threadLocal.set(ctx);
	}

	public static void clean() {
		threadLocal.remove();
	}

	@Override
	public String toString() {
		return this.Name;
	}
}
