package org.js.azdanov.gateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
public class GlobalFiltersConfigurations {

    final Logger logger = LoggerFactory.getLogger(GlobalFiltersConfigurations.class);

    @Order(1)
    @Bean
    public GlobalFilter secondPrePostFilter() {
        return (ServerWebExchange exchange, GatewayFilterChain chain) -> {
            logger.info("My second Pre-filter is executed...");

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                logger.info("My second Post-filter is executed...");
            }));
        };
    }

    @Order(2)
    @Bean
    public GlobalFilter thirdPrePostFilter() {
        return (ServerWebExchange exchange, GatewayFilterChain chain) -> {
            logger.info("My third Pre-filter is executed...");

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                logger.info("My third Post-filter is executed...");
            }));
        };
    }

    @Order(3)
    @Bean
    public GlobalFilter fourthPrePostFilter() {
        return (ServerWebExchange exchange, GatewayFilterChain chain) -> {
            logger.info("My fourth Pre-filter is executed...");

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                logger.info("My fourth Post-filter is executed...");
            }));
        };
    }
}
