package org.netsharp.communication;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import org.netsharp.communication.core.CommunicationConfiguration;
import org.netsharp.communication.core.CommunicationException;
import org.netsharp.communication.core.TransportProtocol;
import org.netsharp.communication.protocol.local.consumer.LocalInvoker;
import org.netsharp.communication.protocol.tcp.consumer.TcpInvoker;
import org.netsharp.communication.provider.SiContext;
import org.netsharp.util.ReflectManager;

public class ServiceFactory {

    public static <T> T create(Class<?> type) {
        return create(type.getName());
    }

    public static <T> T create(String type) {
    	
    	CommunicationConfiguration conf = CommunicationConfiguration.get();

    	return create(type,conf);

    }
    
    public static <T> T create(Class<?> type,CommunicationConfiguration conf){
    	
    	return create(type.getName(),conf);
    	
    }
    
	@SuppressWarnings("unchecked")
	public static <T> T create(String type,CommunicationConfiguration conf){
    	
    	InvocationHandler proxy = null;
    	TransportProtocol protocol = conf.getProtocol();
    	
    	if(SiContext.getCurrent()!=null) {
    		proxy = new LocalInvoker(type);
    	}else if( protocol==TransportProtocol.local){
    		proxy = new LocalInvoker(type);
    	}
    	else if( protocol==TransportProtocol.tcp){
    		proxy = new TcpInvoker(type);
    	}
    	else{ 
    		throw new CommunicationException("don't support transport protocol :"+conf.getProtocol());
    	}
    	
    	Class<?> interfaceType = ReflectManager.getType(type);
        Object proxyObject = Proxy.newProxyInstance(interfaceType.getClassLoader(), new Class<?>[] {interfaceType}, proxy);

         return (T) proxyObject;
    }
}
