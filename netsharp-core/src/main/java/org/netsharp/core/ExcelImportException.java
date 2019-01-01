package org.netsharp.core;

public class ExcelImportException extends NetsharpException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2805622964074282592L;
	private String code;

	public ExcelImportException() {
		super();
	}

	public ExcelImportException(String message) {
		super(message);
	}

	public ExcelImportException(String code, String message) {
		super(message);
		this.code = code;
	}

	public ExcelImportException(Throwable throwable) {
		super(throwable);
	}

	public ExcelImportException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
