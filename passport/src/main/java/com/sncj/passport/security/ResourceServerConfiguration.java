package com.sncj.passport.security;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private Logger logger = LoggerFactory.getLogger(ResourceServerConfiguration.class);
    private static final String DEMO_RESOURCE_ID = "SNCJ";

    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint ;

    @Bean
    public CustomLogoutSuccessHandler customLogoutSuccessHandler(){
        return new CustomLogoutSuccessHandler();
    } ;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(DEMO_RESOURCE_ID).stateless(true);
    }
    @Override
    public void configure(HttpSecurity http) throws Exception {
       logger.info("=========================111111111=========");
       http.exceptionHandling()
               .authenticationEntryPoint(customAuthenticationEntryPoint)
               .and()
               .logout()
               .logoutUrl("/oauth/logout")
               .logoutSuccessHandler(customLogoutSuccessHandler())
               .and()
               .authorizeRequests()
               .antMatchers("/hello/").permitAll()
               .antMatchers("/secure/**").authenticated();
    }

}
