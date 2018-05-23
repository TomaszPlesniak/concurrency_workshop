package com.tplesniak.concurrency;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentCollectionsExample {

    public static void main(String[] args) {

        List<Integer> list = new CopyOnWriteArrayList<>();

        Runnable writer = () -> {
            int count = 0;

            while (true) {

                try {
                    Thread.sleep(2000);

                } catch (InterruptedException e) {

                }
                list.add(++count);
                System.out.println("\n" + count + " added to the list");
            }

        };

        Runnable reader = () -> {
            while (true) {
                Iterator<Integer> iterator = list.iterator();

                while (iterator.hasNext()) {

                    //fake processing time
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {

                    }
                    System.out.print(iterator.next() + " ");
                }
            }
        };

        ExecutorService service = Executors.newFixedThreadPool(20);

        service.submit(writer);
        service.submit(reader);

        if (service != null) service.shutdown();

    }
}
