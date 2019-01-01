package org.netsharp.rest.core.annotation;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * ClassName: WebMvcRegistrationsConfig
 * @Description: TODO 注册自定义Api Mapping
 * @author hbpeng <hbpeng@gongsibao.com>
 * @date 2018/5/9 14:29
 */
@Configuration
public class WebMvcRegistrationsConfig implements WebMvcRegistrations {
    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return  new ApiRequestMapping();
    }
}
