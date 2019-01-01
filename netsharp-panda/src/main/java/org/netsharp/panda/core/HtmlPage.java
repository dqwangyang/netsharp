package org.netsharp.panda.core;

import java.util.ArrayList;

import org.netsharp.panda.controls.Control;
import org.netsharp.panda.controls.other.Body;
import org.netsharp.panda.core.comunication.IHtmlWriter;
import org.netsharp.panda.entity.PWorkspace;

/**   
 * @ClassName:  HtmlPage   
 * @Description:TODO
 * @author: 韩伟
 * @date:   2017年11月4日 上午10:48:06   
 *     
 * @Copyright: 2017 www.yikuaxiu.com Inc. All rights reserved. 
 */
public class HtmlPage extends Control {

	public String title;
	public HttpContext httpContext;
	private PWorkspace pworkspace;

	protected Body body = new Body();

	ArrayList<String> jscripts = new ArrayList<String>();
	ArrayList<String> jscriptsi = new ArrayList<String>();
	
	public void preInitialize() {
		
	}

	/**   
	 * <p>Title: initialize</p>   
	 * <p>Description: </p>      
	 * @see org.netsharp.panda.controls.Control#initialize()   
	 */
	@Override
	public void initialize() {
		
		this.getControls().add(body);
		super.initialize();
	}

	/**   
	 * <p>Title: render</p>   
	 * <p>Description: </p>   
	 * @param writer   
	 * @see org.netsharp.panda.controls.Control#render(org.netsharp.panda.core.comunication.IHtmlWriter)   
	 */
	@Override
	public void render(IHtmlWriter writer) {
		
		writer.write("<!DOCTYPE html>");
		writer.write("<html>");
		writer.write("<head>");
		writer.write("    <title>" + this.title + "</title>");
		importCss(writer);
		writer.write("</head>");

		for (Control control : this.getControls()) {
			
			control.render(writer);
		}

		importJs(writer);
		renderJscript(writer);
		writer.write("</html>");
		this.onRended(writer);
	}

	/**   
	 * @Title: renderJscript   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param writer      
	 * @return: void      
	 * @throws   
	 */
	protected void renderJscript(IHtmlWriter writer) {
		
		if (jscripts.size() == 0 && jscriptsi.size() == 0) {
			
			return;
		}

		writer.write("    <script type=\"text/javascript\">");
		for (String script : jscripts) {
			writer.write(script);
		}

		if (jscriptsi.size() > 0) {
			
			writer.write("        ");
			writer.write("        //");
			writer.write("        $(function() {");
			for (String script : jscriptsi) {
				
				writer.write(script);
			}
			writer.write("        });");
		}

		writer.write("    </script>");
	}

	/**   
	 * @Title: addJscript   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param script
	 * @param: @param type      
	 * @return: void      
	 * @throws   
	 */
	public void addJscript(String script, JscriptType type) {
		
		if (type == JscriptType.Header) {
			
			this.jscripts.add(script);
		} else if (type == JscriptType.Initialize) {
			
			this.jscriptsi.add(script);
		}
	}

	/**   
	 * @Title: createPage   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:       
	 * @return: void      
	 * @throws   
	 */
	public void createPage() {
		
	}

	/**   
	 * @Title: onRended   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param writer      
	 * @return: void      
	 * @throws   
	 */
	protected void onRended(IHtmlWriter writer) {
		
	}

	/**   
	 * @Title: importCss   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param writer      
	 * @return: void      
	 * @throws   
	 */
	protected void importCss(IHtmlWriter writer) {
		
	}
	
	/**   
	 * @Title: importJs   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param writer      
	 * @return: void      
	 * @throws   
	 */
	protected void importJs(IHtmlWriter writer) {
		
	}

	/**   
	 * @Title: isStaticable   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return      
	 * @return: boolean      
	 * @throws   
	 */
	public boolean isStaticize() {
		
		return false;
	}

	/**   
	 * @Title: getPworkspace   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return      
	 * @return: PWorkspace      
	 * @throws   
	 */
	/**   
	 * @Title: getPworkspace   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return      
	 * @return: PWorkspace      
	 * @throws   
	 */
	public PWorkspace getPworkspace() {
		
		return pworkspace;
	}

	/**   
	 * @Title: setPworkspace   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param pworkspace      
	 * @return: void      
	 * @throws   
	 */
	public void setPworkspace(PWorkspace pworkspace) {
		
		this.pworkspace = pworkspace;
	}
}
