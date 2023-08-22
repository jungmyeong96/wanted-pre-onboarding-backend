package com.wanted.jungproject.config.auth;

import com.wanted.jungproject.config.auth.application.CustomOauthUserService;
import com.wanted.jungproject.domain.auth.application.TokenProvider;
import com.wanted.jungproject.domain.auth.entrypoint.JwtAuthenticationEntryPoint;
import com.wanted.jungproject.domain.auth.filter.ExceptionHandlerFilter;
import com.wanted.jungproject.domain.auth.filter.JwtAuthenticationFilter;
import com.wanted.jungproject.domain.auth.handler.JwtAccessDeniedHandler;
import com.wanted.jungproject.domain.user.domain.Role;
import com.wanted.jungproject.domain.user.domain.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.security.Principal;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig {
//    private final TokenProvider tokenProvider;
//    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
//    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
//    private final UsersRepository usersRepository;
//    @Value("${jwt.secret}")
//    private String secretKey;

    @Autowired
    private CustomOauthUserService customOauthUserService;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .httpBasic(httpBasic -> httpBasic.disable())
                .csrf(AbstractHttpConfigurer::disable) // post 방식으로 값을 전송할 때 token을 사용해야하는 보안 설정을 해제
//                .exceptionHandling(exceptionHandling ->
//                        exceptionHandling
//                                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
//                                .accessDeniedHandler(jwtAccessDeniedHandler)
//                )
//                .sessionManagement(
//                        sessionManagement ->
//                                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                )
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
//                                .requestMatchers("/users/**").authenticated()
//                                .requestMatchers("/oauth2/**").permitAll()
//                                .requestMatchers("/users/signup").permitAll()
//                                .requestMatchers("/signupform").permitAll()
//                                .requestMatchers("/signinform").permitAll()
                                .requestMatchers("/swagger-ui/**", "/v3/apiXdocs/**", "/swagger-ui-jung.html", "/webjars/**").permitAll()
                                .requestMatchers("/").permitAll()
                                .requestMatchers("/board/**").hasRole(Role.USER.name())
                                .requestMatchers("/adminpage").hasRole(Role.ADMIN.name())
                                .anyRequest().permitAll()
                )
                .formLogin(
                        login ->
                                login.loginPage("/signinform")
                                        .loginProcessingUrl("/login")
                                        .defaultSuccessUrl("/board/mainpage")
                )
                .oauth2Login(
                        oauth ->
                                oauth.loginPage("/signinform").userInfoEndpoint(
                                        userinfo ->
                                                userinfo.userService(customOauthUserService)
                                )
                );
//                .addFilterBefore(new JwtAuthenticationFilter(secretKey), UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(new ExceptionHandlerFilter(), JwtAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/v3/api-docs/**");
    }

}
