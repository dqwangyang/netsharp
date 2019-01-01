package org.netsharp.elephant;

import org.netsharp.communication.protocol.tcp.container.server.TcpServer;
import org.netsharp.communication.protocol.tcp.container.server.TcpServerConfiguration;
import org.netsharp.communication.registry.ServiceRegistry;

public class ElephantServer {
	
	public static void main(String[] args) {
		
		ServiceRegistry.initialize();

		TcpServer tcpServer = new TcpServer();
		tcpServer.start(TcpServerConfiguration.get());

		while (true) {
			tcpServer.accept();
		}
	}
}

/*

#进入elephant目录
cd /home/xufangbo/gongsibao/git/netsharp/netsharp-elephant

#gradle进行打包
gradle release

#命令行进入jar包目录，也可以复制到别的地方
cd ~/gongsibao/git/netsharp/netsharp-elephant/build/libs

#执行jar包的main函数
java -jar netsharp-elephant-3.1.1-SNAPSHOT.jar

*/