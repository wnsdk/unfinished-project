package com.daildra.daildra.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

//    private final JwtTokenProvider jwtTokenProvider;
//
//    @Autowired
//    public SecurityConfiguration(JwtTokenProvider jwtTokenProvider) {
//        this.jwtTokenProvider = jwtTokenProvider;
//    }

    /**
     * 어떤 URL이 secured 되어야하고, 어떤 URL이 secured 될 필요없는지 정하는 파트
     * */
    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                // UI를 사용하는 것을 기본값으로 가진 시큐리티 설정을 비활성화
//                .httpBasic().disable()
//
//                // REST API에서는 CSRF 보안을 비활성화 (기본적으로 스프링 시큐리티는 CSRF 토큰을 발급해서 요청을 받을 때마다 토큰을 검증함)
//                .csrf().disable()
//
//                // (지금은) 세션은 사용하지 않기 때문에 STATELESS로 설정
//                .sessionManagement()
//                .sessionCreationPolicy(
//                        SessionCreationPolicy.STATELESS
//                )
//
//                .and()
//                // 들어오는 요청에 대한 사용 권한을 체크
//                .authorizeRequests()
//                // 로그인, 회원가입 요청은 모두에게 허용
//                .requestMatchers(new AntPathRequestMatcher("/account/sign-in", "/account/sign-up")).permitAll()
//                // '/product'로 시작하는 GET 요청은 모두 허용
////                .requestMatchers(new AntPathRequestMatcher(HttpMethod.GET, "/product/**")).permitAll()
//                // 'exception' 단어가 들어간 경로는 모두 허용
//                .requestMatchers(new AntPathRequestMatcher("**exception**")).permitAll();
//                // 기타 요청은 인증된 권한을 가진 사용자에게 허용
////                .antMatchers().hasRole("ADMIN")
//
////                .and()
////                // 권한을 확인하는 과정에서 통과하지 못하는 예외가 발생할 경우 예외를 전달
////                .exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler())
////                .and()
////                // 인증 과정에서 예외가 발생할 경우 예외를 전달
////                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())
////
////                .and()
////                // 지금까지 설정한 필터는 JwtAuthenticationFilter를 거친 후에 실행된다.
////                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

        httpSecurity
                .authorizeHttpRequests((requests) -> requests
                        // 로그인, 회원가입은 인증되지 않은 사람도 요청 가능
                        .requestMatchers("/account/sign-in", "account/sign-up").permitAll()
                        // 그 밖의 요청들은 인증된 사람만 가능
                        .anyRequest().authenticated()
                );

        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }

//     WebSecurity는 HttpSecurity 앞단에 적용된다.
//     전체적으로 스프링 시큐리티의 영향권 밖에 있다. 즉, 인증과 인가가 모두 적용되기 전에 동작한다.
//     따라서, 인증과 인가가 필요없는 리소스 접근에 대해서만 사용한다.
//    @Override
//    public void configure(WebSecurity webSecurity) {
//        webSecurity.ignoring().antMatchers("/v2/api-docs");
//    }
}