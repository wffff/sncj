//package com.sncj.web.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.oauth2.client.OAuth2ClientContext;
//import org.springframework.security.oauth2.client.OAuth2RestTemplate;
//import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
//import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
//
//import java.util.Arrays;
//
///**
// * Created by Danny on 2018/7/11.
// */
//@Configuration
//@EnableOAuth2Client
//public class ResourceConfiguration {
//
//    @Bean
//    public OAuth2ProtectedResourceDetails hello() {
//        AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
//        details.setId("hello");
//        details.setClientId("tonr");
//        details.setClientSecret("secret");
//        details.setAccessTokenUri("http://localhost:8083/auth/oauth/token");//认证服务器地址+/oauth/token
//        details.setUserAuthorizationUri("http://localhost:8083/auth/oauth/authorize");//认证服务器地址+/oauth/authorize
//        details.setScope(Arrays.asList("read", "write"));
//        return details;
//    }
//
//    @Bean
//    public OAuth2RestTemplate helloRestTemplate(OAuth2ClientContext oauth2Context) {//客户端的信息被封装到OAuth2RestTemplate用于请求资源
//        return new OAuth2RestTemplate(hello(), oauth2Context);
//    }
//}