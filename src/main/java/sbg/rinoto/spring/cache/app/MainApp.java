package sbg.rinoto.spring.cache.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan("sbg.rinoto.spring.cache")
public class MainApp {

	// @RequestMapping("/")
	// String home() {
	// System.out.println("hola!!");
	// return "Hello Mundo!";
	// }

	public static void main(String[] args) throws Exception {
		SpringApplication.run(MainApp.class, args);
	}

}