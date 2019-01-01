package org.netsharp.plugin.core;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.core.NetsharpException;

public class AddInTreeNode {
	
//	 private HashMap<String, AddInTreeNode> childNodes = new HashMap<String, AddInTreeNode>();
//     private List<Codonable> codons = new ArrayList<Codonable>();

     public List<Object> BuildChildItems(Object caller)
     {
         List<Object> items = new ArrayList<Object>();
//         for (Codonable codon : codons)
//         {
//             List<Object> subItems = null;
//             if (childNodes.ContainsKey(codon.Id))
//             {
//                 subItems = childNodes.get(codon.Id).BuildChildItems(caller);
//             }
//             
//             Object result = codon.BuildItem(caller, subItems);
//
//             if (result != null)
//             {
//                 items.add(result);
//             }
//         }
         return items;
     }

     public Object BuildChildItem(String childItemID, Object caller, List<Object> subItems)
     {
//         for (Codonable codon : codons)
//         {
//             if (codon.Id == childItemID)
//             {
//                 return codon.BuildItem(caller, subItems);
//             }
//         }
         throw new NetsharpException(childItemID);
     }
}
