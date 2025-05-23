package com.edu.pharmacy.security.service.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.edu.pharmacy.common.Role;
import com.edu.pharmacy.security.exception.handler.TokenAuthenticationException;
import com.edu.pharmacy.security.user.AuthUser;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class JwtService {
    private static final String ROLES_CLAIM = "roles";
    private final Algorithm algorithm;

    public JwtService(@Value("${jwt.signing-secret}") String secret) {
        this.algorithm = Algorithm.HMAC256(secret);
    }

    public String generateToken(AuthUser authUser) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + 3600000); // 1 hour

        List<String> roleNames = authUser.roles().stream().map(Enum::name).toList();

        return JWT.create()
                .withSubject(String.valueOf(authUser.userId()))
                .withClaim(ROLES_CLAIM, roleNames)
                .withIssuedAt(now)
                .withExpiresAt(expiry)
                .sign(algorithm);
    }

    public AuthUser parseToken(String token) {
        DecodedJWT jwt = JWT.require(algorithm).build().verify(token);
        Long userId = Long.valueOf(jwt.getSubject());
        List<Role> roles = jwt.getClaim(ROLES_CLAIM).asList(String.class).stream()
                .map(Role::valueOf)
                .toList();

        return new AuthUser(userId, roles);
    }
}
