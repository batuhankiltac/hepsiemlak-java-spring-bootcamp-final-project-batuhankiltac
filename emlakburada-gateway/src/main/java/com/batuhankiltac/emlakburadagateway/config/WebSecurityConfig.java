package com.batuhankiltac.emlakburadagateway.config;

import com.batuhankiltac.emlakburadagateway.filter.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final JwtFilter filter;

    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.httpBasic().disable()
                .formLogin().disable()
                .csrf().disable();
        return http.build();
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("emlakburada-auth",
                        r -> r.method(HttpMethod.POST)
                                .and()
                                .path("/auth/**")
                                .uri("http://localhost:8084"))
                .route("emlakburada-advert",
                        r -> r.method(HttpMethod.POST, HttpMethod.GET, HttpMethod.PUT, HttpMethod.DELETE)
                                .and()
                                .path(("/adverts/**"))
                                .filters(f -> f.filter(filter)).uri("http://localhost:8080"))
                .route("emlakburada-user",
                        r -> r.method(HttpMethod.POST, HttpMethod.PUT, HttpMethod.GET)
                                .and()
                                .path("/users/**", "/products/**")
                                .filters(f -> f.filter(filter)).uri("http://localhost:8081"))
                .route("emlakburada-banner",
                        r -> r.method(HttpMethod.POST, HttpMethod.PUT, HttpMethod.GET, HttpMethod.DELETE)
                                .and()
                                .path("/banners/**")
                                .filters(f -> f.filter(filter)).uri("http://localhost:8082"))
                .route("emlakburada-payment",
                        r -> r.method(HttpMethod.POST, HttpMethod.GET)
                                .and()
                                .path("/payments/**")
                                .filters(f -> f.filter(filter)).uri("http://localhost:8083"))
                .build();
    }
}