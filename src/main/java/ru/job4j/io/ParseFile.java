package ru.job4j.io;

import java.io.*;

public class ParseFile implements Parser {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public String getContent() throws FileNotFoundException {
        String output = "";
        InputStream i = new FileInputStream(file);
        try (BufferedReader buf = new BufferedReader(new InputStreamReader(i))) {
            int data;
            while ((data = buf.read()) > 0) {
                output += (char) data;
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return output;
    }

    public String getContentWithoutUnicode() throws FileNotFoundException {
        String output = "";
        InputStream i = new FileInputStream(file);
        try (BufferedReader buf = new BufferedReader(new InputStreamReader(i))) {
            int data;
            while ((data = buf.read()) > 0) {
                if (data < 0x80) {
                    output += (char) data;
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return output;
    }
}




/**        - Нарушен принцип единой ответственности. Тут нужно сделать два класса.

       - Методы getContent написаны в стиле копипаста. Нужно применить шаблон стратегия.
        content(Predicate<Character> filter)*/