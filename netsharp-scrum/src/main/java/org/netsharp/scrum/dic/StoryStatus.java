package org.netsharp.scrum.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/*项目状态*/
public enum StoryStatus implements IEnum {

	hibernate(1, "未启动"), 
	repulse(2, "打回"), 
	process(3, "开发中"), 
	submitTest(4, "开发完成"), 
	test(5, "测试中"), 
	finished(6, "测试完成"), 
	publish(7, "已发布"), 
	stop(8, "已中止"), 
	delete(9, "删除"),
	unfinished(10, "未完成终止");
	private int value;
	private String text;

	StoryStatus(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static StoryStatus getItem(int value) {

		for (StoryStatus item : values()) {

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
