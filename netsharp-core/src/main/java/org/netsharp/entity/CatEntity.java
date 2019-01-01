package org.netsharp.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.annotations.Category;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Exclusive;
import org.netsharp.resourcenode.entity.ResourceNode;

/*分类实体基类*/
public abstract class CatEntity extends ArchiveEntity  implements Serializable {

	private static final long serialVersionUID = -3318554621213322821L;

	@Exclusive
	@JsonIgnore
	public static int PATH_CODE_SIZE = 4;//分类码长度

	@Category(leafName="isLeaf",leafValue="1",pathCode="pathCode",pathName="pathName")
	@Column(name="parent_id",header="父节ID")
	private Integer parentId;

	@Column(size = 300,name="path_code",header="路径编码")
	private String pathCode;

	@Column(size = 500,name="path_name",header="路径名称")
	private String pathName;
	
	@Column(name="is_leaf",header="叶子节点")
	private Boolean isLeaf=true;

	@Exclusive
	@JsonIgnore
	private List<CatEntity> items = new ArrayList<>();

	@Exclusive
	@JsonIgnore
	private CatEntity parent = null;

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	@JsonIgnore
	public List<CatEntity> getItems() {
		return this.items;
	}

	@JsonIgnore
	public void setItems(List<CatEntity> items) {
		this.items = items;
	}

	public String getPathCode() {
		return pathCode;
	}

	public void setPathCode(String pathCode) {
		this.pathCode = pathCode;
	}

	public String getPathName() {
		return pathName;
	}

	public void setPathName(String pathName) {
		this.pathName = pathName;
	}

	@JsonIgnore
	public CatEntity getParent() {
		return parent;
	}

	@JsonIgnore
	public void setParent(CatEntity parent) {
		this.parent = parent;
	}

	// / 根据分类实体集合构造分类实体的树状关系
	// / 即设置每个分类实体的Parent和Items属性
	public static <T extends CatEntity> List<T> listToTree(List<T> list) {
		Mtable meta = MtableManager.getMtable(ResourceNode.class);

		List<T> tops = new ArrayList<T>();
		
		for(int i=0;i<list.size();i++){
			T t = list.get(i);
			if (meta.getId().isEmpty(t.getParentId())) {
				tops.add(t);
			}
		}

		for (T top : tops) {
			list.remove(top);
		}

		for (T top : tops) {
			listToTree(top, list);
		}

		return tops;
	}

	private static <T extends CatEntity> void listToTree(T t, List<T> list) {
		List<T> children = new ArrayList<T>();
		for (T x : list) {
			if (t.getId().equals(x.getParentId())) {
				children.add(x);
			}
		}

		if (t.getItems() == null) {
			t.setItems(new ArrayList<CatEntity>());
		}

		for (T child : children) {
			t.getItems().add(child);
			child.setParent(t);

			list.remove(child);
		}

		for (T child : children) {
			listToTree(child, list);
		}
	}
	
	// 树状关系生成线性列表集合
	// 树结构关系仍然保留
	public static <T extends CatEntity> List<T> treeToList(List<T> tree) {
		
		List<T> list = new ArrayList<T>();
		
		for(T t : tree){
			treeToList(t,list,tree);
		}

		return list;
	}
	
	@SuppressWarnings("unchecked")
	private static <T extends CatEntity> void treeToList(T item,List<T> list,List<T> tree) {
		
		list.add(item);
		
		if(item.getItems()==null){
			return;
		}
		
		for(CatEntity cat : item.getItems()){
			
			T t =(T)cat;
			treeToList(t,list,tree);
		}
	}
	
	public static <T extends CatEntity> void render(List<T> list,ICatRender<T> render) {
		
		for(T t : list){
			render(t,render);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T extends CatEntity> void render(T cat,ICatRender<T> render) {
		
		if(render.validate(cat)){
			render.execute(cat);
		}
		
		if(cat.getItems()==null){
			return;
		}
		
		for(CatEntity sub : cat.getItems()){
			
			T t =(T)sub;
			render(t,render);
		}
	}
	
	public Boolean getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public interface ICatRender<T extends CatEntity>{
		boolean validate(T t);
		void execute(T t);
	}
}
