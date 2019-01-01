package org.netsharp.panda.service;

import org.netsharp.communication.Service;
import org.netsharp.panda.base.IPFormFieldService;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.service.PersistableService;

@Service
public class PFormFieldService extends PersistableService<PFormField> implements IPFormFieldService {

	public PFormFieldService() {
		super();
		this.type = PFormField.class;
	}
}
