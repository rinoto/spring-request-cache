package sbg.rinoto.spring.cache.services;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheManager = "requestScopedCacheManager", cacheNames = "cachedService")
public class CachedService {

	private String string;

	@Cacheable
	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

}
