package com.example.bankingapplication.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class UserSecurity {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> {
//                    authorize.requestMatchers(HttpMethod.POST,"/user/**").hasRole("ADMIN");
//                    authorize.requestMatchers(HttpMethod.POST,"/address/**").hasRole("ADMIN");
//                    authorize.requestMatchers(HttpMethod.DELETE,"/address/**").hasRole("ADMIN");
//                    authorize.requestMatchers(HttpMethod.GET,"/address/**").hasAnyRole("ADMIN","USER");
//                    authorize.requestMatchers(HttpMethod.PATCH,"/account/**").hasAnyRole("ADMIN","USER");
                    authorize.requestMatchers(HttpMethod.GET,"/user/**").permitAll(); // this means permit Get Publicly
                    authorize.requestMatchers(HttpMethod.GET,"/address/**").permitAll(); // this means permit Get Publicly
                    authorize.requestMatchers(HttpMethod.GET,"/account/**").permitAll(); // this means permit Get Publicly
                    authorize.anyRequest().authenticated();
                }).httpBasic(Customizer.withDefaults())
                .csrf((csrf) -> csrf.disable());
        return http.build();
    }

    @Bean
    public static PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user = User.builder()
                .username("anika")
                .password(encoder().encode("anika"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(encoder().encode("admin"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user,admin);
    }
}
