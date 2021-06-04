package com.lcom_test.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.lcom_test.example.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserService userService;
	// custom Interface
	
	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;
	// AuthEntryPointJwt Class는 AuthenticationEntryPoint interface를 구현하는 내가 만든 Class
	// 401 Error 처리 로직
	
	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}
	// authenticationJwtTokenFilter() 를 Spring에 Bean으로 등록

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(passwordEncoder());
	} 
	// WebSecurityConfigurerAdapter Class 메소드 Override
	// 인증을 담당할 AuthenticationProvider을 설정하는 메소드
	// UserDetailsService 구현체와 PasswordEncoder 구현체를 설정

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	// 외부에서 인증관리자를 사용하기 위한 설정
	// 스프링 2.x Version 에서는 자동등록이 되지 않으므로 메소드를 호출하여 @Bean으로 등록해줘야 한다.

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	//BCryptPasswordEncoder 방식으로 비밀번호 암호화

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
			//권한 없음 에러 처리
			.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			
			//해당 경로 모두 허용
			.authorizeRequests().antMatchers("/api/auth/**").permitAll()
			.antMatchers("/api/test/**").permitAll()
			.antMatchers("/api/admin/**").permitAll()
			
			//이외의 경로 인증처리 된 유저만 접근 가능
			.anyRequest().authenticated();

		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	// WebSecurityConfigurerAdapter Class 메소드 Override
	// 요청 URL에 대한 권한 설정, 특정 기능 결과에 대한 Handler 등록, Custom Filter 등록
	// 예외 Handler 등을 등록 하는 메소드

}