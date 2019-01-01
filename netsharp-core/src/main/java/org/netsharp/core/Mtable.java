package org.netsharp.core;

public class Mtable extends Table<Row> {
	
	private static final long serialVersionUID = -7736844644763666165L;
	private String fullSelected;
    
    protected Mtable(){
    	super();
    }

	public String getFullSelected() {
		return fullSelected;
	}

	public void setFullSelected(String fullSelected) {
		this.fullSelected = fullSelected;
	}
	
	 public Column findProperty(String path)
     {
         int lastIndex = path.lastIndexOf('.');
         if (lastIndex < 0)
         {
             return this.getProperty(path);
         }

         String relationPath = path.substring(0, lastIndex);
         TableRelation relation = findRelation(relationPath);
         
         if(relation == null ){
        	 throw new NetsharpException("找不到关系:"+this.getEntityId()+"["+relationPath+"]");
         }

         String propertyName = path.substring(lastIndex + 1);
         ITable<?> toTable = relation.getToTable();
         
         return toTable.getProperty(propertyName);
     }

     /// <summary>
     /// 根据路径名称查找实体关系元数据
     /// </summary>
     public TableRelation findRelation(String path)
     {
         String[] paths = path.split("\\.");

         Mtable meta = this;
         TableRelation relation = null;

         for (String roleName : paths)
         {
             TableReference refernece = meta.getReference(roleName);
             
             if (refernece!=null)
             {
                 meta = (Mtable)refernece.getToTable();

                 relation = refernece;

                 continue;
             }
             
             TableCompositeOne compositeOne = meta.getCompositeOne(roleName);
             
             if (compositeOne!=null)
             {
                 meta = (Mtable)compositeOne.getToTable();

                 relation = compositeOne;

                 continue;
             }

             TableSubs subs = meta.getSub(roleName);

             if (subs!=null)
             {
            	 meta = (Mtable)subs.getToTable();
                 relation = subs;

                 continue;
             }

             return null;
         }

         return relation;
     }
}
