package ru.job4j.thread;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class WgetTest {

    @Test
    public void whenCreatedRun() throws InterruptedException {
        String[] urls = {
                "https://esmio.ru/odezhda-dlya-tancev/muzhskaya-balnaya/rubashki/",
                "https://esmio.ru/odezhda-dlya-tancev/zhenskaya-balnaya/platya/",
                "https://esmio.ru/odezhda-dlya-tancev/muzhskaya-balnaya/fraki/"
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