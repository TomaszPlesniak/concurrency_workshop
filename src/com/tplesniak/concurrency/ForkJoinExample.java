package com.tplesniak.concurrency;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class ForkJoinExample extends RecursiveAction {

    private int start;
    private int end;
    private Integer[] numbers;

    public ForkJoinExample(Integer[] numbers, int start, int end) {
        this.start = start;
        this.end = end;
        this.numbers = numbers;
    }

    @Override
    protected void compute() {

        if (end - start <= 3) {
            for (int i = start; i < end; i++) {
                numbers[i] = new Random().nextInt(50);
            }
        } else {
            int middle = start + (end - start) / 2;
            System.out.println("[start=" + start + ",middle=" + middle + ",end=" + end + "]");
            invokeAll(new ForkJoinExample(numbers, start, middle), new ForkJoinExample(numbers, middle, end));
        }
    }

    public static void main(String[] args) {
        Integer[] numbers = new Integer[10];

        ForkJoinTask<?> task = new ForkJoinExample(numbers, 0, numbers.length);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(task);

        for (int i : numbers) {
            System.out.print(i + " ");
        }
    }
}
