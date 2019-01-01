package org.netsharp.communication.protocol.tcp.container.serialize;

import java.io.InputStream;
import java.io.OutputStream;

import org.netsharp.communication.core.CommunicationException;

public class TcpStringSerialize implements ITcpSerialize {

	public void serialize(OutputStream outputStream, Object obj) {
		
		try {
			String message = obj.toString();
		    //首先需要计算得知消息的长度
		    byte[] sendBytes = message.getBytes("UTF-8");
		    //然后将消息的长度优先发送出去
		    outputStream.write(sendBytes.length >>8);
		    outputStream.write(sendBytes.length);
		    //然后将消息再次发送出去
		    outputStream.write(sendBytes);
		    outputStream.flush();
		    //==========此处重复发送一次，实际项目中为多个命名，此处只为展示用法
		    message = "第二条消息";
		    sendBytes = message.getBytes("UTF-8");
		    outputStream.write(sendBytes.length >>8);
		    outputStream.write(sendBytes.length);
		    outputStream.write(sendBytes);
		    outputStream.flush();
		    //==========此处重复发送一次，实际项目中为多个命名，此处只为展示用法
		    message = "the third message!";
		    sendBytes = message.getBytes("UTF-8");
		    outputStream.write(sendBytes.length >>8);
		    outputStream.write(sendBytes.length);
		    outputStream.write(sendBytes);

		}catch(Exception ex) {
			throw new CommunicationException("序列化异常",ex);
		}
	}

	public Object deSerialize(InputStream inputStream) {
		
		try {
			byte[] bytes;
			StringBuilder builder = new StringBuilder();
			
		    // 因为可以复用Socket且能判断长度，所以可以一个Socket用到底
		    while (true) {
		      // 首先读取两个字节表示的长度
		      int first = inputStream.read();
		      //如果读取的值为-1 说明到了流的末尾，Socket已经被关闭了，此时将不能再去读取
		      if(first==-1){
		        break;
		      }
		      int second = inputStream.read();
		      int length = (first << 8) + second;
		      // 然后构造一个指定长的byte数组
		      bytes = new byte[length];
		      // 然后读取指定长度的消息即可
		      inputStream.read(bytes);
		      String message = new String(bytes, "UTF-8");
		      builder.append(message);
		    }
			
			return builder.toString();
		}catch(Exception ex) {
			throw new CommunicationException("反序列化异常",ex);
		}
		
	}

}