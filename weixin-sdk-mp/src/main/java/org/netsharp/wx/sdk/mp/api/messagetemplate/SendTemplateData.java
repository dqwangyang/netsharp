package org.netsharp.wx.sdk.mp.api.messagetemplate;

import java.util.HashMap;

public class SendTemplateData {

	private KeyNote first = new KeyNote("first");
	private HashMap<String,KeyNote> keynotes = new HashMap<String,KeyNote>();
	private KeyNote remark = new KeyNote("remark");
	

	public KeyNote getFirst() {
		return first;
	}


	public void setFirst(KeyNote first) {
		this.first = first;
	}

	public KeyNote getRemark() {
		return remark;
	}


	public void setRemark(KeyNote remark) {
		this.remark = remark;
	}

	public HashMap<String,KeyNote> getKeynotes() {
		return keynotes;
	}

	public void setKeynotes(HashMap<String,KeyNote> keynotes) {
		this.keynotes = keynotes;
	}
	
	public HashMap<String,KeyNote> getMap(){
		
		keynotes.put("first", this.first);
		keynotes.put("remark", this.remark);
		
		return keynotes;
	}
}
