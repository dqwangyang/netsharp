package org.netsharp.panda.entity;

import org.netsharp.core.annotations.Column;
import org.netsharp.resourcenode.entity.ResourceBizEntity;

/**   
 * @ClassName:  UiDescription   
 * @Description:界面元素项目基类
 * @author: 韩伟
 * @date:   2017年8月28日 下午2:53:01   
 *     
 * @Copyright: 2017 www.netsharp.org Inc. All rights reserved. 
 */
public abstract class UiDescription extends ResourceBizEntity {

	private static final long serialVersionUID = -7224141889273255066L;

	@Column(name = "system", header = "系统数据")
	protected boolean system = false;

	@Column(name = "readonly", header = "只读")
	protected boolean readOnly = false;

	public boolean isSystem() {
		return this.system;
	}

	public UiDescription setSystem(boolean system) {
		this.system = system;
		return this;
	}

	public boolean isReadOnly() {
		return this.readOnly;
	}

	public UiDescription setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
		return this;
	}
}