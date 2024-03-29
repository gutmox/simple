package com.gutmox.server.nio;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class FutureAsync {

    public static void main(String[] args) {
        System.out.println("Executing from: " + Thread.currentThread().getName());
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Executing from: " + Thread.currentThread().getName();
        });
        completableFuture.thenAccept(System.out::println);
        completableFuture.join();
    }
}
