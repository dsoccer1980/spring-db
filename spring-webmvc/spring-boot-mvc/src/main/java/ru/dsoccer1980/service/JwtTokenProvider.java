package ru.dsoccer1980.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class JwtTokenProvider {


  @Value("${jwt.token.secret}")
  private String secret;

  @Value("${jwt.token.expired}")
  private long validityInMilliseconds;

  public String createJwt() {
    Claims claims = Jwts.claims()
        .setSubject("denis");
   claims.put("userId", 15);
   claims.put("userName", "Denis");

    Date now = new Date();
    Date validity = new Date(now.getTime() + validityInMilliseconds);

    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(validity)
        .signWith(SignatureAlgorithm.HS256, secret)
        .compact();
  }

  public boolean validateToken(String token) throws Exception {
    try {
      Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);

      if (claims.getBody().getExpiration().before(new Date())) {
        return false;
      }

      return true;
    } catch (JwtException | IllegalArgumentException e) {
      return false;
//      throw new Exception("JWT token is expired or invalid" + e);
    }
  }

}
