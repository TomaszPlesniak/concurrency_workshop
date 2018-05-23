package com.tplesniak.concurrency;

import java.util.Random;
import java.util.concurrent.*;

public class RunnableExample {

    private static volatile boolean flag;

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService service = Executors.newFixedThreadPool(20);

        Future<Integer> result;

        Callable<Integer> callable = () -> new Random().nextInt(50);


        Runnable runnable = () -> {
            while (true) {

                if (flag) {
                    System.out.println("Second Thread - Flag change received. Finishing thread.");
                    break;
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                }
                System.out.println("Second Thread - working");
            }
        };


        service.submit(runnable);
        result = service.submit(callable);
        System.out.println("drawn number: " + result.get());


        Thread.sleep(5000);
        System.out.println("First thread - Changing flag");
        flag = true;
        System.out.println("First thread - end");

        if (service != null) service.shutdown();
    }
}
