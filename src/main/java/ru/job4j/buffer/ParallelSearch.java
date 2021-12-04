package ru.job4j.buffer;

import ru.job4j.thread.SimpleBlockingQueue;

public class ParallelSearch {
    public static void main(String[] args) throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<Integer>(7);
        System.out.println("main starts");
        final Thread consumer = new Thread(
                () -> {
                    try {
                        int counter = 0;
                        while (!Thread.currentThread().isInterrupted()) {
                            System.out.println(queue.poll());
                            System.out.println("counter:" + counter++);
                            Thread.sleep(500);
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
        );
        consumer.start();
        final Thread producer = new Thread(
                () -> {
                    try {
                        for (int index = 0; index != 3; index++) {
                            queue.offer(index);
                            Thread.sleep(500);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        producer.start();
        producer.join();
        consumer.interrupt();
        System.out.println("main ends");
    }
}
