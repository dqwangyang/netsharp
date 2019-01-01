package org.netsharp.panda.controls.bootstrap.box;

import org.netsharp.panda.controls.other.Div;
import org.netsharp.panda.controls.table.Table;

public class BoxBody extends Div{
	
	public Table table;
	
    @Override
	public void initialize() {
    	
    	this.setClassName("box-body");
    	Div responsive = new Div();{
    		responsive.setClassName("table-responsive");
    		if(this.table != null){
    			
    			responsive.getControls().add(table);
    		}
    	}
    	this.getControls().add(responsive);

//  <table class="table no-margin">
//      <thead>
//        <tr>
//          <th>Order ID</th>
//          <th>Item</th>
//          <th>Status</th>
//          <th>Popularity</th>
//        </tr>
//      </thead>
//      <tbody>
//        <tr>
//          <td><a href="pages/examples/invoice.html">OR9842</a></td>
//          <td>Call of Duty IV</td>
//          <td><span class="label label-success">Shipped</span></td>
//          <td><div class="sparkbar" data-color="#00a65a" data-height="20"><canvas width="34" height="20" style="display: inline-block; width: 34px; height: 20px; vertical-align: top;"></canvas></div></td>
//        </tr>
//      </tbody>
//    </table>
    }
}
