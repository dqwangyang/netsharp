package org.netsharp.communication.protocol.tcp.container.serialize;

import java.io.InputStream;
import java.io.OutputStream;

public interface ITcpSerialize {
	
	void serialize(OutputStream outputStream ,Object obj);
	Object deSerialize(InputStream inputStream);
	

}
