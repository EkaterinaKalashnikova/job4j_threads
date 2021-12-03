package ru.job4j.thread;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {

    @Test
    public void test() throws InterruptedException {
        SimpleBlockingQueue<Integer> block = new SimpleBlockingQueue<>(7);
        Thread producer = new Thread(() -> block.offer(7));
        Thread consumer = new Thread(block::poll);
        producer.start();
        producer.join();
        consumer.start();
        consumer.join();
        assertEquals(0, block.getSizeQeue());
    }

    @Test
    public void whenAllElementsGet() throws InterruptedException {
        SimpleBlockingQueue<Integer> buf = new SimpleBlockingQueue<>(5);
        List<Integer> result = new ArrayList<>();
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                buf.offer(i);
            }
        });
        producer.start();
        Thread.sleep(1000);
        assertEquals(5, buf.getSizeQeue());
        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                result.add(buf.poll());
            }
        });
        consumer.start();
        consumer.join();
        assertEquals(0, buf.getSizeQeue());
        assertEquals(10, result.size());
    }

    @Test
    public void poll() throws InterruptedException {
        SimpleBlockingQueue<Integer> buf = new SimpleBlockingQueue<>(10);
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Thread producer = new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    buf.offer(j);
                }
            });
            producer.start();
        }
        Thread.sleep(2000);
        assertEquals(10, buf.getSizeQeue());
        List<Thread> listConsumer = new ArrayList<>();
        for (int j = 0; j < 10; j++) {
            Thread consumer = new Thread(() -> {
                for (int i = 0; i < 10; i++) {
                    result.add(buf.poll());
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
        assertEquals(0, buf.getSizeQeue());
        assertEquals(100, result.size());
    }
}