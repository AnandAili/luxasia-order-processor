package com.example.luxasiaorderprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableJpaRepositories
@EnableSwagger2
public class LuxasiaOrderProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(LuxasiaOrderProcessorApplication.class, args);
	}
	
	@Bean
	public Docket LuxAsiaApi() {
	    return new Docket(DocumentationType.SWAGGER_2)
	            .select()
	            .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
	            .paths(PathSelectors.any())
	            .build();            
	} 

}
