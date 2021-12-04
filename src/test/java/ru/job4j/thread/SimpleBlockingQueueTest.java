package ru.job4j.thread;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {

    @Test
    public void test() throws InterruptedException {
        SimpleBlockingQueue<Integer> block = new SimpleBlockingQueue<>(7);
        Thread producer = new Thread(() -> {
            try {
                block.offer(7);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        Thread consumer = new Thread(() -> {
            try {
                block.poll();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        producer.start();
        producer.join();
        consumer.start();
        consumer.join();
        assertEquals(0, block.getSizeQueue());
    }

    @Test
    public void whenAllElementsGet() throws InterruptedException {
        SimpleBlockingQueue<Integer> buf = new SimpleBlockingQueue<>(5);
        List<Integer> result = new ArrayList<>();
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    buf.offer(i);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        producer.start();
        Thread.sleep(1000);
        assertEquals(5, buf.getSizeQueue());
        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    result.add(buf.poll());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        consumer.start();
        consumer.join();
        assertEquals(0, buf.getSizeQueue());
        assertEquals(10, result.size());
    }

    @Test
    public void poll() throws InterruptedException {
        SimpleBlockingQueue<Integer> buf = new SimpleBlockingQueue<>(10);
        ConcurrentLinkedQueue<Integer> result = new ConcurrentLinkedQueue<>();
        for (int i = 0; i < 10; i++) {
            Thread producer = new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    try {
                        buf.offer(j);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
            producer.start();
        }
        Thread.sleep(2000);
        assertEquals(10, buf.getSizeQueue());
        List<Thread> listConsumer = new ArrayList<>();
        for (int j = 0; j < 10; j++) {
            Thread consumer = new Thread(() -> {
                for (int i = 0; i < 10; i++) {
                    try {
                        result.add(buf.poll());
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
            listConsumer.add(consumer);
            consumer.start();
        }
        listConsumer.forEach((c) -> {
            try {
                c.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        assertEquals(0, buf.getSizeQueue());
        assertEquals(100, result.size());
    }
}