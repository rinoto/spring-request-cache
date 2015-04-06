package sbg.rinoto.spring.cache.web;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

@Service
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class RequestContextHolder {

	public String cacheType;

}
