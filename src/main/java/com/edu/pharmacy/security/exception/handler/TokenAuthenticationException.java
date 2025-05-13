package com.edu.pharmacy.security.exception.handler;
import org.springframework.security.core.AuthenticationException;
public class TokenAuthenticationException extends AuthenticationException {
    public TokenAuthenticationException(String message) {
        super(message);
    }
}