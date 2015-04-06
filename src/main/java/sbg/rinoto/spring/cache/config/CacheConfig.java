package sbg.rinoto.spring.cache.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {

	@Bean
	public CacheManager requestScopedCacheManager() {
		RequestScopedCacheManager cacheManager = new RequestScopedCacheManager();
		return cacheManager;
	}

}
