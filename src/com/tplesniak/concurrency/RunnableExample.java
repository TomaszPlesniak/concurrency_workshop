package com.tplesniak.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunnableExample {

    private static volatile boolean flag;

    public static void main(String[] args) throws InterruptedException {

        ExecutorService service = Executors.newFixedThreadPool(20);

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


        Thread.sleep(5000);
        System.out.println("First thread - Changing flag");
        flag = true;
        System.out.println("First thread - end");

        if (service != null) service.shutdown();
    }
}
