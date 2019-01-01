package org.netsharp.panda.controls.datagrid;

import org.netsharp.panda.annotation.DataOption;
import org.netsharp.panda.annotation.EditorOption;
import org.netsharp.panda.annotation.HtmlNode;
import org.netsharp.panda.controls.table.TH;

/**   
 * @ClassName:  DataGridColumn   
 * @Description:TODO
 * @author: 韩伟
 * @date:   2017年8月23日 下午3:04:59   
 *     
 * @Copyright: 2017 www.netsharp.org Inc. All rights reserved. 
 */
@HtmlNode(html="th", isValue=true)
public class DataGridColumn extends TH
{
    @DataOption(html="field")
    public String field;

    @DataOption(html="width")
    public int width;

    @DataOption(html="formatter", isEvent=true)
    public String formatter;
  
    @DataOption(html="styler", isEvent=true)
    public String styler;

    @DataOption(html="align")
    public String align;

    @DataOption(html="editor", isEvent=true)
    public String editor;

    @DataOption(html="sortable")
    public boolean sortable = false;

    @DataOption(html="columnType")
    public String columnType;

    @DataOption(html="foreignName")
    public String foreignName;

    @DataOption(html="groupName")
    public String groupName;
    
    @DataOption(html="aggregateType")
    public String aggregateType;
    
    /**   
     * @Fields precision : 小数位
     */   
    @EditorOption(html="precision",isOption =true,isEvent= false)
    public int precision = 2;
    
	public String jsInstance;

    public boolean frozened;
    
    public boolean visibleed;
    
}

