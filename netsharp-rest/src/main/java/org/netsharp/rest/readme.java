/**
 * 1.rest方法拦截接口HandlerInterceptorAdapter,并通过如下的方法进行注册
 @Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
	@Bean
	LoginCheckInterceptor newSessionInterceptor() {
		return new LoginCheckInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		// 登陆拦截器
		registry.addInterceptor(newSessionInterceptor()).addPathPatterns("/**");
	}
 * 
 * 2.servlet可以同tomcat下一样创建一个servlet的子类，且配置标注@WebServlet(name = "weixin", urlPatterns = "/wx")
 * **/