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
        String output = "";
        InputStream i = new FileInputStream(file);
        try (BufferedReader buf = new BufferedReader(new InputStreamReader(i))) {
            int data;
            Predicate<Character> filters = x -> x < 0x80;
            while ((data = buf.read()) > 0) {
                if (filters.test((char) data)) {
                    output += (char) data;
                }
                output += (char) data;
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return output;
    }
}


