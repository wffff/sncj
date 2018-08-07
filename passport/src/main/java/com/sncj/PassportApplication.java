package com.sncj;

import com.sncj.core.baseconfig.BaseRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;


@SpringBootApplication
@ComponentScan(basePackages = {"com.sncj.passport.*","com.sncj.core.*"})
@EnableJpaRepositories(basePackages = {"com.sncj.core"},
        repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class//指定自己的工厂类
)
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class PassportApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(PassportApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(PassportApplication.class);
    }
//    @Bean
//    public RequestContextListener requestContextListener() {
//        return new RequestContextListener();
//    }
}
