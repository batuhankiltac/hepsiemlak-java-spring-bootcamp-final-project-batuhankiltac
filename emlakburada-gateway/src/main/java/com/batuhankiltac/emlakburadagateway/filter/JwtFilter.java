package com.batuhankiltac.emlakburadagateway.filter;

import com.batuhankiltac.emlakburadagateway.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtFilter implements GatewayFilter {
    private static final String AUTHORIZATION = "Authorization";
    private final JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        Mono<Principal> principal = exchange.getPrincipal();
        log.info("principal: " + principal);

        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        log.info("remote address: " + Objects.requireNonNull(request.getRemoteAddress()).getAddress().toString());
        log.info("URI: " + request.getURI());

        HttpHeaders headers = exchange.getRequest().getHeaders();

        boolean isContainsAuth = headers.containsKey(AUTHORIZATION);

        if (!isContainsAuth) {
            log.info("Header has not AUTHORIZATION!");
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        List<String> headerList = headers.get(AUTHORIZATION);
        String token = Objects.requireNonNull(headerList).get(0);

        if (token.isBlank()) {
            log.info("Header has AUTHORIZATION but has not Token!");
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        if (jwtUtil.isValidToken(token)) {
            log.info("Token is not valid: " + token);
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

		if (jwtUtil.isTokenExpired(token)) {
			log.info("Token is expired: " + token);
			response.setStatusCode(HttpStatus.UNAUTHORIZED);
			return response.setComplete();
		}
        log.info("Token is valid.");

        Claims claims = jwtUtil.getAllClaimsFromToken(token);
        exchange.getRequest().mutate().header("id", String.valueOf(claims.get("id"))).build();

        return chain.filter(exchange);
    }
}