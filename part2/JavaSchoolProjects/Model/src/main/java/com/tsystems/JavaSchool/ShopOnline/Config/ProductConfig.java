package com.tsystems.JavaSchool.ShopOnline.Config;

import com.google.common.base.Preconditions;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Dao.IProductDAO;
import com.tsystems.JavaSchool.ShopOnline.Persistance.Dao.ProductDAO;
import com.tsystems.JavaSchool.ShopOnline.Services.GetCatalogService;
import com.tsystems.JavaSchool.ShopOnline.Services.IGetCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

/**
 * Created by asus on 13.03.2016.
 */
@Configuration
@PropertySource({ "classpath:database.properties" })
@ComponentScan(basePackages="com.tsystems.JavaSchool.ShopOnline")
@EnableJpaRepositories(basePackages = "com.tsystems.JavaSchool.ShopOnline.Persistance.Repository", entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "productTransactionManager")
public class ProductConfig {

    @Autowired
    Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setPackagesToScan(new String[] { "com.tsystems.JavaSchool.ShopOnline.Persistance.Entity" });
        em.setPersistenceXmlLocation("classpath:META-INF/persistence.xml");
        em.setDataSource(productDataSource());

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        final HashMap<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean
    public DataSource productDataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Preconditions.checkNotNull(env.getProperty("jdbc.driverClassName")));
        dataSource.setUrl(Preconditions.checkNotNull(env.getProperty("jdbc.url")));
        dataSource.setUsername(Preconditions.checkNotNull(env.getProperty("jdbc.username")));
        dataSource.setPassword(Preconditions.checkNotNull(env.getProperty("jdbc.password")));

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager productTransactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }


    @Bean
    @Scope("prototype")
    public IProductDAO productDAO() {
        return new ProductDAO();
    }

    @Bean
    @Scope("prototype")
    public IGetCatalogService getCatalogService() {
        return new GetCatalogService();
    }

}
