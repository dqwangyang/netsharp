package org.netsharp.organization.controller.authorization.operation;

import org.netsharp.entity.CatEntity;
import org.netsharp.resourcenode.dic.ResourceType;
import org.netsharp.resourcenode.entity.ResourceNode;

public class OperationCategoryExtention {

	public static Boolean SetIsAuth(ResourceNode resourceNode) {
		
		OperationCategoryStruct operationCategoryStruct = (OperationCategoryStruct) resourceNode.getTag();
		if (resourceNode.getResourceType() == ResourceType.VOUCHER) {
			Boolean isAuth = GetIsAuth(resourceNode);
			operationCategoryStruct.setIsAuth(isAuth);
		} else {
			boolean isAuth = false;
			boolean isNoAuth = false;
			boolean isnull = false;

			for (CatEntity cat : resourceNode.getItems()) {
				ResourceNode sub = (ResourceNode) cat;
				Boolean subResult = SetIsAuth(sub);

				if (subResult == null) {
					isnull = true;
				} else if (!subResult) {
					isNoAuth = true;
				} else {
					isAuth = true;
				}
			}

			if (isnull) {
				operationCategoryStruct.setIsAuth(null);
			} else if (isNoAuth && isAuth) {
				operationCategoryStruct.setIsAuth(null);
			} else if (isNoAuth) {
				operationCategoryStruct.setIsAuth(false);
			} else {
				operationCategoryStruct.setIsAuth(true);
			}
		}

		return operationCategoryStruct.getIsAuth();
	}

	// 资源节点必须为单据类型
	public static Boolean GetIsAuth(ResourceNode resourceNode) {

		boolean isAuth = false;
		boolean isNoAuth = false;
		for (Object obj : resourceNode.getInnerValues().values()) {
			AuthorizationStruct authorizationStruct = (AuthorizationStruct) obj;
			if (authorizationStruct != null) {
				if (authorizationStruct.isHasAuth()) {
					if (!authorizationStruct.isAuth()) {
						isNoAuth = true;
					} else {
						isAuth = true;
					}
				}
			}
		}

		if (isAuth && isNoAuth) {
			return null;
		} else if (isNoAuth) {
			return false;
		} else {
			return true;
		}
	}

	public static void SetTopIsAuth(ResourceNode resourceNode) {
		ResourceNode parent = resourceNode;
		while (parent != null) {
			if (parent.getParent() == null) {
				SetIsAuth(parent);
			}

			parent = (ResourceNode) parent;
		}
	}
}
