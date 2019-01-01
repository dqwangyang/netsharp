package org.netsharp.wx.sdk.mp.api.accesstoken;

import java.io.Serializable;
import java.util.Date;

public class AccessToken implements Serializable {
    private static final long serialVersionUID = 7277592294763881064L;

    private Integer id;
    private String  appId;
    private String  appSecret;
    private Date    createTime;
    private String  token;
    private String  encryptionKey;
    
    private boolean isExpired;

    public AccessToken() {
        setCreateTime(null);
    }

    public final Integer getId() {
        return id;
    }

    public final void setId(Integer value) {
        id = value;
    }

    public final String getAppId() {
        return appId;
    }

    public final void setAppId(String value) {
        appId = value;
    }


    public final String getAppSecret() {
        return appSecret;
    }

    public final void setAppSecret(String value) {
        appSecret = value;
    }


    public final Date getCreateTime() {
        return createTime;
    }

    public final void setCreateTime(Date value) {
        createTime = value;
    }

    public final String getToken() {
        return token;
    }

    public final void setToken(String value) {
        token = value;
    }

//    public final boolean getIsExpired() {
//        if (StringManager.isNullOrEmpty(getToken())) {
//            return true;
//        }
//
//        if (getCreateTime() == null) {
//            return true;
//        }
//
//        // 提前十分钟让token过期，并重新获取，减少被动获取token可能出现的token过期的几率
//        if ((getCreateTime().getTime() + 7200 * 1000 - 60 * 10 * 1000) >= System.currentTimeMillis()) {
//            return true;
//        }
//
//        return false;
//    }
    
    


    public static boolean OpEquality(AccessToken lvalue, AccessToken rvalue) {
        if (lvalue == null || rvalue == null) {
            return false;
        }

        if (!lvalue.getId().equals(rvalue.getId())) {
            return false;
        }

        if (!lvalue.getAppId().equals(rvalue.getAppId())) {
            return false;
        }

        if (!lvalue.getAppSecret().equals(rvalue.getAppSecret())) {
            return false;
        }

        if (!lvalue.getToken().equals(rvalue.getToken())) {
            return false;
        }

        return true;
    }

    public static boolean OpInequality(AccessToken lvalue, AccessToken rvalue) {
        return !(AccessToken.OpEquality(lvalue, rvalue));
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public String getEncryptionKey() {
        return encryptionKey;
    }

    public void setEncryptionKey(String encryptionKey) {
        this.encryptionKey = encryptionKey;
    }

	public void setExpired(boolean isExpired) {
		this.isExpired = isExpired;
	}

	public boolean isExpired() {
		return isExpired;
	}
}