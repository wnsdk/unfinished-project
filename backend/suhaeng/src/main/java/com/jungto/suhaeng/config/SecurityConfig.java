package com.jungto.suhaeng.config;

import com.jungto.suhaeng.config.oauth2.OAuth2SuccessHandler;
import com.jungto.suhaeng.config.jwt.TokenAuthenticationFilter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;
import java.util.List;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final OAuth2UserService oAuth2UserService;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;
    private final TokenAuthenticationFilter tokenAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                //localhost:5173 에 대한 CORS 허용
                .cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(List.of("http://localhost:5173"));
//                        config.setAllowedOrigins(Collections.singletonList("*"));
                        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
                        config.setAllowCredentials(true);
                        config.setAllowedHeaders(Collections.singletonList("*"));
                        config.setMaxAge(3600L);
                        return config;
                    }
                }))

                .httpBasic(Customizer.withDefaults()) // 기본 인증 로그인 비활성화
                .formLogin(Customizer.withDefaults()) // 기본 login form 비활성화
                .logout(Customizer.withDefaults()) // 기본 logout 비활성화

                .authorizeHttpRequests((requests) ->
                        requests
//                                .requestMatchers("/feed").authenticated() //접근 권한 필요
                                .requestMatchers("/**").permitAll()) //누구나 접근 가능

                //oauth 관련 설정
                .oauth2Login(oauth ->
                        oauth.userInfoEndpoint(c -> c.userService(oAuth2UserService))
                                // 로그인 성공 시 핸들러
                                .successHandler(oAuth2SuccessHandler))

                //jwt 관련 설정
                .addFilterBefore(tokenAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class)
        //                .addFilterBefore(new TokenExceptionFilter(), tokenAuthenticationFilter.getClass()); // 토큰 예외 핸들링
        ;

        return http.build();
    }
}