package br.com.cursoudemy.productapi.modules.jwt.service;

import br.com.cursoudemy.productapi.config.exception.AuthenticationException;
import br.com.cursoudemy.productapi.modules.jwt.dto.JwtResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class JwtService {

    private static final String BEARER = "bearer ";

    @Value("${app-config.api-secret}")
    private String apiSecret;

    public void validateAuthorization(String token) {
        var accessToken = extractToken(token);
        Claims claims = null;
        try {
            claims = Jwts
                    .parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(apiSecret.getBytes()))
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new AuthenticationException("Error processing access token");
        }
        var user = JwtResponse.getUser(claims);
        if(isEmpty(user) || isEmpty(user.getId())) {
            throw new AuthenticationException("Invalid user.");
        }
    }

    private String extractToken(String token) {
        if(token == null || token.isEmpty()) {
            throw new AuthenticationException("The access token was not informed.");
        }
        if(token.toLowerCase().contains(BEARER)) {
            token = token.split(" ")[1];
        }
        return token;
    }

}
