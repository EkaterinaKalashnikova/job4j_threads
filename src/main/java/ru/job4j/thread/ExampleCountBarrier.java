package ru.job4j.thread;

import ru.job4j.CountBarrier;

public class ExampleCountBarrier implements Runnable {

    private final CountBarrier countBarrier;

    public ExampleCountBarrier(CountBarrier countBarrier) {
        this.countBarrier = countBarrier;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            countBarrier.await();
            System.out.println(Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        int total = 7;
        CountBarrier countBarrier1 = new CountBarrier(total);
        ExampleCountBarrier countBarrier = new ExampleCountBarrier(countBarrier1);
        for (int i = 0; i < total; i++) {
            Thread thread = new Thread(countBarrier);
            thread.start();
        }
    }
}
