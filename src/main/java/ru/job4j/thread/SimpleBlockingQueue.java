package ru.job4j.thread;

import net.jcip.annotations.GuardedBy;

import javax.annotation.concurrent.ThreadSafe;
import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();

    private final Object monitor = this;

    private final int limit;

    public SimpleBlockingQueue(int limit)  {
        this.limit = limit;
    }

    public void offer(T value) throws InterruptedException {
        synchronized (monitor) {
            while (queue.size() == limit) {
                    wait();
            }
            queue.offer(value);
            notify();
        }
    }

    public T poll() throws InterruptedException {
        synchronized (monitor) {
            T result;
                while (queue.isEmpty()) {
                    wait();
                }
                result = queue.poll();
                notify();
            return result;
        }
    }

    public synchronized int getSizeQueue() {
        return queue.size();
    }
}

