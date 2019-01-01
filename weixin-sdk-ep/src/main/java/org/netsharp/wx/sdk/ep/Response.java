package org.netsharp.wx.sdk.ep;

import java.io.Serializable;
import java.lang.reflect.Field;

public class Response implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -3905471166614448753L;
	private String jsonString;
    private String errcode;
    private String errmsg;

    public final String getJsonString() {
        return jsonString;
    }

    public final void setJsonString(String value) {
        jsonString = value;
    }

    public final String getErrcode() {
        return errcode;
    }

    public final void setErrcode(String value) {
        errcode = value;
    }

    public final String getErrmsg() {
        return errmsg;
    }

    public final void setErrmsg(String value) {
        errmsg = value;
    }

    @Override
    public String toString() {
        Field[] fields = this.getClass().getDeclaredFields();
        StringBuilder rs = new StringBuilder();
        for (Field field : fields) {
            rs.append(field.getName()).append(":");
            boolean isAccessible = field.isAccessible();
            if (!isAccessible) {
                field.setAccessible(true);
            }
            try {
                rs.append(field.get(this));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            field.setAccessible(isAccessible);
            rs.append(";");
        }
        return rs.toString();
    }
}