package io.github.dontknowhatodo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableAutoConfiguration
public class DontknowhatodoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DontknowhatodoApplication.class, args);
	}
}
