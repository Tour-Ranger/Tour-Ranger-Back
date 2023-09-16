package com.tourranger.common.security;

import com.tourranger.common.error.CustomErrorCode;
import com.tourranger.common.exception.CustomException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig {

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// CSRF 사용 X
		http.csrf((csrf) -> csrf.disable());

		// 세션 필요할 때 사용
		http.sessionManagement(
				(sessionManagement) -> sessionManagement
						.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
						.maximumSessions(1)    // 최대 허용 가능 세션 수
						.maxSessionsPreventsLogin(false)    // 멀티로그인 차단, false : 기존 세션 만료(default)
		);

		// 모든 API 인가 필터 거치지 않게 설정
		http.authorizeHttpRequests(
				(authorizeHttpRequests) -> authorizeHttpRequests
						.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
						.requestMatchers("/tour-ranger/**").permitAll()
						.requestMatchers("/image/**").permitAll()
						.anyRequest().authenticated()
		);

		// 기본 로그인
		http.formLogin(
				(formLogin) -> formLogin
						.usernameParameter("email")
						.successHandler(
								(request, response, authentication) -> {
									response.sendRedirect("/tour-ranger/");
								}
						)
						.failureUrl("/login")
						.failureHandler(
								(request, response, exception) -> {
									throw new CustomException(CustomErrorCode.LOGIN_FAILURE, null);
								}
						)
		);

//		http.logout((logout) -> logout
//				.logoutUrl("/logout")
//				.logoutSuccessUrl("/"));

		return http.build();
	}
}
