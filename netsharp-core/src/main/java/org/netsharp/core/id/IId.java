package org.netsharp.core.id;

/*实体Id处理器*/
public interface IId {
	
	/*新创建一个Id*/
    Object newId();
    
    /*Id是否有效*/
    boolean isEmpty(Object id);
    
    String getEmptyFilter(String name);
}
