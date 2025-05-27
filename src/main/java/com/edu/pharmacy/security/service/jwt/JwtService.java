package com.edu.pharmacy.security.service.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.edu.pharmacy.common.Role;
import com.edu.pharmacy.security.user.AuthUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//@Service
//@Slf4j
//public class JwtService {
//    private static final String ROLES_CLAIM = "roles";
//    private static final long EXPIRATION_MS = 3600000; // 1 hour
//    private final Algorithm algorithm;
//    private final JWTVerifier jwtVerifier;
//
//    public JwtService(@Value("${jwt.signing-secret}") String secret) {
//        if (secret == null || secret.trim().isEmpty()) {
//            throw new IllegalArgumentException("JWT signing secret cannot be null or empty");
//        }
//        this.algorithm = Algorithm.HMAC256(secret);
//        this.jwtVerifier = JWT.require(algorithm).build();
//    }
//
//    public String generateToken(AuthUser authUser) {
//        Date now = new Date();
//        Date expiry = new Date(now.getTime() + EXPIRATION_MS);
//
//        List<String> roleNames = authUser.roles().stream()
//                .map(Enum::name)
//                .toList();
//
//        log.debug("Generating token for user: {}, roles: {}", authUser.userId(), roleNames);
//
//        return JWT.create()
//                .withSubject(String.valueOf(authUser.userId()))
//                .withClaim(ROLES_CLAIM, roleNames)
//                .withIssuedAt(now)
//                .withExpiresAt(expiry)
//                .sign(algorithm);
//    }
//
//    public AuthUser parseToken(String token) {
//        try {
//            DecodedJWT jwt = jwtVerifier.verify(token);
//            Long userId = Long.parseLong(jwt.getSubject());
//
//            List<String> roleStrings = jwt.getClaim(ROLES_CLAIM).asList(String.class);
//            if (roleStrings == null) {
//                throw new RuntimeException("No roles found in token");
//            }
//
//            Set<Role> roles = roleStrings.stream()
//                    .map(Role::valueOf)
//                    .collect(Collectors.toSet());
//
//            log.debug("Parsed token for user: {}, roles: {}", userId, roles);
//            return new AuthUser(userId, roles);
//
//        } catch (JWTVerificationException ex) {
//            log.error("JWT verification failed: {}", ex.getMessage());
//            throw new RuntimeException("Invalid or expired JWT token", ex);
//        } catch (NumberFormatException ex) {
//            log.error("Invalid user ID in token: {}", ex.getMessage());
//            throw new RuntimeException("Invalid user ID in token", ex);
//        } catch (IllegalArgumentException ex) {
//            log.error("Invalid role in token: {}", ex.getMessage());
//            throw new RuntimeException("Invalid role in token", ex);
//        }
//    }
//}