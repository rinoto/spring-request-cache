package sbg.rinoto.spring.cache.config;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.stereotype.Component;

/**
 * {@link org.springframework.cache.CacheManager} that stores the caches in a ThreadLocal variable, and, therefore,
 * caching the values for the current thread. Useful if caching is just needed on a per request basis.
 * <p>
 * IMPORTANT: This cachedManager needs to be cleared before each request by using {@link #clearCaches()}. It is also
 * advisable to clear the cache also after the request has completed, in order to free up the cache.
 * 
 * @author rinoto
 */
@Component
public class RequestScopedCacheManager implements CacheManager {

	private static final ThreadLocal<Map<String, Cache>> threadLocalCache = new ThreadLocal<Map<String, Cache>>() {
		@Override
		protected Map<String, Cache> initialValue() {
			return new ConcurrentHashMap<String, Cache>();
		}
	};

	@Override
	public Cache getCache(String name) {
		final Map<String, Cache> cacheMap = threadLocalCache.get();
		Cache cache = cacheMap.get(name);
		if (cache == null) {
			cache = createCache(name);
			cacheMap.put(name, cache);
		}
		return cache;
	}

	private Cache createCache(String name) {
		return new ConcurrentMapCache(name);
	}

	@Override
	public Collection<String> getCacheNames() {
		return threadLocalCache.get().keySet();
	}

	public void clearCaches() {
		threadLocalCache.remove();
	}

}
