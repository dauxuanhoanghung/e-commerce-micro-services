package com.ecommerce.gateway.filters.factory;

import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class ErrorLoggingFilterFactory extends AbstractGatewayFilterFactory<ErrorLoggingFilterFactory.Config> {
    private static final Logger logger = LoggerFactory.getLogger(ErrorLoggingFilterFactory.class);

    public ErrorLoggingFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> chain.filter(exchange)
                .then(Mono.defer(() -> {
                    ServerHttpResponse response = exchange.getResponse();
                    int statusCode = response.getStatusCode() != null ?
                            response.getStatusCode().value() : 0;

                    if (statusCode >= 500) {
                        String path = exchange.getRequest().getURI().getPath();
                        String method = exchange.getRequest().getMethod().name();

                        logger.error(
                                "{} Error - Status: {}, Method: {}, Path: {}, Headers: {}",
                                config.getServiceName(),
                                statusCode,
                                method,
                                path,
                                exchange.getRequest().getHeaders()
                        );
                    }
                    return Mono.empty();
                }));
    }

    @Getter
    @Setter
    public static class Config {
        private String serviceName = "Service";
    }
}
