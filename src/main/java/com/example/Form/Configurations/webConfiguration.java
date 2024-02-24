package com.example.Form.Configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class webConfiguration {
   @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
       http.authorizeHttpRequests((authz) ->authz.anyRequest().permitAll())
               .httpBasic(Customizer.withDefaults());
       return http.build();
   }
}

