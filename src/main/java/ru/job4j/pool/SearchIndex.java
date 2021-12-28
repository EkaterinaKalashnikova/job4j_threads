package ru.job4j.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class SearchIndex {

    public static <T> int linearSearch(T[] data, T searchElement, int start, int end) {
        for (int i = start; i < end; i++) {
            if (data[i].equals(searchElement)) {
                return i;
            }
        }
        return -1;
    }

    public static <T> int recursiveBinarySearch(T[] data, int start, int end, T searchElement) {
        int mid = (end - start) / 2;
        if (end - start <= 10) {
            return mid;
        }
        SearchIndexArray<T> leftSort = new SearchIndexArray<>(data, start, mid, searchElement);
        SearchIndexArray<T> rightSort = new SearchIndexArray<>(data, mid + 1, end, searchElement);
        leftSort.fork();
        rightSort.fork();
        return Math.max(leftSort.join(), rightSort.join());
    }

    public static <T> int init(T[] data, int start, int end, T searchElement) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new SearchIndexArray<>(data, start, end, searchElement));
    }

    public static class Sarch<T> extends RecursiveTask<Integer> {
        private final T[] data;
        private final int start;
        private final int end;
        private final T searchElement;

        public Sarch(T[] data, int start, int end, T searchElement) {
            this.data = data;
            this.start = start;
            this.end = end;
            this.searchElement = searchElement;
        }

        @Override
        protected Integer compute() {
            if (end - start < 10) {
                return linearSearch(data, searchElement, start, end);
            }
            return recursiveBinarySearch(data, start, end, searchElement);
        }
    }
}

