package pl.michal.clinical_nutrition.admin.config;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;

import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import pl.michal.clinical_nutrition.admin.entity.Premissions;

@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -2550185165626007488L;

    //60 sekund * 60 minut * 1 godzina 30 * 60;
    public static final long JWT_TOKEN_VALIDITY = 30 * 60;

    private static Map<String,Date> blackList = new HashMap<>();

    @Value("${jwt.secret}")
    private String secret;

    //retrieve username from jwt token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public Set<SimpleGrantedAuthority> getAutoritiesFromToken(String token){
        Claims claims=getAllClaimsFromToken(token);
        Set<SimpleGrantedAuthority> authorities =  new HashSet<>();
        for (Object auth : claims.get("authority",ArrayList.class)) {
            authorities.add(new SimpleGrantedAuthority(auth.toString()));
        }
        return authorities;
    }

    public long getIdCurrentJos(String token){
        Claims claims=getAllClaimsFromToken(token);
        if(!(claims.get("currentJos")==null))
            return claims.get("currentJos",Long.class);
        return -1;
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    //for retrieveing any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        ArrayList<String> authsList = new ArrayList<>(userDetails.getAuthorities().size());

        for (GrantedAuthority authority : userDetails.getAuthorities()) {
            authsList.add(authority.getAuthority());
        }
        claims.put("authority", authsList);
        return doGenerateToken(claims, userDetails.getUsername());
    }

    public String generateToken(UserDetails userDetails, long josId, String oldToken) {
        addTokenToBlackList(oldToken);
        Map<String, Object> claims = new HashMap<>();
        claims.put("currentJos", josId);
        ArrayList<String> authsList = new ArrayList<>(userDetails.getAuthorities().size());

        for (GrantedAuthority authority : userDetails.getAuthorities()) {
            authsList.add(authority.getAuthority());
        }
        claims.put("authority", authsList);
        return doGenerateToken(claims, userDetails.getUsername());
    }

    public void addTokenToBlackList(String token){
        blackList.put(token,getExpirationDateFromToken(token));
    }

    //while creating the token -
    //1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
    //2. Sign the JWT using the HS512 algorithm and secret key.
    //3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
    //   compaction of the JWT to a URL-safe string
    private String doGenerateToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    private Boolean controlBlacklist(String token){
        blackList.entrySet().removeIf(tok -> tok.getValue().before(new Date()));

        if(blackList.get(token)!=null)
             return false;
         return true;
    }

    //validate token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token) && controlBlacklist(token));
    }
}
