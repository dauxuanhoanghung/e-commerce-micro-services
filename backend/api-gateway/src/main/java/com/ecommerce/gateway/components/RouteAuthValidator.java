package com.ecommerce.gateway.components;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RouteAuthValidator {
    final RouteAuthConfig config;

    public RouteAuthValidator(RouteAuthConfig routeAuthConfig) {
        this.config = routeAuthConfig;
    }

    public Predicate<ServerHttpRequest> isAuthRequired() {
        return request -> {
            String path = request.getURI().getPath();
            return config.getRules().stream()
                    .anyMatch(rule -> path.contains(rule.getPath()));
        };
    }

    public Predicate<ServerHttpRequest> isSecured = request -> request.getURI().getPath().contains("secured");
}
