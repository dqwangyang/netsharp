package org.netsharp.panda;

import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.id.SnowflakeId;
import org.netsharp.panda.base.IPWorkspaceService;
import org.netsharp.util.NetUtil;

public class PWorkspaceTest {

	@Test
	public void remove() {

		System.out.println(NetUtil.getLocalMac());

		// String code = "ykx-cbb-dingjin-list";
		// String code = "ykx-cbb-dingdan-list";
		//
		// IPWorkspaceService service =
		// ServiceFactory.create(IPWorkspaceService.class);
		// int count = service.removeByResourceCode(code);
		//
		// System.out.println("OK "+count);

		for (int i = 0; i < 100; i++) {
			SnowflakeId id = new SnowflakeId();
			System.out.println(id.newId().toString());
		}
	}

	@Test
	public void queryProject() {
		String url = "/operationType/list";
		IPWorkspaceService workspaceService = ServiceFactory.create(IPWorkspaceService.class);
		workspaceService.byUrl(url);
	}
}
