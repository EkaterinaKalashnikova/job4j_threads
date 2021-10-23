package ru.job4j.concurrent;

import java.util.Arrays;

public class ConsoleProgress implements Runnable {
    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(1000); /* симулируем выполнение
                параллельной задачи в течение 1 секунды. */
        progress.interrupt();
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                System.out.println(" Loading ... |");
                Thread.sleep(500);
                String[] process = new String[3];
                process[0] = "\\";
                process[1] = "|";
                process[2] = "/";
              /**  for (int i = 0; i < process.length; i++) {
                    System.out.print("\r load: " + process[i]);
                    if (i == 2) {
                        for (int j = process.length - 1; j >= 0; j--) {
                            System.out.print(process[j] + " ");
                        }
                    }
                }*/
                System.out.println("\r load: " + process[2] + process[1] + process[0]);
                System.out.println("\r load: " + process[0] + process[1] + process[2]);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
}




