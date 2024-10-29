package com.ecommerce.gateway.components;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;

@SpringBootTest
class RouteValidatorTests {

    @Autowired
    private RouteValidator routeValidator;

    @Test
    void shouldIdentifySecuredPath() {
        ServerHttpRequest securedRequest = MockServerHttpRequest.get("/api/secured/data").build();
        assertTrue(routeValidator.isSecured.test(securedRequest), "Path should be identified as secured.");
    }

    @Test
    void shouldIdentifyUnsecuredPath() {
        ServerHttpRequest unsecuredRequest = MockServerHttpRequest.get("/api/public/data").build();
        assertFalse(routeValidator.isSecured.test(unsecuredRequest), "Path should be identified as unsecured.");
    }
}
