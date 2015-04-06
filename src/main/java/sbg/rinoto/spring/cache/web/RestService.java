package sbg.rinoto.spring.cache.web;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sbg.rinoto.spring.cache.services.SlowService;

@RestController
public class RestService {

	@Autowired
	SlowService slowService;

	@RequestMapping("/")
	String home() {
		return IntStream.range(0, 10).mapToObj(i -> i + " -" + slowService.getString()).collect(Collectors.joining());
	}
}
