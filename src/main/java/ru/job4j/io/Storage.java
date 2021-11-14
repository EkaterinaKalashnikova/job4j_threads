package ru.job4j.io;

import java.io.*;

public class Storage {
    private final File file;

    public Storage(File file) {
        this.file = file;
    }

    public void saveContent(String content) throws IOException {
        try (OutputStream o = new FileOutputStream(file);
             BufferedOutputStream bos = new BufferedOutputStream(o)) {
            String text = " ";
            byte[] buffer = text.getBytes();
            bos.write(buffer, 0, buffer.length);
            for (int i = 0; i < content.length(); i += 1) {
                o.write(content.charAt(i));
                o.close();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
