package org.netsharp.autoencoder.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.netsharp.autoencoder.base.IExpandEncodeRule;
import org.netsharp.core.annotations.BizCode;
import org.netsharp.entity.IPersistable;


/**
 * <p>Title: EncodeExpandByEntity</P>
 * <p>Description: 根据实体的属性替换单据编码的对应位置（{field},支持引用对象的字段），</p>
 * <p>Copyright: 易快修 </p>
 * @author gaomeng
 * @version 1.0
 * @since 2016年4月7日
 */
public class EncodeExpandByEntity implements IExpandEncodeRule {

	@Override
	public <T extends IPersistable> void process(T entity) {
		BizCode bizCode = entity.getClass().getAnnotation(BizCode.class);
		String field=bizCode.field();
		String code=(String) entity.get(field);//
		Pattern pattern = Pattern.compile("\\{[A-Za-z0-9\\.]*\\}");
		Matcher matcher = pattern.matcher(code);
		while (matcher.find()) {
			String mat=matcher.group();
			String matfield=mat.substring(1, mat.length()-1);			
			code=code.replace(mat, String.valueOf(entity.get(matfield)));
		}
		entity.set(field, code);
	}
}
