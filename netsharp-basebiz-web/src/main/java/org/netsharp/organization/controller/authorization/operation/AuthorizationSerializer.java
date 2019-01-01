package org.netsharp.organization.controller.authorization.operation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.netsharp.entity.CatEntity;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.StringManager;

public class AuthorizationSerializer {

	private String[] columns;

	public List<Object> serialize(List<ResourceNode> nodes, String[] columns) {
		
		this.columns = columns;

		List<Object> jsonArray = new ArrayList<Object>();

		for (ResourceNode node : nodes) {

			HashMap<String, Object> json = new HashMap<String, Object>();

			jsonArray.add(json);

			serialize(json, node);
		}

		return jsonArray;
	}

	private void serialize(HashMap<String, Object> json, ResourceNode node) {
		
		json.put("id", node.getId());
		json.put("name", node.getName());
		for (String column : columns) {
			
			AuthorizationStruct cellValue = (AuthorizationStruct) node.get(column);
			if (cellValue != null) {
				List<String> ss = new ArrayList<String>();
				ss.add("onclick='controllerPermission.onImageClicked(this);'");
				ss.add("class='fa " + OperationTypeColumn.No + "'");
				ss.add("alt='" + node.getName() + "." + column + "'");
				ss.add("operationId='" + cellValue.getOperation().getId() + "'");
				ss.add("resourceNodeId='" + node.getId() + "'");
				ss.add("operationCode='" + column + "'");
				ss.add("pathCode='" + node.getPathCode() + "'");
				ss.add("collected='true'");

				String cellHtml = "<div style='width:100%;text-align:center;'><i " + StringManager.join(" ", ss) + "></i></div>";

				json.put(column, cellHtml);
			}
		}

		List<String> checksProperties = new ArrayList<String>();
		checksProperties.add("type='checkbox'");
		checksProperties.add("onclick='controllerPermission.onCheckboxClicked(this);'");
		checksProperties.add("isSelectionColumn='true'");
		checksProperties.add("id='" + node.getPathCode() + "'");
		checksProperties.add("pathCode='" + node.getPathCode() + "'");

		json.put("isSelectionColumn", "<input " + StringManager.join(" ", checksProperties)  + "/>");

		if (node.getItems() == null || node.getItems().size() == 0) {
			return;
		}
		json.put("state", "closed");
		ArrayList<Object> subArray = new ArrayList<Object>();
		json.put("children", subArray);

		for (CatEntity cat : node.getItems()) {
			ResourceNode subNode = (ResourceNode)cat;
			HashMap<String, Object> subJson = new HashMap<String, Object>();
			subArray.add(subJson);

			serialize(subJson, subNode);
		}
	}
}
