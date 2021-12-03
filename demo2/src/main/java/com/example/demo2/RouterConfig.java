package com.example.demo2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

/**
 * 각 Bean을 주석 해제해 가며 테스트
 * */
@Configuration
@EnableWebFlux
public class RouterConfig {

    /**
     * 라우터 기본 방식
     * */
    @Bean
    public RouterFunction<ServerResponse> routerExample(ProdoHandler prodoHandler) {
        return RouterFunctions.route()
                .GET("/post/{id}", prodoHandler::getById)
                .POST("/post", prodoHandler::create)
                .POST("/post/json", accept(MediaType.APPLICATION_JSON), prodoHandler::createFromMono)
                                .build();
    }


    @Bean
    public RouterFunction<ServerResponse> routerExample2(ProdoHandler prodoHandler) {
        return RouterFunctions
                .route(GET("/post"), prodoHandler::getById)
                .andRoute(POST("/post").and(accept(MediaType.APPLICATION_FORM_URLENCODED)), prodoHandler::create)
                .andRoute(POST("/post/mono").and(accept(MediaType.APPLICATION_JSON)), prodoHandler::createFromMono)
                .andRoute(POST("/post/flux").and(accept(MediaType.APPLICATION_JSON)), prodoHandler::createFromFlux);
    }

}
