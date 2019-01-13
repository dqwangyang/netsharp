package com.kaibo.core.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.kaibo.core.base.IGradeClassService;
import com.kaibo.core.entity.GradeClass;

@Service
public class GradeClassService extends PersistableService<GradeClass> implements IGradeClassService {
	public GradeClassService() {
		super();
		this.type = GradeClass.class;
	}
}
