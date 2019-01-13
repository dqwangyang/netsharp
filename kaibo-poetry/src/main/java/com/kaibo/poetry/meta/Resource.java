package com.kaibo.poetry.meta;

import org.junit.Before;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.meta.base.ResourceCreationBase;
import org.netsharp.resourcenode.IResourceNodeService;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.kaibo.core.base.IGradeClassService;
import com.kaibo.core.base.IStudentService;
import com.kaibo.core.entity.GradeClass;
import com.kaibo.core.entity.Student;
import com.kaibo.poetry.base.IPoetryService;
import com.kaibo.poetry.entity.Poetry;

public class Resource extends ResourceCreationBase {

	IResourceNodeService service = ServiceFactory.create(IResourceNodeService.class);

	@Before
	public void setup() {

		parentNodeName = pluginName = "凯博诗歌";
		parentNodeCode = "kaibo-poetry";
		entityClass = Poetry.class;
	}

	@Override
	protected void createResourceNodeVouchers(ResourceNode node) {

		ResourceNode node1 = this.createResourceNodeCategory("凯博诗歌", "kaibo-poetry", node.getId());
		this.createResourceNodeVoucher(Poetry.class.getName(), "诗歌", Poetry.class.getSimpleName(), IPoetryService.class.getName(), node1.getId());

		node1 = this.createResourceNodeCategory("基础信息", "kaibo-base", node.getId());
		this.createResourceNodeVoucher(Student.class.getName(), "当前迭代", Student.class.getSimpleName(), IStudentService.class.getName(), node1.getId());
		this.createResourceNodeVoucher(GradeClass.class.getName(), "上个迭代", GradeClass.class.getSimpleName(), IGradeClassService.class.getName(), node1.getId());

}
}