package org.netsharp.rest.core;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
/**
 * ClassName: ExceptionController
 * @Description: TODO 异常拦截
 * @author hbpeng <hbpeng@gongsibao.com>
 * @date 2018/5/2 15:31
 */
@RestControllerAdvice
public class ExceptionController {

    /**
     * @Description:TODO
     * @param  ex
     * @return com.netsharp.rest.controller.result.ResponseData
     * @author hbpeng <hbpeng@gongsibao.com>
     * @date 2018/5/2 15:20
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public RestResult errorHandler(Exception ex) {
        return RestResult.getError(RestResult.EXCEPTION,ex.getMessage());
    }
}
