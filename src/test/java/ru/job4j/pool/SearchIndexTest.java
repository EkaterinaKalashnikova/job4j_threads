package ru.job4j.pool;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

public class SearchIndexTest {
    @Test
    public void indexToArray() {
        Integer[] data = new Integer[]{45, 89, 63, 47, 15, 2, 0, 98, 36, 115, 6, 96};
        Integer ints = SearchIndex.init(data, 115);
        Assert.assertThat(9, Is.is(ints));
    }

    @Test
    public void indexToArrayOne() {
        Integer[] data = new Integer[]{45};
        Integer ints = SearchIndex.init(data, 45);
        Assert.assertThat(0, Is.is(ints));
    }

    @Test
    public void indexNotToArray() {
        Integer[] data = new Integer[]{45, 89, 63, 47, 15, 2, 0, 98, 36, 115, 6, 96};
        Integer ints = SearchIndex.init(data, 7);
        Assert.assertThat(-1, Is.is(ints));
    }

    @Test
    public void indexStartToArray() {
        Integer[] data = new Integer[]{45, 89, 63, 47, 15, 2, 0, 98, 36, 115, 6, 96, 14, 78, 12, 10, 8, 48, 35, 5, 33, 41};
        Integer ints = SearchIndex.init(data, 89);
        Assert.assertThat(1, Is.is(ints));
    }

    @Test
    public void indexEndToArray() {
        Integer[] data = new Integer[]{45, 89, 63, 47, 15, 2, 0, 98, 36, 115, 6, 96, 14, 78, 12, 10, 8, 48, 35, 5, 33, 41};
        Integer ints = SearchIndex.init(data, 41);
        Assert.assertThat(21, Is.is(ints));
    }

    @Test
    public void indexToArrayMany() {
        Integer[] data = new Integer[100000000];
        for (int i = 0; i < 100000000; i++) {
            data[i] = (int) (Math.random() * 100000000);
        }
        SearchIndex.init(data, 101);
        System.out.println(101);
    }

    @Test
    public void indexToArrayManyLine() {
        Integer[] data = new Integer[100000000];
        for (int i = 0; i < 100000000; i++) {
            data[i] = (int) (Math.random() * 100000000);
        }
        int j = data[9999999];
        long start = System.currentTimeMillis();
        for (Integer datum : data) {
            if (datum.equals(j)) {
                System.out.println(j);
                break;
            }
        }
        long finish = System.currentTimeMillis();
        SearchIndex.init(data, 101);
        long end = System.currentTimeMillis();
        System.out.println(finish - start);
        System.out.println(end - finish);
    }
}