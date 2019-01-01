package org.netsharp.panda.core.comunication;

import java.io.OutputStream;

public interface IResponse {
	void sendRedirect(String url);
	void write(String html);
	void reset();
	void setContentType(String contentType);
	void setHeader(String header,String value);
	void addCookie(String key,String value);
	OutputStream getOutputStream();
}
