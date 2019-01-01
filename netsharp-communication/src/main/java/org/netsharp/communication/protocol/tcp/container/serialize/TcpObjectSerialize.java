package org.netsharp.communication.protocol.tcp.container.serialize;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import org.netsharp.communication.core.CommunicationException;

public class TcpObjectSerialize implements ITcpSerialize {

	public void serialize(OutputStream outputStream, Object obj) {
		
		try {
			ObjectOutputStream out = new ObjectOutputStream(outputStream);
			out.writeObject(obj);
			out.flush();

		}catch(Exception ex) {
			throw new CommunicationException("序列化异常",ex);
		}
	}

	public Object deSerialize(InputStream inputStream) {
		
		try {
			ObjectInputStream in = new ObjectInputStream(inputStream);
			Object obj = in.readObject();
			
			return obj;
		}catch(Exception ex) {
			throw new CommunicationException("反序列化异常",ex);
		}
		
	}

}
