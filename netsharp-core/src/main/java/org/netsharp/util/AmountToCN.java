package org.netsharp.util;

import java.math.BigDecimal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 金额转换为大写
 * 
 * @ClassName: AmountToCN
 * @Description: TODO
 * @author 王阳
 * @date 2016年4月7日 上午11:28:46
 *
 */
public class AmountToCN {
	static Log logger = LogFactory.getLog(AmountToCN.class);

	public static String getAmount(BigDecimal amount) {
		try {
			double dAmount = amount.doubleValue();
			String fraction[] = { "角", "分" };
			String digit[] = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
			String unit[][] = { { "元", "万", "亿" }, { "", "拾", "佰", "仟" } };

			String head = dAmount < 0 ? "负" : "";
			dAmount = Math.abs(dAmount);

			String s = "";
			for (int i = 0; i < fraction.length; i++) {
				s += (digit[(int) (Math.floor(dAmount * 10 * Math.pow(10, i)) % 10)] + fraction[i]).replaceAll("(零.)+", "");
			}
			if (s.length() < 1) {
				s = "整";
			}
			int integerPart = (int) Math.floor(dAmount);

			for (int i = 0; i < unit[0].length && integerPart > 0; i++) {
				String p = "";
				for (int j = 0; j < unit[1].length && dAmount > 0; j++) {
					p = digit[integerPart % 10] + unit[1][j] + p;
					integerPart = integerPart / 10;
				}
				s = p.replaceAll("(零.)*零$", "").replaceAll("^$", "零") + unit[0][i] + s;
			}
			return head + s.replaceAll("(零.)*零元", "元").replaceFirst("(零.)+", "").replaceAll("(零.)+", "零").replaceAll("^整$", "零元整");
		} catch (Exception e) {
			logger.info("金额转换出错:" + e.getMessage());
		}
		return "";
	}
}
