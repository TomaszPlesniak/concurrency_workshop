package com.tplesniak.concurrency;

public class RunnableExample {

    private static volatile boolean flag;

    public static void main(String[] args) throws InterruptedException {

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

        Thread thread = new Thread(runnable);
        thread.start();

        Thread.sleep(5000);
        System.out.println("First thread - Changing flag");
        flag = true;
        System.out.println("First thread - end");
    }
}
