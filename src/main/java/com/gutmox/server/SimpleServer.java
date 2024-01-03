package com.gutmox.server;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class SimpleServer {

    public static void main(String[] args) throws IOException {
        new SimpleServer().start();
    }

    public void start() throws IOException {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(8080), 0);
        httpServer.createContext("/health", new SimpleHandler());
        httpServer.createContext("/file", new FIleNioReaderHandler());
        httpServer.createContext("/postgres", new VersionPostgresHandler());
        httpServer.setExecutor(Executors.newVirtualThreadPerTaskExecutor());
        httpServer.start();
    }
}