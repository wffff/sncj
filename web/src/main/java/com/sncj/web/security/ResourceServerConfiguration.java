//package com.sncj.web.security;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//
///**
// * 资源服务器
// */
//@Configuration
//@EnableResourceServer
//public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
//
//    private static final String RESOURCE_ID = "SNCJ";
//
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) {
//        resources.resourceId(RESOURCE_ID).stateless(false);
//    }
//    //
////    @Override
////    public void configure(HttpSecurity http) throws Exception {
////        http.
////                anonymous().disable()
////                .requestMatchers().antMatchers("/user*/**")
////                .and().authorizeRequests()
////                .antMatchers("/user*/**").permitAll()
////                .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
////    }
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.requestMatchers().antMatchers("/*")
//                .and()
//                .authorizeRequests()
//                .antMatchers("/*").authenticated();
//    }
//}