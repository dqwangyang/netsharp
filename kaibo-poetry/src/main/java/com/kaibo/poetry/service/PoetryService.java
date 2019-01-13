package com.kaibo.poetry.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.kaibo.poetry.base.IPoetryService;
import com.kaibo.poetry.entity.Poetry;

@Service
public class PoetryService extends PersistableService<Poetry> implements IPoetryService {
	public PoetryService() {
		super();
		this.type = Poetry.class;
	}

}
