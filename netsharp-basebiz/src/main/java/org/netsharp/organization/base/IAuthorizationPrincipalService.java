package org.netsharp.organization.base;

import org.netsharp.base.IPersistableService;
import org.netsharp.organization.entity.AuthorizationPrincipal;
import org.netsharp.organization.entity.Organization;
import org.netsharp.organization.entity.Position;

public interface IAuthorizationPrincipalService  extends IPersistableService<AuthorizationPrincipal> {
	
	/**   
	 * @Title: addByPost   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param organization      
	 * @return: void      
	 * @throws   
	 */
	void addByPost(Organization organization);
	
	
	/**   
	 * @Title: deleteByPost   
	 * @Description: TODO(根据岗位Id删除授权主体)   
	 * @param: @param organizationId      
	 * @return: void      
	 * @throws   
	 */
	void deleteByPostId(Long organizationId);

	/**
	 * <p>方法名称：addByPosition</p>
	 * <p>方法描述：根据职务增加授权主体</p>
	 * @param position
	 * @author gaomeng
	 * @since  2016年2月25日
	 * <p> history 2016年2月25日 gaomeng  创建   <p>
	 */
	void addByPosition(Position position);
	
	/**
	 * <p>方法名称：deletePosition</p>
	 * <p>方法描述：根据职务删除授权主体</p>
	 * @param position
	 * @author gaomeng
	 * @since  2016年2月25日
	 * <p> history 2016年2月25日 gaomeng  创建   <p>
	 */
	void deleteByPosition(Position position);
}
