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
/**              String[] process = new String[3];
                 process[0] = "\";
                 process[1] = "|";
                 process[2] = "/";
                 System.out.print("\r load: " + process[...]);*/
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}



     /**   4. В тело цикла добавьте вывод в консоль.

        Loading ... |.

        Последний символ должен меняться: - \ | /.
System.out.print("\r load: " + process[...]);
Эти символы создадут эффект крутящегося шара. */

