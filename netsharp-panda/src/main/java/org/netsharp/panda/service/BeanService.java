package org.netsharp.panda.service;

import org.netsharp.communication.Service;
import org.netsharp.panda.base.IBeanService;
import org.netsharp.plugin.bean.Bean;
import org.netsharp.service.PersistableService;

@Service
public class BeanService extends PersistableService<Bean> implements IBeanService {

	public BeanService() {
		super();
		this.type = Bean.class;
	}
}
