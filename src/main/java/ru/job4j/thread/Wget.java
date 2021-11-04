package ru.job4j.thread;

import java.io.*;
import java.net.URL;
import java.util.Scanner;

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
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите адресную строку: ");
        String s1 = null;
        try {
            s1 = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert s1 != null;
        try (BufferedInputStream in = new BufferedInputStream(new URL(s1).openStream())) {
            try (FileOutputStream fileOutputStream = new FileOutputStream("tmp4.jpg")) {
                byte[] dataBuffer = new byte[1024];
                int bytesRead;
                long bytesWrited = 0;
                long time = System.currentTimeMillis();
                while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                    fileOutputStream.write(dataBuffer, 0, bytesRead);
                    bytesWrited = bytesWrited + bytesRead;
                    if (bytesWrited > speed) {
                        long diffTime = System.currentTimeMillis() - time;
                        if (diffTime < 1000) {
                            Thread.sleep(1000 - diffTime);
                        }
                    }
                    bytesWrited = 0;
                    System.out.println(bytesRead);
                    time = System.currentTimeMillis();
                }
            }
        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        long end = System.currentTimeMillis();
        System.out.println("Загрузка завершена: " + ((end - start) / 1000f));
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

