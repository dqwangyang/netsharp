/**  
* @Title: PropertyColumnTextArea.java 
* @Package org.netsharp.panda.commerce.base.controls 
* @Description: TODO
* @author hanwei
* @date 2015-7-13 上午11:56:30 
* @version V1.0  
*/ 

package org.netsharp.panda.commerce.base.controls;

import org.netsharp.panda.controls.datagrid.DataGridColumn;
import org.netsharp.panda.controls.datagrid.TextAreaColumn;
import org.netsharp.panda.entity.PDatagridColumn;

/** 
 * @ClassName: PropertyColumnTextArea 
 * @Description: TODO
 * @author hanwei 
 * @date 2015-7-13 上午11:56:30 
 *  
 */

public class PropertyColumnTextArea extends PropertyColumnBase{
    @Override
    public DataGridColumn create(PDatagridColumn dcolumn)
    {
        TextAreaColumn textAreaColumn = new TextAreaColumn();
        {
        	textAreaColumn.required = dcolumn.isRequired();
        }

        this.render(textAreaColumn, dcolumn);

        return textAreaColumn;
    }
}
