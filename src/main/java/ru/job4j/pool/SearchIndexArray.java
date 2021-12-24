package ru.job4j.pool;

import java.util.concurrent.RecursiveTask;

public class SearchIndexArray<T> extends RecursiveTask<Integer> {
    private final T[] data;
    private final int start;
    private final int end;
    private final T searchElement;

    public SearchIndexArray(T[] data, int start, int end, T searchElement) {
        this.data = data;
        this.start = start;
        this.end = end;
        this.searchElement = searchElement;
    }

    public int linearSearch(T[] data, T searchElement) {
       /* System.out.println("Линейный поиск");
        System.out.println(Thread.currentThread().getName());*/
        int index = -1;
        for (int i = start; i < end; i++) {
            if (data[i].equals(searchElement)) {
                return i;
            }
        }
        return index;
    }

    public int recursiveBinarySearch(T[] data, int start, int end, T searchElement) {
       /* System.out.println("Рекурсивный поиск");
        System.out.println(Thread.currentThread().getName());*/
        int mid = start + (end - start) / 2;
        if (data[mid].equals(searchElement)) {
            return mid;
        }
        /*int searchStartArray = recursiveBinarySearch(data, start, mid - 1, searchElement);
        int searchEndArray = recursiveBinarySearch(data, mid + 1, end, searchElement);*/
        SearchIndexArray<T> leftSort = new SearchIndexArray<>(data, start, mid, searchElement);
        SearchIndexArray<T> rightSort = new SearchIndexArray<>(data, mid + 1, end, searchElement);
        leftSort.fork();
        rightSort.fork();
        int left = leftSort.join();
        int right = rightSort.join();
        return left == -1 ? right : left;
    }

    @Override
    protected Integer compute() {
        if (end - start < 10) {
            return linearSearch(data, searchElement);
        }
        return recursiveBinarySearch(data, start, end, searchElement);
    }
}



