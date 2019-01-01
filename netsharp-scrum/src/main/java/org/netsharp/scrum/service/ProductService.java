package org.netsharp.scrum.service;

import org.netsharp.communication.Service;
import org.netsharp.scrum.base.IProductService;
import org.netsharp.scrum.entity.Product;
import org.netsharp.service.BizService;

@Service
public class ProductService extends BizService<Product> implements IProductService {
	
	public ProductService(){
		this.type = Product.class;
	}
}
