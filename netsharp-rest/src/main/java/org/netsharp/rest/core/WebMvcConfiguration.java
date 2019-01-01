package org.netsharp.rest.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.DeferredResultMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: WebMvcConfiguration
 * 
 * @Description: TODO 修改Handler 加载顺序
 * @author hbpeng <hbpeng@gongsibao.com>
 * @date 2018/5/9 14:30
 */
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurationSupport {
	@Autowired
	private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

	@Bean
	public HandlerMethodReturnValueHandler completableFutureReturnValueHandler() {
		return new RestResultHandler();
	}

	@PostConstruct
	public void init() {
		final List<HandlerMethodReturnValueHandler> originalHandlers = new ArrayList<>(requestMappingHandlerAdapter.getReturnValueHandlers());

		final int deferredPos = obtainValueHandlerPosition(originalHandlers, DeferredResultMethodReturnValueHandler.class);
		// Add our handler directly after the deferred handler.
		originalHandlers.add(deferredPos + 1, completableFutureReturnValueHandler());

		requestMappingHandlerAdapter.setReturnValueHandlers(originalHandlers);
	}

	private int obtainValueHandlerPosition(final List<HandlerMethodReturnValueHandler> originalHandlers, Class<?> handlerClass) {
		for (int i = 0; i < originalHandlers.size(); i++) {
			final HandlerMethodReturnValueHandler valueHandler = originalHandlers.get(i);
			if (handlerClass.isAssignableFrom(valueHandler.getClass())) {
				return i;
			}
		}
		return -1;
	}
}
