package com.kaibo.core.meta;

import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.NetsharpException;
import org.netsharp.core.Oql;

import com.kaibo.core.base.IGradeService;
import com.kaibo.core.entity.Grade;

public class GradeDataTest {

	@Test
	public void initialize() {
		IGradeService service = ServiceFactory.create(IGradeService.class);
		int count = service.queryCount(new Oql());
		if (count > 0) {
			return;
		}

		Grade cls = new Grade();
		{
			cls.toNew();
			cls.setName("小学");
		}
		cls = service.save(cls);
		for (int i = 1; i <= 6; i++) {
			Grade grade = new Grade();
			{
				grade.toNew();
				grade.setName(this.toCN(i)+ "年级");
				grade.setParentId(cls.getId());
			}
			service.save(grade);
		}
		
		//----------------------------------------
		cls = new Grade();
		{
			cls.toNew();
			cls.setName("初中");
		}
		cls = service.save(cls);
		for (int i = 1; i <= 3; i++) {
			Grade grade = new Grade();
			{
				grade.toNew();
				grade.setName(this.toCN(i)+ "年级");
				grade.setParentId(cls.getId());
			}
			service.save(grade);
		}
		
		//----------------------------------------
		cls = new Grade();
		{
			cls.toNew();
			cls.setName("高中");
		}
		cls = service.save(cls);
		for (int i = 1; i <= 3; i++) {
			Grade grade = new Grade();
			{
				grade.toNew();
				grade.setName(this.toCN(i)+ "年级");
				grade.setParentId(cls.getId());
			}
			service.save(grade);
		}		
	}

	private String toCN(int i) {
		switch (i) {
		case 1:
			return "一";
		case 2:
			return "二";
		case 3:
			return "三";
		case 4:
			return "四";
		case 5:
			return "五";
		case 6:
			return "六";
		}

		throw new NetsharpException();
	}

}
