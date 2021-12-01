package ru.job4j.thread;

public class ExamCountBarrier {
    public static void main(String[] args) {
        int total = 10;
        CountBarrier countBarrier = new CountBarrier(total);
        Thread director = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " started");
            countBarrier.count();
        },
                "director");
        Thread worker = new Thread(() -> {
            countBarrier.await();
            System.out.println(Thread.currentThread().getName() + " started");
        }, "worker");
        worker.start();
        director.start();
    }
}
