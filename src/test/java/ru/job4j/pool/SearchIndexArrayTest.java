package ru.job4j.pool;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

public class SearchIndexArrayTest {
    @Test
    public void indexToArray() {
        Integer[] data = new Integer[]{45, 89, 63, 47, 15, 2, 0, 98, 36, 115, 6, 96};
        Integer ints = SearchIndexArray.init(data, 0, data.length, 115);
        Assert.assertThat(9, Is.is(ints));
    }

    @Test
    public void indexToArrayOne() {
        Integer[] data = new Integer[]{45};
        Integer ints = SearchIndexArray.init(data, 0, data.length, 45);
        Assert.assertThat(0, Is.is(ints));
    }

    @Test
    public void indexNotToArray() {
        Integer[] data = new Integer[]{45, 89, 63, 47, 15, 2, 0, 98, 36, 115, 6, 96};
        Integer ints = SearchIndexArray.init(data, 0, data.length, 7);
        Assert.assertThat(-1, Is.is(ints));
    }

    @Test
    public void indexStartToArray() {
        Integer[] data = new Integer[]{45, 89, 63, 47, 15, 2, 0, 98, 36, 115, 6, 96, 14, 78, 12, 10, 8, 48, 35, 5, 33, 41};
        Integer ints = SearchIndexArray.init(data, 0, data.length, 89);
        Assert.assertThat(1, Is.is(ints));
    }

    @Test
    public void indexEndToArray() {
        Integer[] data = new Integer[]{45, 89, 63, 47, 15, 2, 0, 98, 36, 115, 6, 96, 14, 78, 12, 10, 8, 48, 35, 5, 33, 41};
        Integer ints = SearchIndexArray.init(data, 0, data.length, 41);
        Assert.assertThat(21, Is.is(ints));
    }

    @Test
    public void indexToArrayMany() {
        Integer[] data = new Integer[100000000];
        for (int i = 0; i < 100000000; i++) {
            /*data[i] = i;*/
            data[i] = (int) (Math.random() * 100000000);
        }
         SearchIndexArray.init(data, 0, data.length, 101);
        System.out.println(101);
       /* Assert.assertThat(208, Is.is(ints));*/
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
               /* Assert.assertThat(99999, Is.is(datum));*/
            }
        }
        long finish = System.currentTimeMillis();
        SearchIndexArray.init(data, 0, data.length, 101);
        long end = System.currentTimeMillis();
        System.out.println(finish - start);
        System.out.println(end - finish);
    }
}