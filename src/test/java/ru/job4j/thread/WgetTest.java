package ru.job4j.thread;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class WgetTest {

    @Test
    public void whenCreatedRun() throws InterruptedException {
        String[] urls = {
                "https://animalsglobe.ru/wp-content/uploads/2011/11/siamskaya-koshka.jpg",
                "https://krasivosti.pro/uploads/posts/2021-04/thumbs/1617692794_7-p-koshachi-oboi-kotiki-siamskie-7.jpg"
        };
        List<Wget> uploads = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();
        for (String url : urls) {
            Wget wget = new Wget(url, 1);
            uploads.add(wget);
            Thread thread = new Thread(wget);
            threads.add(thread);
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
            System.out.println("Все закачки финишировали.");
        }
        for (Wget wget : uploads) {
            System.out.println(wget.getSpeed() * 100000000 / wget.getSpeed());
        }
    }
}