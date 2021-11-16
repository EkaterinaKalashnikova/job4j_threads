package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public class ParseFile implements Parser {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    @Override
    public String content(Predicate<Character> filter) throws IOException {
        StringBuilder output = new StringBuilder();
        InputStream i = new FileInputStream(file);
        try (BufferedReader buf = new BufferedReader(new InputStreamReader(i))) {
            int data;
            while ((data = buf.read()) > 0) {
                if (filter.test((char) data)) {
                        output.append((char) data);
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return output.toString();
    }

    public String getContent(Predicate<Character> filter) throws IOException {
       return content(i -> true);
    }

    public String getContentWithoutUnicode(Predicate<Character> filter) throws IOException {
        return content(i -> i < 0x80);
    }
}


