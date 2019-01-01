package org.netsharp.rest.core;

import javax.servlet.http.HttpServletResponse;

import org.netsharp.util.JsonManage;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

public class RestResultHandler implements HandlerMethodReturnValueHandler {
	
    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return true;
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
    	
        Class<?> methodReturnType = returnType.getMethod().getReturnType();
        if ("void".equals(methodReturnType.getName())) {
            mavContainer.setRequestHandled(true);
            return;
        }
        mavContainer.setRequestHandled(true);
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);{
        	response.setContentType("application/json;charset=UTF-8");
        }
        
        RestResult restResult = null;

        if (returnValue == null) {
        	restResult = RestResult.getSuccess(returnValue, "");
        } else if (returnValue instanceof String) {
        	restResult = RestResult.getSuccess(returnValue, returnValue.toString());
        } else if (returnValue instanceof RestResult) {
        	restResult = (RestResult)returnValue;
        } else {
        	restResult = RestResult.getSuccess(returnValue, "");
        }
        
        String json = JsonManage.serialize2(restResult);
        
        response.getWriter().append(json).flush();
    }
}
