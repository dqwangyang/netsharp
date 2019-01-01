package org.netsharp.rest.core;
/**
 * ClassName: ResponseData
 * @Description: TODO 返回结果
 * @author hbpeng <hbpeng@gongsibao.com>
 * @date 2018/4/11 15:25
 */
public class RestResult {

    public static int SUCCESS = 200;
    public static int FAIL = -1;
    public static int UNLOGIN = 401;
    public static int UNAUTH = 403;
    public static int EXCEPTION = 500;

    public static final boolean SUCCESS_SUCCESS = true;
    public static final boolean SUCCESS_FAIL = false;

    private int code = 200;
    private String msg = "";
    private Object data;
    private Object extend;

    public static RestResult getSuccess(Object data, String msg) {
        RestResult result = new RestResult();
        result.setData(data);
        result.setMsg(msg);
        result.setCode(SUCCESS);
        return result;
    }


    public static RestResult getError(int code) {
        return getError(code, null);
    }

    public static RestResult getError(int code, String msg) {
        RestResult result = new RestResult();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static RestResult getError(Object data, int code, String msg) {
        RestResult result = new RestResult();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static RestResult getException() {
        RestResult result = new RestResult();
        result.setCode(EXCEPTION);
        result.setMsg("您的网络不稳定，请稍后再试。");
        return result;
    }

    public static RestResult getUnLogin() {
        RestResult result = new RestResult();
        result.setCode(UNAUTH);
        result.setMsg("用户未登录");
        return result;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getExtend() {
        return extend;
    }

    public void setExtend(Object extend) {
        this.extend = extend;
    }

}
