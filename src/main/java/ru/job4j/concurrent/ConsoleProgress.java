package ru.job4j.concurrent;

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
        String[] process = new String[3];
        process[0] = "\\";
        process[1] = "|";
        process[2] = "/";
        while (!Thread.currentThread().isInterrupted()) {
            for (int i = 0; i < process.length; i++) {
                System.out.print("\r load: " + process[i]);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(" Loading ... |");
            }
        }
    }
}



