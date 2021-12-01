package ru.job4j.thread;

import com.google.errorprone.annotations.concurrent.GuardedBy;

import javax.annotation.concurrent.ThreadSafe;
import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();

    public void offer(T value) {
    }

    public T poll() {
        return null;
    }
}

/** Каждой нити нужно передать объект:

 new SimpleBlockingQueue<Integer>()

 Этот объект будет общим ресурсом между этими нитями.

 Метод poll() должен вернуть объект из внутренней коллекции.
 Если в коллекции объектов нет, то нужно перевести
 текущую нить в состояние ожидания.

 Важный момент, когда нить переводить в состояние ожидания,
 то она отпускает объект монитор и другая нить тоже может выполнить этот метод.
 1. Реализовать методы poll() и offer().

 2. Написать тесты. В тестах должны быть две нити: одна производитель, другая потребитель.

 Через методы join() добиться последовательного выполнение программы.

 */