package com.org.JobPortal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

 /*
   >- In Spring 6 certain things changed in security
   >- when we want to change configuration we should return object of security
   >- By Default SecurityFilterChain is created by Spring Security , but to our own configuration we need override it as below
   >- We can override it by accessing HttpSecurity object
   >- By Default spring will have authentication provider , but we want to customize it
   >- We want to our AuthenticationProvider which should access DAO(DATA ACCESS Object)
   >- We need to provide userdetails service to our DAO auth provider

   ***AWT TOKEN AUTHENTICATION***
   >- Once User request is valid , to make server will generate AWT token
   >- To validate that token we need to add custom filter in the existing filter chain
   >- refer @filter-chain comment below
   >- By default spring wont have any custom configuration for us , but it provide classes and interfaces
   >- spring will refer  JWTFilterConfig for custom configuration
   >- Who will use the below beans - spring container will use these below bean to override with existing ones

  */

    //UserDetail Service is interface when you create
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtFilterConfig jwtFilterConfig;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        //>- disabling csrf token
        http.csrf(customizer -> customizer.disable());
        //>- to Enable username & password
        http.authorizeHttpRequests(req -> req
                .requestMatchers("register" ,"login")
                .permitAll()
                .anyRequest().authenticated());
        //>- To work in browser below statement will create form - we dont need form login when we are stateless
        //http.formLogin(Customizer.withDefaults());
        //>- to work basic auth in post man
        http.httpBasic(Customizer.withDefaults());
        //>- when we enable stateless mode - every time new session is generated
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        //@filter-chain
        http.addFilterBefore(jwtFilterConfig, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager (AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(12);
    }




    @Bean
    public AuthenticationProvider authProvider(){
        DaoAuthenticationProvider  provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        return provider;

    }


    /*
      >- By default Spring will use UserDetails class to scan application properties
      >- This class return UserDetails Object
      >- By Default Spring provide stateful session
      >- When we want to have Stateless  and multiple user login we need to override this class
      >- We have created Bean , but we need to configure it multiple users with thier details like username , password , roles ..etc
      >- Below method is not accessing Databases , we are just managing it locally so we used InMemoryUserDetailManager

     */
//    @Bean
//    public UserDetailsService userDetailsService(){
//
//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("pavan")
//                .password("p@123")
//                .roles("USER")
//                .build();
//
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password("a@123")
//                .roles("ADMIN")
//                .build();
//
//
//        return new InMemoryUserDetailsManager(user,admin);
//    }
}
