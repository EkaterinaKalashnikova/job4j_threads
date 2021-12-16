package ru.job4j.pool;

import ru.job4j.thread.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final int size = Runtime.getRuntime().availableProcessors();
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(size);

    private void init() {
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
        if (threads.isEmpty()) {
            init();
        }
        tasks.offer(job);
    }

    public void shutdown() {
        threads.forEach(Thread::interrupt);
    }
}

/**Делаете конструктор, в котором инициализируете пул, помещаете в него потоки,
 * каждый из которых в бесконечном цикле берет из очереди очередную задачу и выполняют ее.
 * Подсказка: очень похоже на ваш код.

 А в методе void work(Runnable job) помещаете очередную задачу в очередь*/