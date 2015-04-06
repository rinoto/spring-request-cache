# spring-request-cache


The <code>RequestScopedCacheManager</code> is an implementation of <code>org.springframework.cache.CacheManager</code> that stores the caches in a ThreadLocal variable, and, therefore,
 caching the values for the current thread. Useful if caching is just needed on a per request basis.
 
### Usage
Just register the <code>RequestScopedCacheManager</code> as a <code>@Bean</code>
 
```java
@Configuration
@EnableCaching
public class CacheConfig {

	@Bean
	public CacheManager requestScopedCacheManager() {
		RequestScopedCacheManager cacheManager = new RequestScopedCacheManager();
		return cacheManager;
	}

}
``` 
and reference it in your Cache annotations (e.g. with <code>@CacheConfig</code>) 

```java
@Service
@CacheConfig(cacheManager = "requestScopedCacheManager", cacheNames = "nameIt")
public class ServiceThatUsesRequestScopedCache {
``` 
The cacheManager can also be specified in the <code>@Cacheable</code> annotations and co. 
 
### Cleaning up the cache
IMPORTANT: This cachedManager needs to be cleared before each request by using <code>requestScopedCacheManager.clearCaches()</code> (see an example in the tests).
It is also advisable to clear the cache also after the request has completed, in order to free up the cache.



ab -c 5 -n 10 -H "X-RINOTO-CACHE-TYPE:hola2" localhost:8080/

mvn spring-boot:run


 