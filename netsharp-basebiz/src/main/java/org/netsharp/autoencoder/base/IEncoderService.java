package org.netsharp.autoencoder.base;

import org.netsharp.autoencoder.entity.Encoder;
import org.netsharp.base.IPersistableService;


public interface IEncoderService extends IPersistableService<Encoder> {
	//根据实体类名取得当前编码的下一个编码
	String getNextCode(String bizType,String entityType);
	
	Encoder byBizType(String billType);

	String createEncodeRule(Class<?> type, String briefCode, String fixCode, String dateformat, Integer serialLength);
	
	/**
	 * <p>方法名称：getExpandClassName</p>
	 * <p>方法描述：取得扩展规则的类名</p>
	 * @param bizType
	 * @param name
	 * @return
	 * @author gaomeng
	 * @since  2016年4月7日
	 * <p> history 2016年4月7日 gaomeng  创建   <p>
	 */
	String getExpandClassName(String bizType, String entityType);
}
