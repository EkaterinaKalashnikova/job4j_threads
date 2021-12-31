package ru.job4j.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class SearchIndex<T> extends RecursiveTask<Integer> {
    private final T[] data;
    private final int start;
    private final int end;
    private final T searchElement;

    public SearchIndex(T[] data, int start, int end, T searchElement) {
        this.data = data;
        this.start = start;
        this.end = end;
        this.searchElement = searchElement;
    }

    public <T> int linearSearch(T[] data, T searchElement, int start, int end) {
        for (int i = start; i <= end; i++) {
            if (data[i].equals(searchElement)) {
                return i;
            }
        }
        return -1;
    }

    public <T> int recursiveBinarySearch(T[] data, int start, int end, T searchElement) {
        int mid = (end - start) / 2;
        SearchIndexArray<T> leftSearch = new SearchIndexArray<>(data, start, mid, searchElement);
        SearchIndexArray<T> rightSearch = new SearchIndexArray<>(data, mid + 1, end, searchElement);
        leftSearch.fork();
        rightSearch.fork();
        return Math.max(leftSearch.join(), rightSearch.join());
    }

    @Override
    protected Integer compute() {
        if (end - start <= 10) {
            return linearSearch(data, searchElement, start, end);
        }
        return recursiveBinarySearch(data, start, end, searchElement);
    }

    public static <T> int init(T[] data, T searchElement) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new SearchIndex<>(data, 0, data.length, searchElement));
    }
}

