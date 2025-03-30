package de.dlsa.api.services;

import de.dlsa.api.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * Verwaltung von JWT
 *
 * @author Benito Ernst
 * @version  01/2024
 */
@Service
public class JwtService {
    @Value("${security.jwt.secret-access}")
    private String secretAccess;

    @Value("${security.jwt.secret-access.expiration-time}")
    private long accessTokenExp;

    /**
     * Extrahiert den Username aus dem JWT
     *
     * @param token gültiger JWT
     * @return Rückgabe des Usernames als String
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String extractRoles(String token) {

        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        // Extrahiert die "roles" aus den Claims, angenommen als Liste von Strings
        var test = claims.get("role", String.class);
        return test;
    }

    /**
     * Extrahiert alle Claims aus dem JWT
     *
     * @param token gültiger JWT
     * @param claimsResolver Funktionsinterfaces Claims
     * @return Rückgabe eines Funktionsinterfaces zur Weiterverarbeitung der Claims
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }

    /**
     * Generiert einen gültigen JWT
     *
     * @param user aktueller User
     * @return Rückgabe eines gültigen JWTs
     */
    public String generateToken(User user) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole().getRolename());

        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenExp))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Prüft ob ein JWT gültig ist
     *
     * @param token aktueller JWT
     * @param userDetails Usercontext
     * @return Wenn der JWT gültig ist, wird true zurückgeliefert. andernfalls false
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    /**
     * Prüft ob ein Token abgelaufen ist
     *
     * @param token aktueller JWT
     * @return Wenn der Token abgelaufen ist, dann wird true zurückgeliefert. andernfalls false
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extrahiert das Ablaufdatum aus einem JWT
     *
     * @param token aktueller JWT
     * @return Wenn der Token abgelaufen ist, dann wird true zurückgeliefert. andernfalls false
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Generieriung einer Signatur für einen JWT
     *
     * @return Gibt eine Signatur für einen JWT zurück
     */
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretAccess);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
