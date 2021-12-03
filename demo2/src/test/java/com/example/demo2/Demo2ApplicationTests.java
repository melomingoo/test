package com.example.demo2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Demo2ApplicationTests {
    @Autowired
    private WebTestClient webTestClient;
    @Test
    void contextLoads() {
        Prodo neo = new Prodo("aa","bb");
        webTestClient
                // Create a GET request to test an endpoint
                .post().uri("/post/mono")
                .bodyValue(neo)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                // and use the dedicated DSL to test assertions against the response
                .expectStatus().isOk()
                .expectBody(Prodo.class).value(s -> {
                    assertThat(s.getTitle()).isEqualTo("aa");
                    assertThat(s.getContent()).isEqualTo("bb");
                });
    }

}
