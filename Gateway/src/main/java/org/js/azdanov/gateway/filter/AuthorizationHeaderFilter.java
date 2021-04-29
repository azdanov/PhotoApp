package org.js.azdanov.gateway.filter;

import io.jsonwebtoken.Jwts;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {

    private final Environment env;

    public AuthorizationHeaderFilter(Environment environment) {
        super(Config.class);
        env = environment;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            String authorizationsHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

            if (authorizationsHeader == null) {
                return onError(exchange, "No authorization header", HttpStatus.UNAUTHORIZED);
            }

            String jwt = authorizationsHeader.replace("Bearer", "");

            if (!isValidJwt(jwt)) {
                return onError(exchange, "JWT token is not valid", HttpStatus.UNAUTHORIZED);
            }

            return chain.filter(exchange);
        };
    }

    private Mono<Void> onError(ServerWebExchange exchange, String message, HttpStatus status) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(status);

        return response.setComplete();
    }

    private boolean isValidJwt(String jwt) {
        String subject;
        try {
            subject = Jwts.parser()
                .setSigningKey(env.getProperty("jwt.secret"))
                .parseClaimsJws(jwt)
                .getBody()
                .getSubject();
        } catch (Exception e) {
            return false;
        }

        return subject != null && !subject.trim().isEmpty();
    }

    public static class Config {

    }
}
