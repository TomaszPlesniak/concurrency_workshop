package com.tplesniak.concurrency;

public class RunnableExample {

    public static void main(String[] args) {

        Runnable runnable = () -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                }
                System.out.println("Second Thread - working");
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }
}
