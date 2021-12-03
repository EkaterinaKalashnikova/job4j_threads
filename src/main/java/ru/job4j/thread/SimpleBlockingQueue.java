package ru.job4j.thread;

import com.google.errorprone.annotations.concurrent.GuardedBy;

import javax.annotation.concurrent.ThreadSafe;
import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();

    private final Object monitor = this;

    private final int limit;

    public SimpleBlockingQueue(int limit) {
        this.limit = limit;
    }

    public void offer(T value) {
        synchronized (monitor) {
            while (queue.size() >= limit) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            queue.offer(value);
            notify();
        }
    }

    public T poll() {
        synchronized (monitor) {
            T result = null;
            try {
                while (queue.isEmpty()) {
                    wait();
                }
                result = queue.poll();
                notify();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return result;
        }
    }

    public synchronized int getSizeQeue() {
        return queue.size();
    }
}

