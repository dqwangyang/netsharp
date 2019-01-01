package org.netsharp.panda.controls.datagrid;

import java.util.ArrayList;

import org.netsharp.panda.annotation.EditorOption;
import org.netsharp.panda.annotation.EditorOptionAnnotation;
import org.netsharp.panda.controls.ControlPropertyMap;
import org.netsharp.panda.controls.EasyuiManager;
import org.netsharp.util.ReflectManager;
import org.netsharp.util.StringManager;

public class DataGridEditColumn extends DataGridColumn {
	public DataGridEditColumn() {
		super();
		this.editorOptions = new ArrayList<String>();
	}

	@EditorOption(html = "type", isOption = false, isEvent = false)
	public String type;

	@EditorOption(html = "required", isOption = true, isEvent = false)
	public boolean required = false;
	
	@EditorOption(html = "validType", isOption = true, isEvent = false)
	public String validType;

	@EditorOption(html = "options", isOption = true, isEvent = false)
	public String options = "";

	public ArrayList<String> editorOptions;

	@Override
	public void initialize() {
		super.initialize();
		this.createEditor();
	}

	public void createEditor() {
		
		ArrayList<String> Editors = new ArrayList<String>();
		ControlPropertyMap map = EasyuiManager.Find(this.getClass());
		for (EditorOptionAnnotation att : map.EditorOptionProperties) {
			
			java.lang.reflect.Field pi = att.Field;
			Object propertyValue = ReflectManager.get(pi, this);
			if (att.TypeConvertor.isNullOrEmpty(propertyValue)) {
				continue;
			}

			String str = null;
			if (att.isOption) {
				
				if (att.isEvent) {
					
					str = propertyValue.toString();
				} else {
					str = att.TypeConvertor.toJson(propertyValue);
				}

				if (str.startsWith("\"")) {
					
					str = StringManager.trim(str, '\"');
					str = "'" + str + "'";
				}

				if (str != null) {
					
					editorOptions.add(att.html + ":" + str);
				}

			} else {
				
				if (att.isEvent) {
					str = propertyValue.toString();
				} else {
					str = att.TypeConvertor.toJson(propertyValue);
				}

				if (str.startsWith("\"")) {
					
					str = StringManager.trim(str, '\"');
					str = "'" + str + "'";
				}

				if (str != null) {
					Editors.add(att.html + ":" + str);
				}
			}
		}

		String options = "";
		if (editorOptions.size() > 0) {
			
			options = StringManager.join(",", editorOptions);
			Editors.add("options:{" + options + "}");
		}

		if (Editors.size() > 0) {
			
			this.editor = "{" + StringManager.join(",", Editors) + "}";
		}
	}
}
