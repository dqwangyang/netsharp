package com.kaibo.core.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.kaibo.core.base.IStudentService;
import com.kaibo.core.entity.Student;

@Service
public class StudentService  extends PersistableService<Student> implements IStudentService {
	public StudentService() {
		super();
		this.type = Student.class;
	}
}