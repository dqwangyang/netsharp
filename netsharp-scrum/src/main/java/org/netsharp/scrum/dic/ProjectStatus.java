package org.netsharp.scrum.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum ProjectStatus implements IEnum {

	hibernate(1, "未启动"), 
	definition(2, "产品定义"), 
	process(3, "开发中"), 
	submitTest(4, "提交测试"), 
	test(5, "测试中"), 
	finished(6, "测试完成"), 
	publish(7, "已发布"), 
	suspend(8, "挂起"),
	stop(9, "已中止"), 
	delete(10, "删除");

	private int value;
	private String text;

	ProjectStatus(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static ProjectStatus getItem(int value) {

		for (ProjectStatus item : values()) {

			if (item.getValue() == value) {
				return item;
			}
		}
		return null;
	}

	public String getText() {
		return this.text;
	}

	@Override
	public Integer getValue() {

		return this.value;
	}
}
