package org.netsharp.meta.platform.operation;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.db.DbFactory;
import org.netsharp.db.IDb;
import org.netsharp.organization.base.IOperationTypeService;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.resourcenode.IResourceNodeService;
import org.netsharp.resourcenode.entity.ResourceNode;

public class OperationTypeTest {
	
	@Test
	public void execute(){

		IDb db = DbFactory.create();
		Mtable meta = MtableManager.getMtable(OperationType.class);
		db.reCreateTable(meta);
		
		IResourceNodeService resourceService = ServiceFactory.create(IResourceNodeService.class);
		ResourceNode node = resourceService.byCode(OperationType.class.getSimpleName());
		IOperationTypeService service = ServiceFactory.create(IOperationTypeService.class);
		List<OperationType> ots = new ArrayList<OperationType>();
		OperationType ot = null;
		ot = new OperationType();{
			ot.toNew();
			ot.setCode("operation");
			ot.setName("操作");
			ot.setResourceNode(node);
		}
		ots.add(ot);
		
		ot = new OperationType();{
			ot.toNew();
			ot.setCode("view");
			ot.setName("查看");
			ot.setResourceNode(node);
		}
		ots.add(ot);
		
		ot = new OperationType();{
			ot.toNew();
			ot.setCode("add");
			ot.setName("新增");
			ot.setResourceNode(node);
		}
		ots.add(ot);
		
		ot = new OperationType();{
			ot.toNew();
			ot.setCode("update");
			ot.setName("修改");
			ot.setResourceNode(node);
		}
		ots.add(ot);
		
		ot = new OperationType();{
			ot.toNew();
			ot.setCode("delete");
			ot.setName("删除");
			ot.setResourceNode(node);
		}
		
		ots.add(ot);
		ot = new OperationType();{
			
			ot.toNew();
			ot.setCode("audit");
			ot.setName("审核");
			ot.setResourceNode(node);
		}
		ots.add(ot);
		
		ot = new OperationType();{
			ot.toNew();
			ot.setCode("unaudit");
			ot.setName("弃审");
			ot.setResourceNode(node);
		}
		ots.add(ot);
		
		ot = new OperationType();{
			ot.toNew();
			ot.setCode("print");
			ot.setName("打印");
			ot.setResourceNode(node);
		}
		ots.add(ot);
		
		ot = new OperationType();{
			ot.toNew();
			ot.setCode("exportExcel");
			ot.setName("导出");
			ot.setResourceNode(node);
		}
		ots.add(ot);
		
		ot = new OperationType();{
			ot.toNew();
			ot.setCode("importExcel");
			ot.setName("导入");
			ot.setResourceNode(node);
		}
		ots.add(ot);
		
		ot = new OperationType();{
			ot.toNew();
			ot.setCode("attachment");
			ot.setName("附件");
			ot.setResourceNode(node);
		}
		ots.add(ot);
		
		for(int i=0;i<ots.size();i++){
			ot = ots.get(i);
			ot.setSeq(i*100);
			
			service.save(ot);
		}
	}
}
