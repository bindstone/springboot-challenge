package com.bindstone.helloserver.api;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
@Slf4j
public class HelloApi {

    @NotNull
    private static Mono<String> getHelloWorld() {
        log.info("Request for Hello");
        return Mono.just("Hello World");
    }

    @Bean
    RouterFunction<ServerResponse> getHello() {
        return route(GET("/hello"), req -> ok().body(getHelloWorld(), String.class));
    }
}
