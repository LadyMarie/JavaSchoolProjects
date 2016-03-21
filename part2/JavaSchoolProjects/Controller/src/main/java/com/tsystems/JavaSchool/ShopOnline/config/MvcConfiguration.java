package com.tsystems.JavaSchool.ShopOnline.config;


import com.tsystems.JavaSchool.ShopOnline.Persistance.Entity.Person;
import com.tsystems.JavaSchool.ShopOnline.Services.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
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
public class MvcConfiguration extends WebMvcConfigurerAdapter{


	@Bean
	public ViewResolver getViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/pages/");
		resolver.setSuffix(".jsp");
		//resolver.setViewClass(JstlView.class);
		return resolver;
	}

    @Bean
    @Scope("singleton")
    public IGetCatalogService getCatalogService() {
        return new GetCatalogService();
    }

	@Bean
	@Scope("singleton")
	public ISignupService signupService() {
		return new SignupService();
	}

	@Bean
	@Scope("singleton")
	public ILoginService loginService() {
		return new LoginService();
	}

	@Bean
	@Scope("singleton")
	public ICartItemService cartItemService() {
		return new CartItemService();
	}

	@Bean
	public Person user() {
		return new Person();
	}

	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/pages/css/**").addResourceLocations("/pages/css/");
		registry.addResourceHandler("/pages/Scripts/**").addResourceLocations("/pages/Scripts/");
		registry.addResourceHandler("/icons/**").addResourceLocations("/icons/");
		registry.addResourceHandler("/uploads/**").addResourceLocations("file:/C:/data/uploads/");
	}

	
}
