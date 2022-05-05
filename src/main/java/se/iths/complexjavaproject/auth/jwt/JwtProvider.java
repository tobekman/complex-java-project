package se.iths.complexjavaproject.auth.jwt;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import se.iths.complexjavaproject.auth.UserPrincipal;

import java.util.Date;

@Component
public class JwtProvider {
    @Value("${security.jwt.expiration:#{3600000}}")
    private int expiration;
    @Value("${security.jwt.secret:secret}")
    private String secret;

    public String generateToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expiration);

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String getUsernameFromJWT (String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(authToken);
            return true;
        } catch (SignatureException | UnsupportedJwtException | MalformedJwtException | ExpiredJwtException |
                 IllegalArgumentException e) {
            e.printStackTrace();
        }
        return false;
    }


    public int getExpiration() {
        return expiration;
    }

    public String getSecret() {
        return secret;
    }
}
