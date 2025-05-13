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

@Service
public class JwtService {
    private static final String ROLES_CLAIM = "roles";
    private final Algorithm signingAlgorithm;


    public JwtService(@Value("${jwt.signing-secret}") String signingSecret) {
        this.signingAlgorithm = Algorithm.HMAC256(signingSecret);
    }


    public AuthUser resolveJwtToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(signingAlgorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);

            String userId = decodedJWT.getSubject();
            List<Role> roles = decodedJWT.getClaim(ROLES_CLAIM).asList(Role.class);

            return new AuthUser(userId, roles);
        } catch (JWTVerificationException exception) {
            throw new TokenAuthenticationException("JWT is not valid");
        }
    }

    public String createJwtToken(AuthUser authUser) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        long expMillis = nowMillis + 3600000; // 1 hour validity
        Date exp = new Date(expMillis);

        List<String> roles = authUser.roles().stream().map(Role::name).toList();

        return JWT.create()
                .withSubject(authUser.userId())
                .withClaim(ROLES_CLAIM, roles)
                .withIssuedAt(now)
                .withExpiresAt(exp)
                .sign(signingAlgorithm);
    }

}
