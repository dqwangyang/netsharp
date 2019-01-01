package org.netsharp.panda.controls.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.util.OrdinalMap;

public class FormFieldGroup extends ArrayList<PFormField> {

	private static final long serialVersionUID = 1L;

	private String groupName;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public static Collection<FormFieldGroup> groupby(PForm pform) {

		OrdinalMap<String, FormFieldGroup> map = new OrdinalMap<String, FormFieldGroup>();
		List<PFormField> fields = pform.getFields();

		for (PFormField field : fields) {

			if (!field.isVisible()) {
				continue;
			}

			// String propertyName = field.getPropertyName();
			// boolean isPermission =
			// PermissionManager.isFieldGetway(pform.getEntityId(),
			// propertyName);
			// if(!isPermission){
			// continue;
			// }

			String groupName = field.getGroupName() == "" ? null : field.getGroupName();
			FormFieldGroup group = map.get(groupName);
			if (group == null) {

				group = new FormFieldGroup();
				{
					group.setGroupName(field.getGroupName());
				}

				map.put(groupName, group);
			}

			group.add(field);
		}

		return map.values();
	}
}
