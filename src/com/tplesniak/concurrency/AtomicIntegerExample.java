package com.tplesniak.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerExample {


    private static AtomicInteger counter = new AtomicInteger(0);


    public static void main(String[] args) {

        ExecutorService service = Executors.newFixedThreadPool(20);

        try {

            for (int i = 0; i < 10; i++) {
                service.submit(() -> System.out.print(counter.incrementAndGet() + " "));
            }


        } finally {
            if (service != null) service.shutdown();
        }

    }


}
