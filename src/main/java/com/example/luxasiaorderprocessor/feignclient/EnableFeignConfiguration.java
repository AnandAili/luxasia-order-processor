package com.example.luxasiaorderprocessor.feignclient;

import feign.RequestInterceptor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients
@Configuration
public class EnableFeignConfiguration {

	@Bean
	RequestInterceptor requestInterceptor1() {
		return (requestTemplate) -> {
			//requestTemplate.header("main-traceId", "mian-traceid");
		};
	}


}
