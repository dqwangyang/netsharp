package org.netsharp.communication.protocol.tcp.container.server;

import org.netsharp.application.Configuration;
import org.netsharp.application.ConfigItem;
import org.netsharp.communication.core.ConfigurationBase;

@Configuration(file = "/conf/communication.properties")
public class TcpServerConfiguration extends ConfigurationBase {

	@ConfigItem(key = "org.netsharp.communication.pool.coreSize", defaultValue = "200")
	private int corePoolSize = 200;
	@ConfigItem(key = "org.netsharp.communication.poll.maxinumSize", defaultValue = "1000")
	private int maximumPoolSize = 1000;
	@ConfigItem(key = "org.netsharp.communication.pool.keepAliveTime", defaultValue = "100")
	private long keepAliveTime = 100;

	private TcpServerConfiguration() {}

	private static TcpServerConfiguration instance = null;

	public static TcpServerConfiguration get() {

		if (instance == null) {
			instance = new TcpServerConfiguration();
			instance.deserialize();
		}

		return instance;
	}

	public int getCorePoolSize() {
		return corePoolSize;
	}

	public int getMaximumPoolSize() {
		return maximumPoolSize;
	}

	public long getKeepAliveTime() {
		return keepAliveTime;
	}
}