//package com.example.demo.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests(authorizeRequests ->
//                        authorizeRequests
//                                .requestMatchers("/public/**").permitAll()  // 公开访问的路径
//                                .anyRequest().authenticated()  // 其他所有路径都需要认证
//                )
//                .oauth2Login(oauth2Login ->
//                        oauth2Login.loginPage("/login")  // 自定义登录页面
//                );
//        return http.build();
//    }
//}

