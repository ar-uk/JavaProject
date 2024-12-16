package com.adil.unisiteproject.javaproject.Configs;

import com.adil.unisiteproject.javaproject.services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    // Constructor injection for CustomUserDetailsService
    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    /**
     * PasswordEncoder bean for password hashing (BCrypt is recommended)
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configure AuthenticationManager with CustomUserDetailsService and PasswordEncoder
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder)
            throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }

    /**
     * Define SecurityFilterChain for detailed security configuration
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/error").permitAll() // Allow everyone to access login and error pages
                        .requestMatchers("/student/**").hasRole("STUDENT") // Restrict /student/** to ROLE_STUDENT
                        .requestMatchers("/teacher/**").hasRole("TEACHER") // Restrict /teacher/** to ROLE_TEACHER
                        .anyRequest().authenticated() // All other requests require authentication
                )
                .formLogin(form -> form
                        .loginPage("/login") // Custom login page
                        .defaultSuccessUrl("/dashboard", true) // Default fallback if no match
                        .successHandler((request, response, authentication) -> {
                            var authorities = authentication.getAuthorities();

                            // Redirect based on role
                            if (authorities.stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_TEACHER"))) {
                                response.sendRedirect("/teacher/dashboard");
                            } else if (authorities.stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_STUDENT"))) {
                                response.sendRedirect("/student/dashboard");
                            } else {
                                response.sendRedirect("/login?error=true");
                            }
                        })
                        .failureUrl("/login?error=true") // Redirect back to login with error
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // Custom logout URL
                        .logoutSuccessUrl("/login?logout=true") // Redirect to login page on successful logout
                        .invalidateHttpSession(true) // Invalidate session
                        .deleteCookies("JSESSIONID") // Remove session cookie
                );

        return http.build();
    }
}