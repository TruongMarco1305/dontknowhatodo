package io.github.dontknowhatodo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableAutoConfiguration
public class DontknowhatodoApplication {

	@RequestMapping("/")
    @SuppressWarnings("unused")
	String home() {
		return "Welcome to Dontknowhatodo";
	}

	public static void main(String[] args) {
		SpringApplication.run(DontknowhatodoApplication.class, args);
	}

}
