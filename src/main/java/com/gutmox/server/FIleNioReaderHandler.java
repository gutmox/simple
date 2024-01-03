package com.gutmox.server;

import com.gutmox.server.nio.AsyncNioFileReader;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class FIleNioReaderHandler implements HttpHandler {

    public FIleNioReaderHandler() {
    }

    @Override
    public void handle(HttpExchange exchange) {
        AsyncNioFileReader.readFileAsync("random.json")
                .thenAccept(response -> buildResponse(exchange, response, 200));
    }

    private static void buildResponse(HttpExchange exchange, String response, int resultCode) {
        try {
            exchange.sendResponseHeaders(resultCode, response.length());
            exchange.getResponseBody().write(response.getBytes());
            exchange.getResponseBody().close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
