package com.sncj.passport.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;

/**
 * Created by danny on 2017/10/17.
 */
@Configuration
@Order(1)
public class WebSecurityConfiguration {
    @Configuration
    @EnableWebSecurity
    protected static class UserSecurityConfiguration extends WebSecurityConfigurerAdapter {

        @Autowired
        private UserDetailsService userService;

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {

            auth
                    .userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
        }

        @Bean
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

        @Bean
        public static NoOpPasswordEncoder passwordEncoder() {
            return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            // 字符编码过滤器必须在SecurityFilter之前运行
//            CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
//            characterEncodingFilter.setEncoding(Utils.CHARSET_UTF8);
//            characterEncodingFilter.setForceEncoding(true);
//            http.addFilterBefore(characterEncodingFilter, CsrfFilter.class);
//
//            http
//                    .authorizeRequests()
//                    .antMatchers("/login", "/register", "/forget", "/test2").permitAll()
//                    .anyRequest().authenticated()
//                    .and()
//                    .exceptionHandling()
//                    .accessDeniedPage("/login?error=true")
//                    .and()
//                    .logout().logoutUrl("/logout").logoutSuccessUrl("/login")
//                    .and()
//                    .formLogin().loginPage("/login").loginProcessingUrl("/login").failureUrl("/login?error=true");
////                    .and()
////                    .requiresChannel().anyRequest().requiresSecure();
            http.requestMatchers()
                    .antMatchers("/login", "/oauth/authorize")
                    .and()
                    .authorizeRequests()
                    .anyRequest()
                    .authenticated()
                    .and()
                    .formLogin()
                    .permitAll();
        }

        //开启全局方法拦截
        @EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
        public static class GlobalSecurityConfiguration extends GlobalMethodSecurityConfiguration {
            @Override
            protected MethodSecurityExpressionHandler createExpressionHandler() {
                return new OAuth2MethodSecurityExpressionHandler();
            }

        }
    }
}
