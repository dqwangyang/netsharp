package org.netsharp.application;

import org.netsharp.util.StringManager;

@Configuration(file = "conf/configuration.properties")
public class Application extends ApplicationConfiguration {
	
	private static Application instance;
	private Application() {}
	
	// 应用程序显示名称
	@ConfigItem(key="org.netsharp.name",defaultValue="Netsharp")
	private String name ;
	
	// 要检索的包名，多个包用逗号分开
	@ConfigItem(key="org.netsharp.packages.scan",defaultValue="org.netsharp")
	private String packagesToScan ;
	
	// 机器编号（物理编号）
	@ConfigItem(key="org.netsharp.performance",defaultValue="true")
	private Boolean performance=true;
	
	// 机器编号（物理编号）
	@ConfigItem(key="org.netsharp.id.datacenterid",defaultValue="1")
	private Long datacenterid;
	
	// 实例编号（进程编号）
	@ConfigItem(key="org.netsharp.id.workid",defaultValue="1")
	private Long workid;
	
	public static Application getInstance() {
		
		if(instance==null) {
			instance = new Application();
			instance.deserialize();
			
			if(StringManager.isNullOrEmpty(instance.packagesToScan)) {
				instance.packagesToScan="org.netsharp";
			}else {
				if(!instance.packagesToScan.contains("org.netsharp")) {
					instance.packagesToScan += ",org.netsharp";
				}
			}
		}
		
		return instance;
	}


	public String getName() {
		return name;
	}

	public String getPackagesToScan() {
		return packagesToScan;
	}

	public Long getDatacenterid() {
		return datacenterid;
	}


	public Long getWorkid() {
		return workid;
	}


	public Boolean getPerformance() {
		return performance;
	}


	public void setPerformance(Boolean performance) {
		this.performance = performance;
	}
}