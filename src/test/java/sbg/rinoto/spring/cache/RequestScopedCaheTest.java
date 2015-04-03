package sbg.rinoto.spring.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sbg.rinoto.spring.cache.services.CachedService;

@ContextConfiguration(classes = { CacheConfig.class,
		RequestScopedCacheManager.class, CachedService.class, })
@RunWith(SpringJUnit4ClassRunner.class)
public class RequestScopedCaheTest {

	@Autowired
	CachedService cachedService;

	@Test
	public void shouldCacheInThisThread() {

	}
}
