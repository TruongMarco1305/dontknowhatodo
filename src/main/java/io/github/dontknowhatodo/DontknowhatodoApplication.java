package io.github.dontknowhatodo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RestController;

import io.github.dontknowhatodo.config.AppProperties;

@RestController
@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class DontknowhatodoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DontknowhatodoApplication.class, args);
	}
}
