package com.lcom_test.example.config;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import com.lcom_test.example.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter{
	// AuthTokenFilter는 OncePerRequestFilter 추상 클래스를 상속받는다.
	// OncePerRequestFilter는 모든 서블릿에 일관된 요청을 처리하는 필터이다.
	// 이 필터는 한 번의 요청에 대해 정확히 한 번 실행된다. (보안 인증 작업에 유용)
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private JwtUtils jwtUtils;
	// 토큰을 관리하는 Class
	
	@Autowired
	private UserService userService;
	
	
	//filter -> servelet 실행 전 실행 dofilter 실행시 최종적으로  dofilterInternal 을 실행 doFilter는 전후처리
	// OncePerReqiestFilter를 상속하는 경우 doFilter 대신 doFilterInternal 메서드 구현하면 됨
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			//parseJwt 메소드 사용
			String jwt = parseJwt(request);
			
			//jwt 토큰이 null 이 아니고 유효한 토큰일때
			if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
				
				//jwt를 통한 회원 아이디 추출
				String username = jwtUtils.getUserNameFromJwtToken(jwt);
				
				//security를 통한 인증 로직
				UserDetails userDetails = userService.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} catch (Exception e) {
			logger.error("Cannot set user authentication: {}", e);
		}

		filterChain.doFilter(request, response);
		
	}


	private String parseJwt(HttpServletRequest request) {
		
		//header 중에서 Authorization(인증된) key의 value 값을 가져옴
		String headerAuth = request.getHeader("Authorization");
		
		//jwt 가져옴
		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
			return headerAuth.substring(7, headerAuth.length());
		}
		// StringUtils.hasText()로 Header에서 token값을 가져오고, 그 token의 시작이 "Bearer" 이라면 ~
		// 위 조건에 부합하는 토큰이면 return => headerAuth.substring(7, headerAuth.length());

		return null;
		// 조건에 맞지 않는 토큰이라면 null 반환
	}
	
	
}
