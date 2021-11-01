package ru.job4j.thread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Wget implements Runnable {
    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        System.out.println("Скачивание началось....");
        long start = System.currentTimeMillis();
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream())) {
            try (FileOutputStream fileOutputStream = new FileOutputStream("tmp3.jpg")) {
                byte[] dataBuffer = new byte[speed * 1024];
                int bytesRead;
                long bytesWrited = 0;
                while ((bytesRead = in.read(dataBuffer, 0, speed * 1024)) != -1) {
                    fileOutputStream.write(dataBuffer, 0, bytesRead);
                    bytesWrited = bytesWrited + bytesRead;
                    if (bytesWrited == speed) {
                        long diffTime = System.currentTimeMillis();
                        if (diffTime < 1000) {
                            Thread.sleep(1000 - diffTime);
                        }
                    }
                    System.out.println(bytesRead);
                    bytesWrited = 0;
                }
            }
        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        long end = System.currentTimeMillis();
        System.out.println("Загрузка завершена: " + ((end - start) / 1000));
    }

    public static void main(String[] args) throws InterruptedException {
        valid(args);
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }

    private static void valid(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException();
        }
    }
}


