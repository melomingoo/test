package com.example.demo2;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class NeoClient {

	private final WebClient client;

	// Spring Boot auto-configures a `WebClient.Builder` instance with nice defaults and customizations.
	// We can use it to create a dedicated `WebClient` for our component.
	public NeoClient(WebClient.Builder builder) {
		this.client = builder.baseUrl("http://localhost:1234").build();
	}

	public Mono<String> setMessage() {
		return this.client.get().uri("/hello").accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(Prodo.class).map(Prodo::getContent);

	}

}
