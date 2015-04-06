package sbg.rinoto.spring.cache.services;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheManager = "requestScopedCacheManager", cacheNames = "cachedService")
public class SlowService {

	@Cacheable
	public String getString() {
		try {
			Thread.sleep(200L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "hola";
	}

}
