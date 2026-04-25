package com.pula.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("booking-service", r -> r
                        .path("/msapi/bookings/**")
                        .uri("lb://booking-service"))
                .route("user-service", r -> r
                        .path("/msapi/users/**")
                        .uri("lb://user-service"))
                .route("salon-service", r -> r
                        .path("/msapi/salons/**")
                        .uri("lb://salon-service"))
                .route("category-service", r -> r
                        .path("/msapi/categories/**")
                        .uri("lb://category-service"))
                .route("service-offering", r -> r
                        .path("/msapi/service-offering/**")
                        .uri("lb://service-offering"))
                .route("payment-service", r -> r
                        .path("/msapi/payments/**")
                        .uri("lb://payment-service"))
                .build();
    }
}
