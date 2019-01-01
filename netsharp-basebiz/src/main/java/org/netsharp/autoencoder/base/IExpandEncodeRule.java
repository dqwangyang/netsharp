package org.netsharp.autoencoder.base;

import org.netsharp.entity.IPersistable;

/**
 * <p>Title: IExpandEncodeRule</P>
 * <p>Description: 编码动态扩展接口</p>
 * <p>Copyright: netsharp </p>
 * @author gaomeng
 * @version 1.0
 * @since 2016年4月7日
 */
public interface IExpandEncodeRule {
	<T extends IPersistable> void process(T entity);
}
