package org.netsharp.communication.registry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.application.Application;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.communication.core.CommunicationException;
import org.netsharp.entity.IPersistable;
import org.netsharp.util.PakcageManage;
import org.netsharp.util.StringManager;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class ServiceRegistry {

	private static Log logger = LogFactory.getLog(ServiceFactory.class);
	private static boolean isInitialized = false;
	private static HashMap<Class<?>, Class<?>> map = new HashMap<Class<?>, Class<?>>();
	private static ReentrantLock lock = new ReentrantLock();

	private static int i = 0;
	private static List<String> classNames = Lists.newArrayList();
	private static Map<String, Class<?>> strAndClassMap = Maps.newHashMap();

	public static Class<?> getServiceType(Class<?> interfaceType) {

		if (interfaceType == null) {
			throw new CommunicationException("未能获取接口类型:" + interfaceType);
		}
		Class<?> serviceType = map.get(interfaceType);

		if (serviceType == null) {
			throw new CommunicationException("未能获取接口" + interfaceType + "实现类，实现类需要配置@Service");
		}

		return serviceType;
	}

	public static void initialize() {

		if (isInitialized) {
			return;
		}
		
		try {
			lock.lock();

			if (isInitialized) {
				return;
			}

			// 第一次进入
			if (i == 0) {
				String[] pakcages = Application.getInstance().getPackagesToScan().split(",");

				for (String pack : pakcages) {
					classNames.addAll(PakcageManage.getClassNames(pack,"服务类型初始化"));
				}

				i = 1;
			}

			for (String cls : classNames) {
				if (strAndClassMap.containsKey(cls)) {
					continue;
				} else {
					Class<?> clsEntity = null;
					try {
						clsEntity = Class.forName(cls);
					} catch (Exception eee) {

					}

					strAndClassMap.put(cls, clsEntity);
				}
			}

			if (isInitialized)
				return;

			// 最后一个create 方法设置为true
			// 即最后一个create方法 完成完整的扫描
			for (Class<?> cls : strAndClassMap.values()) {
				if (cls != null)
					parseService(cls);
			}

			isInitialized = true;

		} finally {
			lock.unlock();
		}
	}

	private static void parseService(Class<?> cls) {

		if (cls.isInterface()) {
			return;
		}

		Service service = (Service) cls.getAnnotation(Service.class);
		if (service == null) {
			return;
		}

		Class<?>[] inters = cls.getInterfaces();

		if (inters.length == 1) {
			map.put(inters[0], cls);
		} else if (inters.length == 0) {
			logger.warn(cls.getName() + "没有对应的接口！");
		} else {
			Class<?> inter = service.type();
			if (inter == IPersistable.class) {
				inter = null;
			}
			if (inter != null) {
				map.put(inter, cls);
			} else {

				String message = cls.getName();
				message += "标记为服务，但是其有两个接口，无法确定服务接口是哪个，请用type属性标记服务接口，";
				message += "方法：@Service(type=ISalesOrderService.class)" + StringManager.NewLine;

				String[] names = new String[inters.length];
				for (int i = 0; i < inters.length; i++) {
					names[i] = inters[i].getSimpleName();
				}

				message += "此服务已经实现的接口有：" + StringManager.join(",", names);

				logger.warn(message);
			}
		}
	}

}
