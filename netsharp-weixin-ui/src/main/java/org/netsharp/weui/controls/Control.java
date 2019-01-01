package org.netsharp.weui.controls;

import java.util.ArrayList;



public class Control {

	public ArrayList<Control> controls = new ArrayList<Control>();
//
//	protected int level; // 控件级次，根据级次生成html的缩进格式
//
//	@HtmlAttr(html = "id")
//	public String id;
//
//	@HtmlAttr(html = "style")
//	public String style;
//
//	@HtmlAttr(html = "class")
//	public String className;
//
//	@HtmlAttr(html = "name")
//	public String name;
//
//	@HtmlAttr(html = "border")
//	public String isBorder;
//
//	@HtmlAttr(html = "seq")
//	public BigDecimal seq;
//
//	@HtmlAttr(html = "code")
//	public String code;
//
//	public ArrayList<String> dataOptions;
//
//	public HashMap<String, String> innerValues = new HashMap<String, String>();
//	
//	public int getLevel() {
//		return level;
//	}
//
//	public void setLevel(int level) {
//		this.level = level;
//	}
//
//	public String getId() {
//		return id;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}
//
//	public String getStyle() {
//		return style;
//	}
//
//	public void setStyle(String style) {
//		this.style = style;
//	}
//
//	public String getClassName() {
//		return className;
//	}
//
//	public void setClassName(String className) {
//		this.className = className;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getIsBorder() {
//		return isBorder;
//	}
//
//	public void setIsBorder(String isBorder) {
//		this.isBorder = isBorder;
//	}
//
//	public BigDecimal getSeq() {
//		return seq;
//	}
//
//	public void setSeq(BigDecimal seq) {
//		this.seq = seq;
//	}
//
//	public String getCode() {
//		return code;
//	}
//
//	public void setCode(String code) {
//		this.code = code;
//	}
//
//	public ArrayList<String> getDataOptions() {
//		return dataOptions;
//	}
//
//	public void setDataOptions(ArrayList<String> dataOptions) {
//		this.dataOptions = dataOptions;
//	}
//
//	public HashMap<String, String> getInnerValues() {
//		return innerValues;
//	}
//
//	public void setInnerValues(HashMap<String, String> innerValues) {
//		this.innerValues = innerValues;
//	}
//
//	public Control() {
//		super();
//		this.dataOptions = new ArrayList<String>();
//	}
//
	public void initialize() {
//		for (Control control : this.getControls()) {
//
//			control.level = this.level + 1;
//			control.initialize();
//		}
	}
//
//	public void render(IHtmlWriter writer) {
////		String spaces = this.getSpaces();
//
//		HtmlNode attr = this.getClass().getAnnotation(HtmlNode.class);
//		if (attr == null) {
//			System.out.println(this.getClass().getName());
//		}
//
//		if (this instanceof PlainHtml) {
//			PlainHtml plianHtml = (PlainHtml) this;
//			writer.write(plianHtml.value);
//		}
//
//		if (this.getControls().size() > 0) {
//			//
//			String htmlAttributes = this.renderProperties();
//			String htmlNode = this.Node(attr.html(), htmlAttributes, false);
//			writer.write(htmlNode);
//
//			//
//			for (Control control : this.controls) {
//				control.render(writer);
//			}
//
//			//
//			if (attr.isValue()) {
//				Field pi = ReflectManager.getField(this.getClass(), "value");
//				String value = (String) ReflectManager.get(pi, this);
//
//				if (value != null) {
//					writer.write(value);
//				}
//			}
//
//			//
//			//writer.write(spaces + "</" + attr.html() + ">");
//			writer.write("</" + attr.html() + ">");
//		} else if (attr != null) {
//			if (attr.isValue()) {
//				//
//				writer.write(this.Node(attr.html(), this.renderProperties(), false));
//
//				//
//				Field pi = ReflectManager.getField(this.getClass(), "value");
//				String value = (String) ReflectManager.get(pi, this);
//
//				if (value != null) {
//					writer.write(value);
//				}
//
//				//
//				//writer.write(spaces + "</" + attr.html() + ">");
//				writer.write("</" + attr.html() + ">");
//			} else {
//				writer.write(this.Node(attr.html(), this.renderProperties(), true));
//			}
//		}
//	}
//
//	public String CreateHtml() {
//		StringBuilder htmlBuilder = new StringBuilder();
//
//		HtmlNode attr = (HtmlNode) this.getClass().getAnnotation(HtmlNode.class);
//
//		if (this.getControls().size() > 0) {
//			//
//			htmlBuilder.append(this.Node(attr.html(), this.renderProperties(), false));
//
//			//
//			for (Control control : this.controls) {
//				htmlBuilder.append(control.CreateHtml()).append(StringManager.NewLine);
//			}
//
//			//
//			if (attr.isValue()) {
//				Field pi = ReflectManager.getField(this.getClass(), "value");
//				String value = (String) ReflectManager.get(pi, this);
//
//				htmlBuilder.append(value).append(StringManager.NewLine);
//			}
//
//			//
//			htmlBuilder.append("</" + attr.html() + ">").append(StringManager.NewLine);
//		} else {
//			htmlBuilder.append(this.Node(attr.html(), this.renderProperties(), false));
//
//			if (attr.isValue()) {
//				//
//				Field pi = ReflectManager.getField(this.getClass(), "value");
//				String value = (String) ReflectManager.get(pi, this);
//
//				htmlBuilder.append(value).append(StringManager.NewLine);
//
//				//
//				htmlBuilder.append("</" + attr.html() + ">").append(StringManager.NewLine);
//			}
//		}
//
//		return htmlBuilder.toString();
//	}
//
//	protected String renderProperties() {
//		ControlPropertyMap map = EasyuiManager.Find(this.getClass());
//		ArrayList<String> ss = new ArrayList<String>();
//
//		for (HtmlAttrAnnotation att : map.HtmlProperties) {
//			String propertyValue = PropertyValue(att);
//
//			if (!StringManager.isNullOrEmpty(propertyValue) || att.mustWrite) {
//				ss.add(att.html + "=\"" + propertyValue + "\"");
//			}
//		}
//
//		// DataOptions
//		for (DataOptionAnnotation att : map.DataOptionProperties) {
//			Field pi = att.Field;
//			Object propertyValue = ReflectManager.get(pi, this);
//
//			if (att.TypeConvertor.isNullOrEmpty(propertyValue)) {
//				continue;
//			}
//
//			String str = null;
//
//			if (att.isEvent) {
//				str = (String) propertyValue;
//			} else {
//				str = att.TypeConvertor.toJson(propertyValue);
//			}
//			
//			if(str==null){
//				System.out.println(this.getClass().getName()+"["+att.html+"] is null");
//			}
//
//			if (str.startsWith("\"")) {
//				str = StringManager.trim(str, '\"');
//
//				str = "'" + str + "'";
//			}
//
//			this.dataOptions.add(att.html + ":" + str);
//		}
//
//		if (this.dataOptions.size() > 0) {
//			ss.add("data-options=\"" + StringManager.join(",", this.dataOptions) + "\"");
//		}
//
//		// InnerValues
//		Iterator<Entry<String, String>> iter = this.innerValues.entrySet().iterator();
//		while (iter.hasNext()) {
//			Entry<String, String> kv = (Entry<String, String>) iter.next();
//			ss.add(kv.getKey() + "=\"" + kv.getValue() + "\"");
//		}
//
//		String returnValue = StringManager.join(" ", ss);
//
//		if (StringManager.isNullOrEmpty(returnValue)) {
//			return null;
//		} else {
//			return returnValue;
//		}
//	}
//
//	private String PropertyValue(HtmlPropertyAnnotation att) {
//		if (att.serializeType != IControlPropertySerializer.class) {
//			IControlPropertySerializer serizer = (IControlPropertySerializer) ReflectManager.newInstance(att.serializeType);
//
//			return serizer.Serialize(this);
//		}
//
//		Field pi = att.Field;
//
//		Object propertyValue = ReflectManager.get(pi, this);
//
//		if (propertyValue == null) {
//			return null;
//		}
//
//		String strPropertyValue = null;
//
//		//
//		strPropertyValue = propertyValue.toString();
//
//		if (strPropertyValue == att.defaultValue) {
//			return null;
//		}
//
//		//
//		if (pi.getType() == boolean.class || pi.getType() == Boolean.class) {
//			strPropertyValue = strPropertyValue.toLowerCase();
//
//			if (strPropertyValue.equals("false")) {
//				strPropertyValue = null;
//			}
//		}
//
//		return strPropertyValue;
//	}
//
//	protected String Node(String html, String properties, boolean isClose) {
//		String spaces = this.getSpaces();
//
//		if (isClose) {
//			if (StringManager.isNullOrEmpty(properties)) {
//				return spaces + "<" + html + "/>";
//			} else {
//				return spaces + "<" + html + " " + properties + "/>";
//			}
//		} else {
//			if (StringManager.isNullOrEmpty(properties)) {
//				return spaces + "<" + html + ">";
//			} else {
//				return spaces + "<" + html + " " + properties + ">";
//			}
//		}
//	}
//
//	public Control find(String id) {
//		
//		if (StringManager.equals(this.id ,id)) {
//			return this;
//		}
//
//		for (Control control : this.controls) {
//			Control subControl = control.find(id);
//
//			if (subControl != null) {
//				return subControl;
//			}
//		}
//
//		return null;
//	}
//
	public ArrayList<Control> getControls() {
		return controls;
	}

	public void setControls(ArrayList<Control> controls) {
		this.controls = controls;
	}
//
//	protected String getSpaces() {
//		String space = "    ";
//		String spaces = "";
//		for (int i = 0; i < this.level; i++) {
//			spaces += space;
//		}
//
//		return spaces;
//	}
//
//	public String getRequest(String index) {
//		
//		IRequest request = HttpContext.getCurrent().getRequest();
//		String item = request.getParameter(index);
//
//		if (item != null && item.contains("{@UserId}")) {
//			String userId = "在View中未被替换的{@UserId}";
//			item = item.replaceAll("{@UserId}", userId);
//		}
//
//		return item;
//	}
//
//	public Object getSession(String key) {
//		IRequest request = HttpContext.getCurrent().getRequest();
//		return request.getSession(key);
//	}
//
//	public void setSession(String key, Object value) {
//		IRequest request = HttpContext.getCurrent().getRequest();
//		request.setSession(key, value);
//	}
}
