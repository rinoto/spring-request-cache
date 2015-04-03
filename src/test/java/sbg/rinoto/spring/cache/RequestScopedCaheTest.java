package sbg.rinoto.spring.cache;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sbg.rinoto.spring.cache.services.CachedService;
import sbg.rinoto.spring.cache.services.SecondCachedService;

@ContextConfiguration(classes = { CacheConfig.class, RequestScopedCacheManager.class, CachedService.class,
		SecondCachedService.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class RequestScopedCaheTest {

	@Autowired
	CachedService cachedService;
	@Autowired
	SecondCachedService secondCachedService;
	@Autowired
	RequestScopedCacheManager cacheManager;

	@Before
	public void setup() {
		cachedService.clearCache();
	}

	@Test
	public void shouldGetNormalValue() {
		// given
		cachedService.setString("originalValue");
		// when-then
		assertThat(cachedService.getCachedString(), is("originalValue"));
	}

	@Test
	public void shouldCacheInThisThread() {
		// given
		cachedService.setString("originalValue");
		// cache value
		cachedService.getCachedString();
		// this one will be set, but not cached
		cachedService.setString("newValue");
		// when-then
		assertThat(cachedService.getCachedString(), is("originalValue"));
	}

	@Test
	public void shouldNotCacheInASecondThread() throws Exception {
		// given
		cachedService.setString("originalValue");
		// cache value
		cachedService.getCachedString();
		// this one will be set, but not cached
		cachedService.setString("newValue");
		// this thread still has the originalValue, but the new thread will
		// cache the newValue
		final ExecutorService executorService = Executors.newFixedThreadPool(1);
		final Future<String> futureForSecondThread = executorService.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				return cachedService.getCachedString();
			}
		});
		// when-then
		assertThat(cachedService.getCachedString(), is("originalValue"));
		assertThat(futureForSecondThread.get(), is("newValue"));
		executorService.shutdownNow();
	}

	@Test
	public void shouldSupportMultipleCaches() {
		// given
		cachedService.setString("originalValue");
		secondCachedService.setString("originalValueForSecondCache");
		// when-then
		assertThat(cachedService.getCachedString(), is("originalValue"));
		assertThat(secondCachedService.getCachedString(), is("originalValueForSecondCache"));
	}

	public void shouldClearCachesWhenClearingCacheManager() {
		// given
		cachedService.setString("originalValue");
		cacheManager.clearCaches();
		cachedService.setString("newValue");
		// when-then
		assertThat(cachedService.getCachedString(), is("newValue"));

	}

}
