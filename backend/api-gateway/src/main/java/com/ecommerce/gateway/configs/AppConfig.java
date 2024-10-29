package com.ecommerce.gateway.configs;

import com.ecommerce.jwt.JwtProvider;
import com.ecommerce.jwt.JwtProviderInterface;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class AppConfig {

    @Value("${app.jwt.secret}")
    String secret;

    @Bean
    public JwtProviderInterface jwtProvider() {
        return new JwtProvider(this.secret);
    }
}
