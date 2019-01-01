//package org.netsharp.plugin;
//
//import java.util.List;
//
//import org.junit.Test;
//import org.netsharp.panda.controls.Control;
//import org.netsharp.panda.controls.toolbar.Toolbar;
//import org.netsharp.panda.controls.toolbar.ToolbarItem;
//import org.netsharp.panda.core.pad.IPad;
//import org.netsharp.panda.plugin.ToolbarService;
//import org.netsharp.panda.plugin.entity.PPads;
//import org.netsharp.panda.plugin.entity.TreeManager;
//import org.netsharp.plugin.core.AddInTree;
//
//public class ToolbarTest {
//	
//    @Test
//    public void toolbarDatagrid(){
//    	
//    	String path = "panda/datagrid/voucher";
//    	
//    	 Toolbar toolbar = ToolbarService.create(null,path, "grid");
//    	 
//    	 for(Control item : toolbar.getControls()){
//    		 ToolbarItem  ti =(ToolbarItem)item;
//    		 
//    		 System.out.println(ti.Value);
//    	 }
//    }
//    
//    @Test
//    public void toolbarForm(){
//    	
//    	String path = "panda/form/voucher";
//    	
//    	 Toolbar toolbar = ToolbarService.create(null,path, "form");
//    	 
//    	 for(Control item : toolbar.getControls()){
//    		 ToolbarItem  ti =(ToolbarItem)item;
//    		 
//    		 System.out.println(ti.Value);
//    	 }
//    }
//    
//    @Test
//    public void pads(){
//    	
//    	String path = "panda/workbench/pad";
//    	
//    	List<Object> items = AddInTree.buildItems(PPads.class,null,path, "pads");
//    	 
//    	 for(Object item : items){
//    		 IPad pad = (IPad)item;
//    		 
//    		 System.out.println(pad.toString());
//    	 }
//    }
//    
//    @Test
//    public void treenode(){
//    	
//    	String path = "ykx/online";
//    	
//    	 TreeManager.create(path, null, null);
//    	 
////    	 for(Object item : items){
////    		 IPad pad = (IPad)item;
////    		 
////    		 System.out.println(pad.toString());
////    	 }
//    }
//}
