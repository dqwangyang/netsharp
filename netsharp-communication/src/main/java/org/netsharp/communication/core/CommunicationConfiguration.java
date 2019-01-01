package org.netsharp.communication.core;

import org.netsharp.application.Configuration;
import org.netsharp.application.ConfigItem;

@Configuration(file = "/conf/communication.properties")
public class CommunicationConfiguration extends ConfigurationBase {

	@ConfigItem(key = "org.netsharp.communication.protocol", defaultValue = "local")
	private TransportProtocol protocol;

	private CommunicationConfiguration() {}

	private static CommunicationConfiguration instance = null;

	public static CommunicationConfiguration get() {

		if (instance == null) {
			instance = new CommunicationConfiguration();
			instance.deserialize();
		}

		return CommunicationConfiguration.instance;
	}

	public TransportProtocol getProtocol() {
		return protocol;
	}
}
