package com.spring.titlecharge;

import java.util.Arrays;
import java.util.Locale;

import javax.servlet.Filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@SpringBootApplication
public class TitlechargeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TitlechargeApplication.class, args);
	}
	
	@Bean
	public LocaleResolver localeResolver() {
		return new FixedLocaleResolver(new Locale("pt", "BR"));
	}
	@Bean
	public LayoutDialect layoutDialect() {
	    return new LayoutDialect();
	}
	@Bean
	public FilterRegistrationBean<Filter> hiddenHttpMethodFilter() {
		FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<Filter>(new HiddenHttpMethodFilter());
		filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
		return filterRegistrationBean;
	}
	
	@Configuration
	public static class MvcConfiguration implements WebMvcConfigurer{
		
		@Override
		public void addViewControllers(ViewControllerRegistry registry) {
			registry.addRedirectViewController("/", "/title");
		}
	}
}