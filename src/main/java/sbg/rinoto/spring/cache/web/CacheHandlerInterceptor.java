package sbg.rinoto.spring.cache.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import sbg.rinoto.spring.cache.config.RequestScopedCacheManager;

public class CacheHandlerInterceptor extends HandlerInterceptorAdapter implements ApplicationContextAware {

	@Autowired
	private RequestScopedCacheManager requestScopedCacheManager;

	private ApplicationContext applicationContext;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		requestScopedCacheManager.clearCaches();

		final RequestContextHolder requestContextHolder = applicationContext.getBean(RequestContextHolder.class);
		System.out.println("Holder: " + requestContextHolder.cacheType);
		String header = request.getHeader("X-RINOTO-CACHE-TYPE");
		System.out.println("Header: " + header);
		requestContextHolder.cacheType = header;
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		requestScopedCacheManager.clearCaches();
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
