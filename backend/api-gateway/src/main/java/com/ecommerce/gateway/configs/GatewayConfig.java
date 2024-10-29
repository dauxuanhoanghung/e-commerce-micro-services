package com.ecommerce.gateway.configs;

import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * GatewayConfig
 */
@Configuration
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class GatewayConfig {

    @Value("${spring.profiles.active}")
    Boolean isProdMode;

    @Value("${app.logging.filter.enabled}")
    Boolean isLoggerEnabled;


}
