package com.nashtech.ecommerce.security.jwt;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.nashtech.ecommerce.security.UserDetailsImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtUtils {

	@Value("${jwt.secret-key}")
	private String jwtSecret;

	@Value("${jwt.expirationMs}")
	private int jwtExpirationMs;

	public String generateJwtToken(Authentication authentication) {
		UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
		return Jwts.builder().setSubject((user.getUsername())).setIssuedAt(new Date())
				.setExpiration(generateExpirationDate())
				.signWith(SignatureAlgorithm.HS256, jwtSecret).compact();
	}

	public boolean validateJwtToken(String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		} catch (SignatureException e) {
			log.error("Invalid JWT signature: {}", e.getMessage());
			throw new SignatureException(e.getMessage());
		} catch (MalformedJwtException e) {
			log.error("Invalid JWT token: {}", e.getMessage());
			throw new MalformedJwtException(e.getMessage());
		} catch (ExpiredJwtException e) {
			log.error("JWT token is expired: {}", e.getMessage());
			throw new ExpiredJwtException(null, null, e.getMessage());
		} catch (UnsupportedJwtException e) {
			log.error("JWT token is unsupported: {}", e.getMessage());
			throw new UnsupportedJwtException(e.getMessage());
		} catch (IllegalArgumentException e) {
			log.error("JWT claims string is empty: {}", e.getMessage());
			throw new IllegalArgumentException(e.getMessage());
		} 
	}

	public String getEmailFromJwtToken(String token) {
		Claims claims = getClaimsFromJwtToken(token);
		if (claims != null && isTokenExpired(claims)) {
			return claims.getSubject();
		}
		return null;
	}

	public Claims getClaimsFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
	}

	private boolean isTokenExpired(Claims claims) {
		return claims.getExpiration().after(new Date());
	}

	public Date generateExpirationDate() {
		return new Date(System.currentTimeMillis() + jwtExpirationMs);
	}

}
