package org.netsharp.panda.controls.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.netsharp.panda.controls.Control;
import org.netsharp.panda.controls.IControlSerializer;
import org.netsharp.util.JsonManage;

public class JTreeNodeSerializor implements IControlSerializer
{
    public String Serialize(Control control)
    {
        Tree tree = (Tree)control;
        if (tree.nodes == null || tree.nodes.size() == 0)
        {
            return null;
        }
        
        List<Object> jsonArray = new ArrayList<Object>();
        for (TreeNode node : tree.nodes)
        {
            Map<String,Object> j = serialize(node);

            jsonArray.add(j);
        }

        String json = JsonManage.serialize2(jsonArray) ;
        

        return json;
    }

    public String SubJson(String json)
    {
        if (json == null)
        {
            return null;
        }

        json = json.replace("\"", "'");
        json = json.replace("'SerlizeType':'',", "");

        return json;
    }

    private Map<String,Object> serialize(TreeNode node)
    {
    	Map<String,Object> j = new HashMap<String,Object>();
        {
            j.put("text", node.text);
            j.put("id", node.id);
            j.put("iconCls", node.iconCls);
        }

        Map<String,Object> attributes = new HashMap<String,Object>();
        {
            attributes.put("url",  node.url );
            attributes.put("openMode",  node.attributes.getOpenMode());
            attributes.put("windowWidth",  node.attributes.getWindowWidth());
            attributes.put("windowHeight",  node.attributes.getWindowHeight());
        }

//        node.attributes.openMode,
//        node.attributes.windowWidth,
//        node.attributes.windowHeight
        
        if (attributes.size() > 0)
        {
            j.put("attributes", attributes);
        }

        if (node.getChildren().size() > 0)
        {
            List<Object> jsubs = new ArrayList<Object>();

            j.put("children", jsubs);
            for (TreeNode sub : node.getChildren())
            {
            	Map<String,Object> jsub = serialize(sub);

                jsubs.add(jsub);
            }

            j.put("state", "closed");
        }

        return j;
    }
}
