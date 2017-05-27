package com.chuisha;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.chuisha.conf.CSProperties;
import com.chuisha.web.filter.WebSiteMeshFilter;
import com.wondersgroup.util.mapper.WondersgroupMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Controller
@SpringBootApplication
@MapperScan(basePackages = "com.chuisha.mapper", markerInterface = WondersgroupMapper.class)
@EnableConfigurationProperties(CSProperties.class)
public class CsQuestionApplication extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(CsQuestionApplication.class, args);
	}
	@RequestMapping(value = "/main")
	public ModelAndView main() {
		return new ModelAndView("main");
	}
	@RequestMapping(value = "/main-student")
	public ModelAndView mainStudent() {
		return new ModelAndView("main-student");
	}

	@RequestMapping(value = "/")
	public ModelAndView index() {
		return new ModelAndView("index");
	}

	@RequestMapping(value = "/login")
	public ModelAndView login() {
		return new ModelAndView("login");
	}

	@RequestMapping(value = "/currentClassify")
	public ModelAndView currentClassify() {
		return new ModelAndView("manager/current-classify");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
	}

	/**
	 * 装饰器
	 * @return
	 * 2016年8月27日下午12:37:20
	 */
	@Bean
	public FilterRegistrationBean siteMeshFilter(){
		FilterRegistrationBean fitler = new FilterRegistrationBean();
		WebSiteMeshFilter siteMeshFilter = new WebSiteMeshFilter();
		fitler.setFilter(siteMeshFilter);
		return fitler;
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		super.configureMessageConverters(converters);

		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(
				SerializerFeature.PrettyFormat
		);
		fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
		fastConverter.setFastJsonConfig(fastJsonConfig);

		converters.add(fastConverter);
	}
}
