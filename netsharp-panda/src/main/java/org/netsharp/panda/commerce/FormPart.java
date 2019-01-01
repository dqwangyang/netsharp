package org.netsharp.panda.commerce;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.core.Category;
import org.netsharp.core.Column;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.NotImplementException;
import org.netsharp.core.Oql;
import org.netsharp.core.Paging;
import org.netsharp.core.convertor.ITypeConvertor;
import org.netsharp.core.convertor.TypeConvertorFactory;
import org.netsharp.entity.IPersistable;
import org.netsharp.panda.commerce.base.FormPartBase;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.controls.utility.UrlHelper;
import org.netsharp.panda.core.JscriptType;
import org.netsharp.panda.core.comunication.IHtmlWriter;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.util.ReflectManager;
import org.netsharp.util.StringManager;

public class FormPart extends FormPartBase {

	Paging paging;

	public FormPart() {

	}

	public FormPart(Object obj) {
		this.obj = obj;
	}

	public IPersistable newInstance(Object par) {

		this.getService();

		IPersistable entity = this.service.newInstance();

		if (par != null) {
			// par 参数目前只有一种场景使用，即树结构的实体，传递父id
			Mtable meta = MtableManager.getMtable(this.context.getEntityId());
			Category cat = meta.getCategory();
			if (cat != null) {
				Class<?> type = cat.getProperty().getType();
				ITypeConvertor tc = TypeConvertorFactory.create(type);
				Object pvalue = tc.fromString(par.toString());
				cat.getProperty().field(entity, pvalue);
			}
		}

		return entity;
	}

	protected FormNavigation createFormNavigation(Object id) {

		this.getService();

		String entityId = this.context.getEntityId();
		Mtable meta = MtableManager.getMtable(entityId);

		Oql oql = new Oql();
		{
			oql.setEntityId(entityId);
			oql.setTag(id);
		}

		String orderby = meta.getOrderby();
		if (StringManager.isNullOrEmpty(orderby)) {
			Column column = meta.getPropertyOrColumn("createTime");
			if (column != null) {
				oql.setOrderby(column.getPropertyName() + " DESC");
			} else {
				oql.setOrderby(meta.getKeyColumn().getPropertyName() + " DESC");
			}
		} else {
			oql.setOrderby(orderby);
		}

		Paging paging = this.service.queryIndex(oql);

		FormNavigation navigation = new FormNavigation();
		{
			navigation.Paging = paging;
		}

		return navigation;
	}

	public FormNavigation byId(Object id) {

		this.getService();

		FormNavigation navigation = this.createFormNavigation(id);

		IPersistable obj = service.byId(id);
		if (obj == null) {
			navigation.Entity = this.newInstance(null);
		} else {
			navigation.Entity = obj;
		}

		return navigation;
	}

	/**
	 * @Title: convertVoucher
	 * @Description: 选单后转换单据使用
	 * @param @param sourceVoucherType 来源单据类型
	 * @param @param voucherId 单据id
	 * @param @param detailIds 单据明细ids,格式:1_2_3
	 * @param @param entity 当前单据实体
	 * @param @return
	 * @return IPersistable 返回类型
	 * @throws
	 */
	public IPersistable convertVoucher(String sourceVoucherType, Object voucherId, Object detailIds, IPersistable entity) {
		
		String[] ids = detailIds.toString().split("_");
		Integer[] array = new Integer[ids.length];
		for (int i = 0; i < ids.length; i++) {
			array[i] = Integer.parseInt(ids[i]);
		}
		return this.doConvertVoucher(sourceVoucherType, Integer.parseInt(voucherId.toString()), array, entity);
	}

	/**
	 * @Title: doConvertVoucher
	 * @Description: 执行转换(子类重写)
	 * @param @param sourceVoucherType 来源单据类型
	 * @param @param voucherId 单据id
	 * @param @param detailIds 单据明细ids,格式:1_2_3
	 * @param @param entity 当前单据实体
	 * @param @return
	 * @return IPersistable 返回类型
	 * @throws
	 */
	public IPersistable doConvertVoucher(String sourceVoucherType, Integer voucherId, Integer[] detailIds, IPersistable entity) {
		
		return null;
	}

	public IPersistable save(IPersistable entity) {

		this.getService();
		entity = this.service.save(entity);
		entity = this.service.byId(entity);
		return entity;
	}

	public IPersistable audit(IPersistable entity) {
		
		throw new NotImplementException();

//		this.getService();
//		entity = this.service.audit(entity);
//		return entity;
	}

	public IPersistable unAudit(IPersistable entity) {
		
		throw new NotImplementException();

//		this.getService();
//		entity = this.service.unAudit(entity);
//		return entity;
	}

	public IPersistable remove(String id, Paging paging) {
		this.getService();

		String entityId = this.context.getEntityId();

		Mtable meta = MtableManager.getMtable(entityId);

		IPersistable entity = (IPersistable) ReflectManager.newInstance(meta.getType());

		ITypeConvertor tc = TypeConvertorFactory.create(meta.getKeyColumn().getType());
		Object uid = tc.fromString(id);
		meta.setId(entity, uid);

		entity.toDeleted();

		entity = this.service.save(entity);

		Oql oql = new Oql();
		{
			oql.setEntityId(entityId);
			oql.setPaging(paging);
		}

		List<?> list = service.queryList(oql);
		if (list == null || list.size() <= 0) {
			return null;
		}

		return (IPersistable) list.get(0);
	}

	public IPersistable copy(Object id) {
		// Set set=this.getProxy().ById(this.entityId,
		// this.context.getService(), id);
		// if(set==null || set.main().size()==0)
		// {
		// throw new BusinessException("当前记录已经不存在，可能被其他人删除！");
		// }
		//
		// IPersistable old = set.firstItem();
		// IPersistable entity= old.Copy(true);
		//
		// return entity;

		return null;
	}

	public FormNavigation first() {
		this.getService();
		String entityId = this.context.getEntityId();
		FormNavigation fn = new FormNavigation();
		Oql oql = new Oql();
		{
			Mtable meta = MtableManager.getMtable(entityId);
			String orderby = meta.getOrderby();

			oql.setEntityId(entityId);
			oql.selects = "*";
			if (StringManager.isNullOrEmpty(orderby)) {
				Column column = meta.getPropertyOrColumn("createTime");
				oql.setOrderby(column.getPropertyName() + " DESC");
			} else {
				oql.setOrderby(orderby);
			}
		}
		IPersistable objFirst = this.service.queryFirst(oql);
		Mtable meta = MtableManager.getMtable(entityId);
		Object id = meta.getId(objFirst);
		IPersistable obj = service.byId(id);
		if (obj == null) {
			fn.Entity = this.newInstance(null);
			logger.debug("first未查到实体!");
		} else {
			fn.Entity = obj;
		}
		fn.Paging = oql.getPaging();
		return fn;
	}

	public FormNavigation pre(Object id, Paging paging) {

		this.getService();
		String entityId = this.context.getEntityId();
		FormNavigation fn = new FormNavigation();
		Oql oql = new Oql();
		{
			Mtable meta = MtableManager.getMtable(entityId);

			String orderby = meta.getOrderby();
			oql.setEntityId(entityId);
			if (StringManager.isNullOrEmpty(orderby)) {
				Column column = meta.getPropertyOrColumn("createTime");
				oql.setOrderby(column.getPropertyName() + " DESC");
			} else {
				oql.setOrderby(orderby);
			}
		}
		List<?> list = this.getService().queryList(oql);
		int i = 0;
		Mtable meta = MtableManager.getMtable(entityId);

		IPersistable entity = null;
		Object preId = id;
		List<Object> idList = new ArrayList<Object>();
		for (i = 0; i < list.size(); i++) {
			entity = (IPersistable) list.get(i);
			preId = meta.getId(entity);
			idList.add(preId);
			if (preId.equals(id))
				break;
		}
		if (i - 1 < 0)
			i = 1;
		IPersistable obj = service.byId(idList.get(i - 1));
		if (obj == null) {
			fn.Entity = this.newInstance(null);
			logger.debug("id(" + id + ")前一张未查到实体!");
		} else {
			fn.Entity = obj;
		}
		fn.Paging = oql.getPaging();
		return fn;
		// List<?> list = this.getService().queryList(oql);
		// if (list.size() > 0) {
		// fn.Entity = (IPersistable) list.get(0);
		// fn.Paging.setTotalCount(oql.getPaging().getTotalCount());
		// }
		// return fn;
	}

	public FormNavigation next(Object id, Paging paging) {
		this.getService();
		String entityId = this.context.getEntityId();
		FormNavigation fn = new FormNavigation();
		Oql oql = new Oql();
		{
			Mtable meta = MtableManager.getMtable(entityId);
			String orderby = meta.getOrderby();

			oql.setEntityId(entityId);
			if (StringManager.isNullOrEmpty(orderby)) {
				Column column = meta.getPropertyOrColumn("createTime");
				oql.setOrderby(column.getPropertyName() + " DESC");
			} else {
				oql.setOrderby(orderby);
			}
		}
		List<?> list = this.getService().queryList(oql);
		int i = 0;
		Mtable meta = MtableManager.getMtable(entityId);

		IPersistable entity = null;
		Object nextId = null;
		for (i = 0; i < list.size(); i++) {
			entity = (IPersistable) list.get(i);
			nextId = meta.getId(entity);
			if (nextId.equals(id))
				break;
		}
		if (i + 1 < list.size()) {
			entity = (IPersistable) list.get(i + 1);
			nextId = meta.getId(entity);
		}
		IPersistable obj = service.byId(nextId);
		if (obj == null) {
			fn.Entity = this.newInstance(null);
			logger.debug("id(" + id + ")下一张未查到实体!");
		} else {
			fn.Entity = obj;
		}
		fn.Paging = oql.getPaging();
		return fn;
	}

	public FormNavigation last(Object id) {
		this.getService();
		String entityId = this.context.getEntityId();
		FormNavigation fn = new FormNavigation();
		Oql oql = new Oql();
		{
			Mtable meta = MtableManager.getMtable(entityId);
			String orderby = meta.getOrderby();

			oql.setEntityId(entityId);
			if (StringManager.isNullOrEmpty(orderby)) {
				Column column = meta.getPropertyOrColumn("createTime");
				oql.setOrderby(column.getPropertyName() + " DESC");
			} else {
				oql.setOrderby(orderby);
			}
		}
		List<?> list = this.getService().queryList(oql);
		Mtable meta = MtableManager.getMtable(entityId);

		IPersistable entity = null;
		Object nextId = id;

		if (list.size() > 0) {
			entity = (IPersistable) list.get(list.size() - 1);
			nextId = meta.getId(entity);
		}
		IPersistable obj = service.byId(nextId);
		if (obj == null) {
			fn.Entity = this.newInstance(null);
			logger.debug("id(" + id + ")最后一张未查到实体!");
		} else {
			fn.Entity = obj;
		}
		fn.Paging = oql.getPaging();
		return fn;
	}

	@Override
	protected void importJs(IHtmlWriter writer) {

		PForm nform = this.context.getForm();
		PFormField field = null;
		for (PFormField x : nform.getFields()) {
			if (x.getControlType() == ControlTypes.EDITOR) {
				field = x;
				break;
			}
		}

		field = null;
		for (PFormField x : nform.getFields()) {
			if (x.getControlType() == ControlTypes.QINIUUPLOAD || x.getControlType() == ControlTypes.OSS_UPLOAD) {
				field = x;
				break;
			}
		}
		if (field != null) {
			writer.write(UrlHelper.getVersionScript("/package/qiniu/plupload.full.min.js"));
			writer.write(UrlHelper.getVersionScript("/package/qiniu/qiniu.js"));
		}
		super.importJs(writer);
	}

	@Override
	protected void addJscript() {
		
		super.addJscript();
		this.addJscript("        " + getJsInstance() + ".workspaceId=\"" + this.context.getWorkspaceId() + "\";", JscriptType.Header);
	}
}
