package com.plate.silverplate.config;

import com.plate.silverplate.common.exception.MyAuthenticationFailureHandler;
import com.plate.silverplate.common.exception.MyAuthenticationSuccessHandler;
import com.plate.silverplate.user.jwt.fillter.JwtAuthFilter;
import com.plate.silverplate.user.jwt.fillter.JwtExceptionFilter;
import com.plate.silverplate.user.service.OauthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {
    // filter
    private final JwtAuthFilter jwtAuthFilter;
    private final JwtExceptionFilter jwtExceptionFilter;

    // service
    private final OauthService oAuth2UserService;

    // handler
    private final MyAuthenticationSuccessHandler oAuth2LoginSuccessHandler;
    private final MyAuthenticationFailureHandler oAuth2LoginFailureHandler;


    @Value("${cors.allowed-origins}")
    String[] corsOrigins;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .cors(corsConfig -> corsConfig.configurationSource(configurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagementConfig ->
                        sessionManagementConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/**").permitAll()         // TODO: 권한에 따른 접근 가능 url 설정 예정
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtExceptionFilter, JwtAuthFilter.class)
                .oauth2Login(oauth2 -> oauth2.userInfoEndpoint(
                        userInfo -> userInfo.userService(oAuth2UserService))
                        .failureHandler(oAuth2LoginFailureHandler)
                        .successHandler(oAuth2LoginSuccessHandler)
                )
                .build();
    }

    @Bean
    public CorsConfigurationSource configurationSource() {
        // TODO: 나중에 클라이언트 주소만 허용할 예정
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOriginPatterns(List.of(corsOrigins));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setExposedHeaders(List.of("Access-Control-Allow-Credentials", "Authorization", "Set_Cookie"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}