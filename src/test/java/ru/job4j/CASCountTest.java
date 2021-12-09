package ru.job4j;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CASCountTest {
    @Test
    public void whenUseCASCount() throws InterruptedException {
        CASCount count = new CASCount();
        Thread one = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("one");
                count.increment();
            }
        });
        Thread two = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("two");
                count.increment();
            }
        });
        one.start();
        two.start();
        one.join();
        two.join();
        assertThat(count.get(), is(10));
    }

    @Test
    public void whenUseCASCountHundredTimes() throws InterruptedException {
        CASCount count = new CASCount();
        Thread one = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                count.increment();
            }
        });
        Thread two = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                count.increment();
            }
        });
        Thread three = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                count.increment();
            }
        });
        one.start();
        two.start();
        three.start();
        one.join();
        two.join();
        three.join();
        assertThat(count.get(), is(300));
    }
}