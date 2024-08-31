package com.daildra.daildra.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.XorCsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public WebSecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    protected SecurityFilterChain apiFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
////                // 권한을 확인하는 과정에서 통과하지 못하는 예외가 발생할 경우 예외를 전달
////                .exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler())
////                .and()
////                // 인증 과정에서 예외가 발생할 경우 예외를 전달
////                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())

        httpSecurity.httpBasic().disable()
                // SPA 방식은 csrf 공격에서 안전한 편인듯? 그러니 disable 시키기
                .csrf(csrf -> csrf.disable())
                
                // 아래 선언한 corsConfigurationSource가 이 시점에서 실행되는 듯?
                .cors()

                // JWT 토큰으로 인증을 처리하며, 세션은 사용하지 않기 때문에 STATELESS
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                
                .and()
                .authorizeHttpRequests((requests) -> requests
                        // 로그인, 회원가입은 인증되지 않은 사람도 요청 가능
                        .requestMatchers("/account/log-in", "/account/sign-up").permitAll()
                        // 그 밖의 요청들은 인증된 사람만 가능
                        .anyRequest().hasRole("ADMIN")
                )

                // JwtAuthenticationFilter를 거친 후 id/password 인증 필터 실행
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("user")
//                        .password("password")
//                        .roles("USER")
//                        .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
//        해당 출처에서 오는 요청은 허락한다.
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        
//        해당 패턴을 가진 출처에서 오는 요청은 허락한다.
//        configuration.addAllowedOriginPattern("*");
        
//        3가지 요청(예비요청 / 단순요청 / 인증된요청) 중, 인증된요청 처리와 관련된 부분
        configuration.setAllowCredentials(true);
        
        configuration.addAllowedHeader("*");
        
//        GET, POST, PUT, DELETE 등의 메서드 관련 부분
        configuration.addAllowedMethod("*");
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

//     WebSecurity는 HttpSecurity 앞단에 적용된다.
//     전체적으로 스프링 시큐리티의 영향권 밖에 있다. 즉, 인증과 인가가 모두 적용되기 전에 동작한다.
//     따라서, 인증과 인가가 필요없는 리소스 접근에 대해서만 사용한다.
//    @Override
//    public void configure(WebSecurity webSecurity) {
//        webSecurity.ignoring().antMatchers("/v2/api-docs");
//    }
}