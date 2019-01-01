package org.netsharp.scrum.service;

import org.netsharp.communication.Service;
import org.netsharp.scrum.base.IProductDemandService;
import org.netsharp.scrum.entity.ProductDemand;
import org.netsharp.service.BizService;

@Service
public class ProductDemandService extends BizService<ProductDemand> implements IProductDemandService {

	public ProductDemandService(){
		super();
		this.type = ProductDemand.class;
	}
}