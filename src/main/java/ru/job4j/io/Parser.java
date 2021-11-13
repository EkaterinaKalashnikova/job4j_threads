package ru.job4j.io;

import java.io.FileNotFoundException;

public interface Parser {

     String getContent() throws FileNotFoundException;

     String getContentWithoutUnicode() throws FileNotFoundException;
}
