package sbg.rinoto.spring.cache.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

	@Bean
	public CacheHandlerInterceptor cacheHandlerInterceptor() {
		return new CacheHandlerInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		System.out.println("Registering interceptor!!!!");
		registry.addInterceptor(cacheHandlerInterceptor()).addPathPatterns("/**");
	}

}
