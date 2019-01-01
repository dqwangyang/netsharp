package org.netsharp.panda.service;

import org.netsharp.communication.Service;
import org.netsharp.core.NetsharpException;
import org.netsharp.panda.base.IPDatagridService;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.service.PersistableService;
import org.netsharp.util.StringManager;

@Service
public class PDatagridService extends PersistableService<PDatagrid> implements IPDatagridService {
	
    public PDatagridService(){
    	super();
    	this.type=PDatagrid.class;
    }
    
    @Override
    public PDatagrid save(PDatagrid entity) {
    	
    	if(!entity.isReadOnly()) {
    		for(PDatagridColumn column : entity.getColumns()) {
    			if(!StringManager.isNullOrEmpty(column.getGroupName())) {
    				throw new NetsharpException("设置分组的列表，必须设置isReadOnly=true");
    			}
    		}
    	}
    	
		return super.save(entity);
	}
    
}
