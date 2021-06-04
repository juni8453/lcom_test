package com.lcom_test.example.config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import com.lcom_test.example.domain.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class JwtUtils {

	private static final String jwtSecret = "lcomputerstudyexample"; //원하는 시크릿키로 수정
	
	private static final int jwtExpirationMs = 864000; // 토큰 유효시간 설정
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//jwt 생성 메소드
		public String generateJwtToken(Authentication authentication) {
			//User의 정보가 담겨있는 authentication 객체를 받아온다

			User user = (User) authentication.getPrincipal(); 
			//authentication에 User 정보가 있으므로 getPrincipal()로 정보를 뽑아온다.
			
			return Jwts.builder() //builder 패턴을 이용하여 jwt생성
					.setSubject((user.getUsername())) 
					// 토큰의 Payload 부분
					
					.setIssuedAt(new Date())
					.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
					// 토큰 발급과 만료 시간 설정
					
					.signWith(SignatureAlgorithm.HS512, jwtSecret)
					// 복호화 시 사용되는 Signature 설정 (홈페이지 확인)
					// 따라서 jwtSecret 키가 들어가줘야함
					
					.compact();
					// 토큰에 담긴 정보를 직렬화
		}
		
		//토큰을 이용하여 유저 아이디 불러오는 메소드
		public String getUserNameFromJwtToken(String token) {
			return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
		}
		
		 private static Claims getClaimsFormToken(String token) {
	        return (Claims) Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(jwtSecret))
	        		.parseClaimsJws(token).getBody();
	    }
		 
		 public static String getUserEmailFromToken(String token) {
		        Claims claims = getClaimsFormToken(token);
		        Map<String, Object> map = new HashMap<>(claims);
		        String username = (String) map.get("sub");
		        
		        return username;
		    }

	
	//jwt 유효성 검사 메소드
	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			logger.error("Invalid JWT signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			logger.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty: {}", e.getMessage());
		}

		return false;
	}
	
	
}