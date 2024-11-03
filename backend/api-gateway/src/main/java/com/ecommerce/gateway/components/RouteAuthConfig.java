package com.ecommerce.gateway.components;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@Configuration
@ConfigurationProperties(prefix = "app.route-auth")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RouteAuthConfig {
    List<AuthRule> rules;

    @Data
    public static class AuthRule {
        private String path;
        private List<String> methods;
    }
}