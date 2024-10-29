package com.ecommerce.gateway.filters.factory;

import com.ecommerce.core.dto.responses.ApiResponse;
import com.ecommerce.gateway.filters.factory.AuthenticationGatewayFilterFactory.Config;
import com.ecommerce.jwt.JwtProviderInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationGatewayFilterFactory extends AbstractGatewayFilterFactory<Config> {

    final JwtProviderInterface jwtProvider;
    final ObjectMapper objectMapper;

    private static final String BEAR_TOKEN_TYPE = "Bearer ";


    public AuthenticationGatewayFilterFactory(
            JwtProviderInterface jwtProvider,
            ObjectMapper objectMapper
    ) {
        super(Config.class);
        this.jwtProvider = jwtProvider;
        this.objectMapper = objectMapper;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String path = exchange.getRequest().getURI().getPath();

            // Check if the request is for an excluded path
            if (config.getExcludedPaths() != null && config.getExcludedPaths().contains(path)) {
                return chain.filter(exchange); // Skip authentication for excluded paths
            }

            // Check for HTTPS requirement
            if (config.isRequireHttps() && !exchange.getRequest().getURI().getScheme().equals("https")) {
                return onError(exchange, "HTTPS is required", HttpStatus.FORBIDDEN);
            }

            String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");

            // Check token validity
            if (!isValidToken(authHeader, config)) {
                return onError(exchange, "Invalid or missing authorization token", HttpStatus.UNAUTHORIZED);
            }

            return chain.filter(exchange);
        };
    }

    /**
     * Handle error response:
     * <p>
     * 1. Set the response status code <br>
     * 2. Create the ApiResponse object <br>
     * 3. Convert the ApiResponse object to JSON <br>
     * 4. Set the response headers <br>
     * 5. Write JSON to the response body <br>
     *
     * @param exchange
     * @param message
     * @param status
     * @return
     */
    private Mono<Void> onError(ServerWebExchange exchange, String message, HttpStatus status) {
        exchange.getResponse().setStatusCode(status);
        ApiResponse<Object> errorResponse = ApiResponse.builder()
                .code(status.value())
                .message(message)
                .data(null)
                .build();
        try {
            String jsonResponse = objectMapper.writeValueAsString(errorResponse);
            HttpHeaders headers = exchange.getResponse().getHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set(HttpHeaders.CONTENT_LENGTH, String.valueOf(jsonResponse.getBytes(StandardCharsets.UTF_8).length));

            DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(jsonResponse.getBytes(StandardCharsets.UTF_8));
            return exchange.getResponse().writeWith(Mono.just(buffer));
        } catch (Exception e) {
            return Mono.error(new RuntimeException("Error serializing the response", e));
        }
    }

    /**
     * Validate JWT token
     *
     * @param token  The token to validate
     * @param config The configuration
     * @return true if the token is valid, false otherwise
     */
    private Boolean isValidToken(String token, Config config) {
        if (token == null || token.isEmpty()) {
            return false;
        }

        if ("bearer".equalsIgnoreCase(config.getAuthType())) {
            if (token.startsWith(BEAR_TOKEN_TYPE)) {
                String jwt = token.substring(7);
                return jwtProvider.validateToken(jwt);
            }
        }
        return false;
    }

    @Getter
    @Setter
    public static class Config {

        private String authType;
        private List<String> excludedPaths;
        private boolean requireHttps;
    }

}

