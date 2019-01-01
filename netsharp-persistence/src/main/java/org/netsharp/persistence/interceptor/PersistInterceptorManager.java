package org.netsharp.persistence.interceptor;

import java.util.ArrayList;
import java.util.List;

public class PersistInterceptorManager {
	
    public static List<IPersistInterceptor> BeforeInterceptors = new ArrayList<IPersistInterceptor>();
    public static List<IPersistInterceptor> AfterInterceptors = new ArrayList<IPersistInterceptor>();
    
	static{
		
		BeforeInterceptors.add(new PersistInterceptorArchive());
//      BeforeInterceptors.add(new PersistInterceptorSession());
//      BeforeInterceptors.add(new PersistInterceptorCode());
//      BeforeInterceptors.add(new PersistInterceptorCategorySysEntity());
//      BeforeInterceptors.add(new PersistInterceptorReference());
//
//      AfterInterceptors.add(new PersistInterceptorConcurrency());
	}
}
