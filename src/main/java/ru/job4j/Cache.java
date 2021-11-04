package ru.job4j;

import java.util.concurrent.atomic.AtomicReference;

public final class Cache {
    private static AtomicReference<Cache> cache;

    public static synchronized AtomicReference<Cache> instOf() {
        if (cache == null) {
            cache = new AtomicReference<Cache>();
        }
        return cache;
    }
}
