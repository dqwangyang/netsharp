package org.netsharp.panda.utils.plupload;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;


public class UploaderFilters {

	@JsonProperty(value = "mime_types")
	private List<MimeType> mimeTypes;
	
	@JsonProperty(value = "max_file_size")
	private String maxFileSize;
	
	@JsonProperty(value = "prevent_duplicates")
	private Boolean preventDuplicates = false;

	public List<MimeType> getMimeTypes() {
		return mimeTypes;
	}

	public void setMimeTypes(List<MimeType> mimeTypes) {
		this.mimeTypes = mimeTypes;
	}

	public String getMaxFileSize() {
		return maxFileSize;
	}

	public void setMaxFileSize(String maxFileSize) {
		this.maxFileSize = maxFileSize;
	}

	public Boolean getPreventDuplicates() {
		return preventDuplicates;
	}

	public void setPreventDuplicates(Boolean preventDuplicates) {
		this.preventDuplicates = preventDuplicates;
	}
	
	
//	filters: {
//	  mime_types : [ //只允许上传图片和zip文件
//	    { title : "Image files", extensions : "jpg,gif,png" }, 
//	    { title : "Zip files", extensions : "zip" }
//	  ],
//	  max_file_size : '400kb', //最大只能上传400kb的文件
//	  prevent_duplicates : true //不允许选取重复文件
//	},
}
