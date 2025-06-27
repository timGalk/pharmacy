package com.edu.pharmacy.config;

import com.edu.pharmacy.security.filter.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
public class SecurityConfig {
    private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/medicines").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/users").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/medicines/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/login", "/api/login/").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/me").authenticated()
                        
                        // Medicine management - Admin and Pharmacist only
                        .requestMatchers("/api/medicines/**").hasAnyAuthority("ADMIN", "PHARMACIST")
                        
                        // Cart operations - Only USER role can perform cart operations
                        .requestMatchers(HttpMethod.GET, "/api/carts/**").hasAuthority("USER")
                        .requestMatchers(HttpMethod.POST, "/api/carts/**").hasAuthority("USER")
                        .requestMatchers(HttpMethod.PATCH, "/api/carts/**").hasAuthority("USER")
                        .requestMatchers(HttpMethod.DELETE, "/api/carts/**").hasAuthority("USER")
                        
                        // Order operations - Only USER role can create orders
                        .requestMatchers(HttpMethod.POST, "/api/orders").hasAuthority("USER")
                        .requestMatchers(HttpMethod.PATCH, "/api/orders/*/status").hasAnyAuthority("ADMIN", "PHARMACIST")
                        .requestMatchers(HttpMethod.POST, "/api/orders/*/cancel").hasAnyAuthority("ADMIN", "PHARMACIST")
                        
                        // Order viewing - Admin can see all orders, Pharmacist can see orders, User can see their own
                        .requestMatchers(HttpMethod.GET, "/api/orders").hasAnyAuthority("ADMIN", "PHARMACIST")
                        .requestMatchers(HttpMethod.GET, "/api/orders/status/*").hasAnyAuthority("ADMIN", "PHARMACIST")
                        .requestMatchers(HttpMethod.GET, "/api/orders/user").hasAuthority("USER")
                        .requestMatchers(HttpMethod.GET, "/api/orders/user/*").hasAuthority("USER")
                        .requestMatchers(HttpMethod.GET, "/api/orders/*").hasAnyAuthority("ADMIN", "PHARMACIST", "USER")
                        
                        .requestMatchers("/test/public").permitAll()
                        .anyRequest().authenticated()
                )

                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


}

