//package com.sncj.passport.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//
//
//@Configuration
//@EnableAuthorizationServer
//public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {
//
//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;
//
//    @Override
//    public void configure(final AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
//        oauthServer.tokenKeyAccess("permitAll()")
//            .checkTokenAccess("isAuthenticated()");
//    }
//
//    @Override
//    public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory()
//            .withClient("de9af4d7233e7fe43803c915bda11ab190062a82")
//            .secret(passwordEncoder.encode("233b0a865b1dbb4de3e510fdbdb47e65b68fc2a7"))
//            .authorizedGrantTypes("authorization_code")
//            .scopes("user_info")
//            .autoApprove(true)
//            .redirectUris("http://localhost:8000/ui/login","http://localhost:8000/ui/login")
//        // .accessTokenValiditySeconds(3600)
//        ; // 1 hour
//    }
//
//
//}
