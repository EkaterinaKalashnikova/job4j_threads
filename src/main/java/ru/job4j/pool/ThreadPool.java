package ru.job4j.pool;

import ru.job4j.thread.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final int size = Runtime.getRuntime().availableProcessors();
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(size);

    public ThreadPool() {
        for (int i = 0; i < size; i++) {
            threads.add(new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        Runnable jobPoll = tasks.poll();
                        jobPoll.run();
                    } catch (Exception e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }));
        }
    }

    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
    }

    public void shutdown() {
        threads.forEach(Thread::interrupt);
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            String name = Thread.currentThread().getName();
            System.out.println("Task executed" + name);
        };
        ThreadPool threadPool = new ThreadPool();
        for (int i = 0; i < 5; i++) {
            threadPool.work(() -> System.out.println(task));
            System.out.println("Task : " + threadPool.threads.get(i));
        }
        threadPool.shutdown();
    }
}

