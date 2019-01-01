package org.netsharp.web;

import java.util.Date;

import org.netsharp.util.DateManage;
import org.netsharp.web.dto.OssConfig;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;

public class AliyunOssController {

	private final String HTTP = "http://";
	private final String DOMAIN = "oss-cn-beijing.aliyuncs.com";
	private final String endpoint = HTTP + DOMAIN;
	private final String accessId = "LTAIyAUK8AD04P5S";
	private final String accessKey = "DHmRtFlw2Zr3KaRwUFeiu7FWATnmla";
	private final String dir = "netsharp";
	private final String bucketName = "gsb-public.";

	public OssConfig getOssConfig() {

		String host = HTTP + bucketName + DOMAIN;
		OssConfig config = new OssConfig();
		{
			config.setAccessid(accessId);
			config.setHost(host);
			config.setDir(dir);
			config.setExpire(30);
		}

		OSSClient ossClient = new OSSClient(endpoint, accessId, accessKey);
		try {

			Date expiration = DateManage.parse("2120-01-01"); 
			System.out.println(DateManage.toString(expiration,"yyyy-MM-dd HH:mm:ss"));
			PolicyConditions policyConds = new PolicyConditions();
			policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
			policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

			String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
			byte[] binaryData = postPolicy.getBytes("utf-8");
			String encodedPolicy = BinaryUtil.toBase64String(binaryData);
			config.setPolicyBase64(encodedPolicy);

			String signature = ossClient.calculatePostSignature(postPolicy);
			config.setSignature(signature);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return config;
	}
	
	public static void main(String[] args) {
		
		AliyunOssController ctl = new AliyunOssController();
		OssConfig config = ctl.getOssConfig();
		System.out.println(config.getSignature());
	}
}
