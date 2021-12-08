package ru.job4j;

import net.jcip.annotations.ThreadSafe;
import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>(0);

    public void increment() {
        int newValue;
        int oldValue;
        do {
            oldValue = count.get();
            System.out.println(oldValue + " old");
            if (oldValue == -1) {
                throw new UnsupportedOperationException("Count is not impl.");
            }
            newValue = oldValue + 1;
            System.out.println(newValue + " new");
        } while (!count.compareAndSet(oldValue, newValue));
    }

    public Integer get() {
        int result;
        result = count.get();
        if (result == -1) {
            throw new UnsupportedOperationException("Count is not impl.");
        }
        return result;
    }
}


