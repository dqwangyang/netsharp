package org.netsharp.persistence.query;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.core.Column;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.TableCompositeOne;
import org.netsharp.core.TableReference;
import org.netsharp.core.TableSubs;
import org.netsharp.core.property.IProperty;
import org.netsharp.entity.IPersistable;
import org.netsharp.util.ObjectManager;
import org.netsharp.util.ReflectManager;

public class TTable {
	
	public Mtable Mtable;
    public ArrayList<IPersistable> Items=new ArrayList<IPersistable>();
    private int totalCount;
    
    public IPersistable newItem(){
    	
    	IPersistable obj=(IPersistable)ReflectManager.newInstance(this.Mtable.getType());
    	
    	return obj;
    }

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	public void connect(TSet set){
		
		for(TableSubs composite : this.Mtable.getSubs().values()){
			String toentityId= composite.getToEntityId();
			TTable toTable= set.getTable(toentityId);
			if(toTable==null){
				continue;
			}
			
			Field field=composite.getField();
			IProperty fkProperty= composite.getFkColumn().getProperty();
						
			for(Object item : this.Items){
				@SuppressWarnings("unchecked")
				List<Object> subs= (List<Object>) ReflectManager.get(field, item);
				if(subs==null){
					
					subs=new ArrayList<Object>();
					ReflectManager.set(field, item,subs);
				}
				
				Object id=this.Mtable.getId(item);
				
				for(Object sub : toTable.Items){
					Object fk= fkProperty.field(sub);
					if(ObjectManager.equals(id, fk)){
						subs.add(sub);
					}
				}
			}
		}
		
		//
		for(TableReference tr : this.Mtable.getReferences().values()){
			this.connectReference(set, tr);
		}
		
		for(TableCompositeOne tr : this.Mtable.getCompositeOnes().values()){
			this.connectReference(set, tr);
		}
	}
	
	private void connectReference(TSet set,TableReference tr){
		
		String toentityId= tr.getToEntityId();
		TTable toTable= set.getTable(toentityId);
		Mtable toMeta=MtableManager.getMtable(toentityId);
		if(toTable==null){
			return;
		}
		
		Field field=tr.getField();
		Column fkColumn=tr.getFkColumn();
		
		IProperty fkProperty= fkColumn.getProperty();
		
		for(Object item : this.Items){
			Object fk=fkProperty.field(item);
			for(Object ref : toTable.Items){
				
				Object id=toMeta.getId(ref);
				
				if(ObjectManager.equals(id, fk)){
					ReflectManager.set(field, item,ref);
				}
			}
		}
	}
}
