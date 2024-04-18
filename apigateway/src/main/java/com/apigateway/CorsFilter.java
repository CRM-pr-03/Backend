package com.apigateway;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import java.util.*;
@Component
public class CorsFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        Set<String> allowedOrigins = new HashSet<>();
        exchange.getRequest().getHeaders().getOrDefault("Origin", List.of()).forEach(origin -> allowedOrigins.add(origin));
        exchange.getResponse().getHeaders().add("Access-Control-Allow-Origin","http://localhost:4200" );
        exchange.getResponse().getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, PATCH, OPTIONS");
        exchange.getResponse().getHeaders().add("Access-Control-Allow-Headers", "Content-Type, Authorization");
        exchange.getResponse().getHeaders().add("Access-Control-Max-Age", "3600");

        if (exchange.getRequest().getMethod().equals(HttpMethod.OPTIONS)) {
            exchange.getResponse().setStatusCode(HttpStatus.OK);
            return Mono.empty();
        }

        return chain.filter(exchange);
    }
}
