package ru.job4j.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {
    private final ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    public void emailTo(User user) {
        pool.submit(() -> {
            String subject = String.format("subject = Notification {username} to email {email}",
                    user.getUsername(), user.getEmail());
            String body = String.format("Add a new event to {username}", user.getUsername());
            String email = String.format("to the new address", user.getEmail());
            send(subject, body, email);
        });
    }

    public void close() {
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        pool.shutdown();
    }

    public void send(String subject, String body, String email) {

    }

    public static void main(String[] args) {
        EmailNotification emailNotification = new EmailNotification();
        User user = new User();
        user.setEmail("123@bk.ru");
        user.setUsername("Sam");
        emailNotification.emailTo(user);
        System.out.println(user.getUsername());
    }
}
