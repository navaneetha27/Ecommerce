package com.projects.ecommerce.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
    static SecretKey secretStringKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    // Encode the key as a Base64 string to store it
    private  static  final String secretKey = Base64.getEncoder().encodeToString(secretStringKey.getEncoded());
//    private  static  final String secretKey = "WW91cnN0cmluZwOxcncn89cccjedKOklie67wwreeposndhwxlk==";

    private static  final  Integer TOKEN_VALIDITY = 3600 * 5;
    public  String getUserNameFromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }

    private <T> T  getClaimFromToken(String token, Function<Claims,T> claimResolver){
        final Claims claims = getAllClaimsFromToken(token);
        return claimResolver.apply(claims);
    }

    private  Claims getAllClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    public boolean validateToken(String jwtToken, UserDetails userDetails) {
        String userName = getUserNameFromToken(jwtToken);
        return (userDetails.getUsername().equals(userName)  && !isTokenExpired(jwtToken));
    }

    private  boolean isTokenExpired(String token){
        final Date expiration = getExpirationDateFromToken(token);
        return  expiration.before(new Date());
    }

    private Date getExpirationDateFromToken(String token){
        return  getClaimFromToken(token, Claims::getExpiration);
    }
    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis()+ TOKEN_VALIDITY*1000))
                .signWith(SignatureAlgorithm.HS512, secretKey).compact();
    }
}
