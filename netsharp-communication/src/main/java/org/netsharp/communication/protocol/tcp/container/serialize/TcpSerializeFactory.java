package org.netsharp.communication.protocol.tcp.container.serialize;

public class TcpSerializeFactory {
	
	public static ITcpSerialize create() {
		return new TcpObjectSerialize();
	}

}
