package ru.job4j.cache;

import org.junit.Assert;
import org.junit.Test;

public class CacheTest {

    @Test
    public void whenAddValue() {
        Cache cache = new Cache();
        Base model = new Base(1, 1);
        boolean b = cache.add(model);
        Assert.assertTrue(b);
    }

    @Test
    public void whenUpdateValueNotChange() {
        Cache cache = new Cache();
        Base model = new Base(1, 1);
        cache.add(model);
        Assert.assertTrue(cache.update(model));
    }

    @Test
    public void whenUpdateValueChange() {
        Cache cache = new Cache();
        Base model = new Base(1, 1);
        Base modelNext = new Base(2, 1);
        cache.add(model);
        Assert.assertFalse(cache.update(modelNext));
    }

    @Test(expected = OptimisticException.class)
    public void whenUpdateValueWhereChangeVersion() {
        Cache cache = new Cache();
        Base model = new Base(1, 1);
        Base modelNext = new Base(1, 2);
        cache.add(model);
        cache.add(modelNext);
        cache.update(modelNext);
    }

    @Test
    public void whenDeleteValue() {
        Cache cache = new Cache();
        Base model = new Base(1, 1);
        cache.add(model);
        cache.delete(model);
        Assert.assertTrue(cache.add(model));
    }
}