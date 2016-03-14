package com.tsystems.JavaSchool.ShopOnline.config;


import com.tsystems.JavaSchool.ShopOnline.Services.GetCatalogService;
import com.tsystems.JavaSchool.ShopOnline.Services.IGetCatalogService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages="com.tsystems.JavaSchool.ShopOnline")
@EnableWebMvc
//@Import(ProductConfig1.class)
public class MvcConfiguration extends WebMvcConfigurerAdapter{


	@Bean
	public ViewResolver getViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/pages/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

    @Bean
    @Scope("prototype")
    public IGetCatalogService getCatalogService() {
        return new GetCatalogService();
    }

	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/pages/css/**").addResourceLocations("/pages/css/");
		registry.addResourceHandler("/pages/Scripts/**").addResourceLocations("/pages/Scripts/");
		registry.addResourceHandler("/icons/**").addResourceLocations("/icons/");
		registry.addResourceHandler("/uploads/**").addResourceLocations("file:/C:/data/uploads/");
	}

	
}
