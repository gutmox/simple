package com.gutmox.server;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;

class SimpleServerTest {

    private static HttpClient httpClient;

    @BeforeAll
    static void setUp() throws Exception {
        new SimpleServer().start();
        SimpleServerTest.httpClient = HttpClient.newHttpClient();
    }

    @Test
    void simpleShouldStart() throws Exception {
        //Mono.fromFuture(httpClient.sendAsync
        var response =
                httpClient.send(HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8080/health"))
                .GET()
                .build(), HttpResponse.BodyHandlers.ofString(UTF_8));
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.body()).isEqualTo("Hello World");
    }

    @Test
    void simpleShouldNotFoundEndpoint() throws Exception {
        var response =
                httpClient.send(HttpRequest.newBuilder()
                        .uri(new URI("http://localhost:8080/non-existing"))
                        .GET()
                        .build(), HttpResponse.BodyHandlers.ofString(UTF_8));
        assertThat(response.statusCode()).isEqualTo(404);
    }
}