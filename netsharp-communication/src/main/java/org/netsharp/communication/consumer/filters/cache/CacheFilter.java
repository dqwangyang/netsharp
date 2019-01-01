package org.netsharp.communication.consumer.filters.cache;

import org.netsharp.communication.consumer.CiContext;
import org.netsharp.communication.consumer.IClientFilter;

public class CacheFilter implements IClientFilter {
	
	private IClientFilter processor;

	public void invoking(CiContext ctx) {
		
		this.processor = CacheFactory.create(ctx);
		
		if(this.processor!=null) {
			this.processor.invoking(ctx);
		}
		
	}

	public void invoked(CiContext ctx) {
		
		if(this.processor!=null) {
			this.processor.invoked(ctx);
		}
		
	}

}
