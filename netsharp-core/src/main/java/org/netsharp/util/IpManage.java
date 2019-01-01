package org.netsharp.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IpManage {
    public static String getIp(){
    	
    	
		try {
			InetAddress addr = InetAddress.getLocalHost();
			
			String ip=addr.getHostAddress().toString();//获得本机IP
	        //address=addr.getHostName()toString;//获得本机名称
	    	
	    	return ip.trim();
		} catch (UnknownHostException e) {
			return null;
		}
    }
}
