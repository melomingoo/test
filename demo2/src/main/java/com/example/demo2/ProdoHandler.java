package com.example.demo2;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Component
public class ProdoHandler {


    public Mono<ServerResponse> getById(ServerRequest request) {
        Long id = Long.parseLong(request.pathVariable("id"));
        Prodo post = new Prodo("garden", "hello");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(post);
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        Mono<MultiValueMap<String, String>> formData = request.formData();

        return formData.flatMap(data -> {
            Map<String, String> dataMap = data.toSingleValueMap();
            String title = dataMap.getOrDefault("title", null);
            String content = dataMap.getOrDefault("content", null);

            Prodo newPost = new Prodo(title, content);
            return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(newPost);
        });
    }

    // json data 추출
    public Mono<ServerResponse> createFromMono(ServerRequest request) {
//        Mono<Prodo> PostMono = Mono.just(new Prodo("aa","cc"));
        Mono<Prodo> prodo = request.bodyToMono(Prodo.class);
        System.out.println(request.toString());
        System.out.println(request.headers().toString());
        System.out.println(request.attributes().toString());


        return prodo.flatMap(p ->ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(p));

    }

    public Mono<ServerResponse> createFromFlux(ServerRequest request) {
//        Mono<Prodo> PostMono = Mono.just(new Prodo("aa","cc"));
        Mono<List<Prodo>> prodo = request.bodyToFlux(Prodo.class).collectList();
        System.out.println(request.toString());
        System.out.println(request.headers().toString());
        System.out.println(request.attributes().toString());


        return prodo.flatMap(p ->ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(p));

    }

}
