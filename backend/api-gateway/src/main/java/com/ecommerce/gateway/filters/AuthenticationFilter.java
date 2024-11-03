package com.ecommerce.gateway.filters;

import com.ecommerce.gateway.components.RouteAuthValidator;
import com.ecommerce.jwt.JwtProviderInterface;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RefreshScope
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationFilter implements GatewayFilter {

    RouteAuthValidator routeValidator;
    JwtProviderInterface jwtProvider;

    @Value("${app.authentication.enabled}")
    Boolean authEnabled;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if (!authEnabled) {
            log.info("Authentication is disabled. To enable it, make \"authentication.enabled\" property as true");
            return chain.filter(exchange);
        }

        ServerHttpRequest request = exchange.getRequest();

        if (routeValidator.isSecured.test(request)) {
            String token = "";
            if (this.isCredsMissing(request)) {
                return this.onError(exchange, "Credentials missing", HttpStatus.UNAUTHORIZED);
            }
            if (request.getHeaders().containsKey("userName") && request.getHeaders().containsKey("role")) {
//                token = authUtil.getToken(request.getHeaders().get("userName").toString(), request.getHeaders().get("role").toString());
            } else {
                token = request.getHeaders().get("Authorization").toString().split(" ")[1];
            }

            if (jwtProvider.validateToken(token)) {
                return this.onError(exchange, "Auth header invalid", HttpStatus.UNAUTHORIZED);
            }

            this.populateRequestWithHeaders(exchange, token);
        }
        return chain.filter(exchange);
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    private String getAuthHeader(ServerHttpRequest request) {
        return request.getHeaders().getOrEmpty("Authorization").get(0);
    }


    private boolean isCredsMissing(ServerHttpRequest request) {
        return !(request.getHeaders().containsKey("username") && request.getHeaders().containsKey("role")) && !request.getHeaders().containsKey("Authorization");
    }

    private void populateRequestWithHeaders(ServerWebExchange exchange, String token) {
//        Claims claims = jwtUtil.getALlClaims(token);
        exchange.getRequest()
                .mutate()
//                .header("id", String.valueOf(claims.get("id")))
//                .header("role", String.valueOf(claims.get("role")))
                .build();
    }
}