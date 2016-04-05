package com.tsystems.JavaSchool.ShopOnline.config;


import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Person;
import com.tsystems.JavaSchool.ShopOnline.Services.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsFileUploadSupport;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

@Configuration
@ComponentScan(basePackages="com.tsystems.JavaSchool.ShopOnline")
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter {


	@Bean
	public ViewResolver getViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/pages/");
		resolver.setSuffix(".jsp");
		//resolver.setViewClass(JstlView.class);
		return resolver;
	}

	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setMaxUploadSize(10485760);
		return resolver;
	}

	@Bean
	public Person user() {
		return new Person();
	}

	@Bean
	@Scope("singleton")
	public IPersonService loginService() {
		return new PersonService();
	}

	@Bean
	@Scope("singleton")
	public ICartItemService cartItemService() {
		return new CartItemService();
	}


	@Bean
	@Scope("singleton")
	public IProductService productService() {
		return new ProductService();
	}

	@Bean
	@Scope("singleton")
	public IOrderService orderService() {
		return new OrderService();
	}

	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/pages/css/**").addResourceLocations("/pages/css/");
		registry.addResourceHandler("/pages/Scripts/**").addResourceLocations("/pages/Scripts/");
		registry.addResourceHandler("/icons/**").addResourceLocations("/icons/");
		registry.addResourceHandler("/uploads/**").addResourceLocations("file:/" + getUploads());

	}

	public static String getUploads() {
		return "c:/data/uploads/";
	}


}
