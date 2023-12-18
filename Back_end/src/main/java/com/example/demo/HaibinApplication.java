package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.Filter;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.util.EnumSet;
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class HaibinApplication {

	public static void main(String[] args) {
		SpringApplication.run(HaibinApplication.class, args);
	}

}

/**
 * @author Rob Winch
 */
@Configuration
class CacheHandlerMappingIntrospectorConfig {
	@Bean
	static FilterRegistrationBean<Filter> handlerMappingIntrospectorCacheFilter(HandlerMappingIntrospector hmi) {
		Filter cacheFilter = hmi.createCacheFilter();
		FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>(cacheFilter);
		registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		registrationBean.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
		return registrationBean;
	}
}
