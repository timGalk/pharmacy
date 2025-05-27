package com.edu.pharmacy.security.filter;

//import com.edu.pharmacy.security.service.jwt.JwtService;
import com.edu.pharmacy.security.user.AuthUser;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


//@Component
//@Slf4j // Add Lombok for logging
//public class JwtAuthFilter extends OncePerRequestFilter {
//    private final JwtService jwtService;
//
//    public JwtAuthFilter(JwtService jwtService) {
//        this.jwtService = jwtService;
//    }
//
//    @Override
//    protected void doFilterInternal(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            FilterChain filterChain
//    ) throws ServletException, IOException {
//
//        String requestURI = request.getRequestURI();
//        String method = request.getMethod();
//        log.debug("Processing request: {} {}", method, requestURI);
//
//        final String authHeader = request.getHeader("Authorization");
//
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            log.debug("No Bearer token found for request: {} {}", method, requestURI);
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        String jwt = authHeader.substring(7);
//        log.debug("Found JWT token, attempting to parse...");
//
//        try {
//            AuthUser authUser = jwtService.parseToken(jwt);
//            Long userId = authUser.userId();
//
//            log.debug("Successfully parsed token for user: {}, roles: {}", userId, authUser.roles());
//
//            if (SecurityContextHolder.getContext().getAuthentication() == null) {
//                List<SimpleGrantedAuthority> authorities = authUser.roles().stream()
//                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
//                        .collect(Collectors.toList());
//
//                log.debug("Setting authorities: {}", authorities);
//
//                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//                        userId,
//                        null,
//                        authorities
//                );
//                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//
//                log.debug("Authentication set successfully for user: {}", userId);
//            }
//        } catch (Exception e) {
//            log.error("JWT token parsing failed: {}", e.getMessage());
//            SecurityContextHolder.clearContext();
//        }
//
//        filterChain.doFilter(request, response);
//    }
//}