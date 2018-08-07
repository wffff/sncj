package com.sncj.passport.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

//    private static final String RESOURCE_ID = "SNCJ";

    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) {
//        resources.resourceId(RESOURCE_ID).stateless(true);
//    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling()
                .authenticationEntryPoint(customAuthenticationEntryPoint)
                .and()
                .requestMatchers()
                .antMatchers("/**")
                .and()
                .authorizeRequests()
                .antMatchers("/api/**")
                .authenticated();
//               .logout()
//               .logoutUrl("/oauth/logout")
//               .logoutSuccessHandler(customLogoutSuccessHandler())
//               .and()
//               .authorizeRequests()
//               .antMatchers("/hello/").permitAll()
//               .antMatchers("/secure/**").authenticated();
    }

}
