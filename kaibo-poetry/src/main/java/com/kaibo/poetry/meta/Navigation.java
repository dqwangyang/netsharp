package com.kaibo.poetry.meta;

import org.junit.Before;
import org.netsharp.meta.base.NavigationBase;
import org.netsharp.panda.plugin.entity.PNavigation;

public class Navigation extends NavigationBase {

	@Before
	public void setup() {
		this.treeName = "凯博读诗";
		this.treeCode = "kaibo_poetry";
		this.treePath = "kaibo/poetry";
		this.resourceNode = "kaibo_poetry";
	}

	public void createAccodions() {

		this.doCreateAccodions("kaibo_poetry", "凯博读诗", "fa fa-connectdevelop fa-fw",998);
	}

	protected void doCreateTree(PNavigation tree) {

		createPTreeNode(tree, null, "", "poetry", "诗歌管理", "", 1);
		{
			createPTreeNode(tree, "poetry", null, "poetry", "当前迭代", "/kaibo/poetry/list", 1);
		}
		
		createPTreeNode(tree, null, "", "kaibobase", "基础信息", "", 2);
		{
			createPTreeNode(tree, "kaibobase", null, "student", "学生列表", "/kaibo/student/list", 1);
			createPTreeNode(tree, "kaibobase", null, "class", "班级信息", "/kaibo/class/list", 2);
//			createPTreeNode(tree, "kaibobase", null, "grade", "年级信息", "/kaibo/grade/list", 3);
		}
	}
}