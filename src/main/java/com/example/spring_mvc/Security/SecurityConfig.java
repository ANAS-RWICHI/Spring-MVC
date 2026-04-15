package com.example.spring_mvc.Security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return new InMemoryUserDetailsManager(
                org.springframework.security.core.userdetails.User
                        .withUsername("user")
                        .password(passwordEncoder().encode("1234"))
                        .roles("USER")
                        .build(),
                org.springframework.security.core.userdetails.User
                        .withUsername("admin")
                        .password(passwordEncoder().encode("1234"))
                        .roles("ADMIN","USER")
                        .build()
        );
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .formLogin(Customizer.withDefaults())
                .authorizeHttpRequests(ar->ar.requestMatchers("/index/**").hasRole("USER"))
                .authorizeHttpRequests(ar->ar.requestMatchers("/delete").hasRole("ADMIN"))
                .authorizeHttpRequests(ar->ar.requestMatchers("/addProduct").hasRole("ADMIN"))
                .authorizeHttpRequests(ar->ar.requestMatchers("/saveProduct").hasRole("ADMIN"))
                .authorizeHttpRequests(ar->ar.anyRequest().authenticated())
                .exceptionHandling(e->e.accessDeniedPage("/notAuthorized"))
                .build();
    }
}
