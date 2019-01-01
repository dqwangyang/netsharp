package org.netsharp.wx.sdk.mp.api.messagetemplate;

/*行业编码*/
public class IndustryCode {
	
	private String code;
	private String first_class;
	private String second_class;
	
	public IndustryCode(){};
		
	public IndustryCode(String code,String first_class,String second_class){
		this.code=code;
		this.first_class=first_class;
		this.second_class=second_class;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getFirst_class() {
		return first_class;
	}

	public void setFirst_class(String first_class) {
		this.first_class = first_class;
	}

	public String getSecond_class() {
		return second_class;
	}

	public void setSecond_class(String second_class) {
		this.second_class = second_class;
	}
}

//new IndustryCode("1", "IT科技","互联网/电子商务");
//new IndustryCode("2", "IT科技","IT软件与服务");
//new IndustryCode("3", "IT科技","IT硬件与设备");
//new IndustryCode("4", "IT科技","电子技术");
//new IndustryCode("5", "IT科技","通信与运营商");
//new IndustryCode("6", "IT科技","网络游戏");
//new IndustryCode("7", "金融业","银行");
//new IndustryCode("8", "金融业","基金|理财|信托");
//new IndustryCode("9", "金融业","保险");
//new IndustryCode("10", "餐饮","餐饮");
//new IndustryCode("11", "酒店旅游","酒店");
//new IndustryCode("12", "酒店旅游","旅游");
//new IndustryCode("13", "运输与仓储","快递");
//new IndustryCode("14", "运输与仓储","物流");
//new IndustryCode("15", "运输与仓储","仓储");
//new IndustryCode("16", "教育","培训");
//new IndustryCode("17", "教育","院校");
//new IndustryCode("18", "政府与公共事业","学术科研");
//new IndustryCode("19", "政府与公共事业","交警");
//new IndustryCode("20", "政府与公共事业","博物馆");
//new IndustryCode("21", "政府与公共事业","公共事业|非盈利机构");
//new IndustryCode("22", "医药护理","医药医疗");
//new IndustryCode("23", "医药护理","护理美容");
//new IndustryCode("24", "医药护理","保健与卫生");
//new IndustryCode("25", "交通工具","汽车相关");
//new IndustryCode("26", "交通工具","摩托车相关");
//new IndustryCode("27", "交通工具","火车相关");
//new IndustryCode("28", "交通工具","飞机相关");
//new IndustryCode("29", "房地产","建筑");
//new IndustryCode("30", "房地产","物业");
//new IndustryCode("31", "消费品","消费品");
//new IndustryCode("32", "商业服务","法律");
//new IndustryCode("33", "商业服务","会展");
//new IndustryCode("34", "商业服务","中介服务");
//new IndustryCode("35", "商业服务","认证");
//new IndustryCode("36", "商业服务","审计");
//new IndustryCode("37", "文体娱乐","传媒");
//new IndustryCode("38", "文体娱乐","体育");
//new IndustryCode("39", "文体娱乐","娱乐休闲");
//new IndustryCode("40", "印刷","印刷");
//new IndustryCode("41", "其它","其它");
