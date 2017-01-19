package com.wondersgroup.tpa;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.wondersgroup.util.mapper.WondersgroupMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Controller
@EnableWebMvc
@SpringBootApplication
@MapperScan(basePackages = "com.wondersgroup.tpa.mapper", markerInterface = WondersgroupMapper.class)
public class TpaAgricultureWebApplication extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(TpaAgricultureWebApplication.class, args);
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		super.configureMessageConverters(converters);

		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(
				SerializerFeature.PrettyFormat
		);
		fastConverter.setFastJsonConfig(fastJsonConfig);

		converters.add(fastConverter);
	}

	@RequestMapping(value = "/main")
	public ModelAndView main(){
		return new ModelAndView("main");
	}
//
//	/**
//	 * （1）在启动类App.java类中继承：WebMvcConfigurerAdapter
//	 * （2）覆盖方法：configureContentNegotiation
//	 * <p>
//	 * favorPathExtension表示支持后缀匹配，
//	 * 属性ignoreAcceptHeader默认为fasle，表示accept-header匹配，defaultContentType开启默认匹配。
//	 * 例如：请求aaa.xx，若设置<entry key="xx" value="application/xml"/> 也能匹配以xml返回。
//	 * 根据以上条件进行一一匹配最终，得到相关并符合的策略初始化ContentNegotiationManager
//	 */
//	@Override
//	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//
////        configurer.favorPathExtension(true);
//		configurer.ignoreAcceptHeader(true);
//
//		configurer.mediaType("html", MediaType.TEXT_HTML);
//		configurer.mediaType("xml",MediaType.APPLICATION_XML);
//		configurer.mediaType("json",MediaType.APPLICATION_JSON);
//
//		configurer.defaultContentType(MediaType.TEXT_HTML);
//
//	}
//
//	/*
//     * Configure ContentNegotiatingViewResolver
//     */
//	@Bean
//	public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
//		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
//		resolver.setContentNegotiationManager(manager);
//
//		// Define all possible view resolvers
//		List<ViewResolver> resolvers = new ArrayList<ViewResolver>();
//		resolvers.add(new ViewResolver() {
//			@Override
//			public View resolveViewName(String s, Locale locale) throws Exception {
//				return new FastJsonJsonView();
//			}
//		});
//
//		resolvers.add(new ViewResolver() {
//			@Override
//			public View resolveViewName(String s, Locale locale) throws Exception {
//				MarshallingView view = new MarshallingView();
//				view.setMarshaller(new XStreamMarshaller());
//				return view;
//			}
//		});
//
//		resolver.setViewResolvers(resolvers);
//		return resolver;
//	}

}
