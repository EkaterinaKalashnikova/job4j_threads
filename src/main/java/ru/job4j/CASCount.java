package ru.job4j;

import net.jcip.annotations.ThreadSafe;
import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>(0);

    public void increment() {
        Integer value;
        do {
            value = count.get();
            System.out.println(value);
        } while (!count.compareAndSet(value, value + 1));
    }

    public Integer get() {
        return count.get();
    }
}


