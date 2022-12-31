package com.bindstone.gatewayserver.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.util.matcher.PathPatternParserServerWebExchangeMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityAuthorisationConfig {

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    SecurityWebFilterChain eurekaSecurity(ServerHttpSecurity http) {
        http
            .securityMatcher(new PathPatternParserServerWebExchangeMatcher("/eureka-service/**"))
            .authorizeExchange((exchanges) -> exchanges
                    .anyExchange().permitAll()
            );
        return http.build();
    }

    @Bean
    SecurityWebFilterChain eurekaResourceSecurity(ServerHttpSecurity http) {
         http
         .securityMatcher(new PathPatternParserServerWebExchangeMatcher("/eureka/**"))
         .authorizeExchange((exchanges) -> exchanges
         .anyExchange().permitAll()
         );
        return http.build();
    }

    @Bean
    SecurityWebFilterChain actuatorSecurity(ServerHttpSecurity http) {
        http
            .securityMatcher(new PathPatternParserServerWebExchangeMatcher("/actuator-service/**"))
            .authorizeExchange((exchanges) -> exchanges
                    .anyExchange().permitAll()
            );
        return http.build();
    }

    @Bean
    SecurityWebFilterChain actuatorLocalSecurity(ServerHttpSecurity http) {
        http
                .securityMatcher(new PathPatternParserServerWebExchangeMatcher("/actuator/**"))
                .authorizeExchange((exchanges) -> exchanges
                        .anyExchange().permitAll()
                );
        return http.build();
    }

    @Bean
    SecurityWebFilterChain helloSecurity(ServerHttpSecurity http) {
        http
            .securityMatcher(new PathPatternParserServerWebExchangeMatcher("/hello-service/**"))
            .authorizeExchange((exchanges) -> exchanges
                    .anyExchange().authenticated()
            )
            .httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    @Order(Ordered.LOWEST_PRECEDENCE)
    SecurityWebFilterChain defaultSecurity(ServerHttpSecurity http) {
        http
            .authorizeExchange((authorize) -> authorize
                    .pathMatchers("/**").denyAll()
            );
        return http.build();
    }
}
