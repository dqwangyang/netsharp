package com.kaibo.core.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.kaibo.core.base.IGradeService;
import com.kaibo.core.entity.Grade;

@Service
public class GradeService  extends PersistableService<Grade> implements IGradeService {
	public GradeService() {
		super();
		this.type = Grade.class;
	}
}