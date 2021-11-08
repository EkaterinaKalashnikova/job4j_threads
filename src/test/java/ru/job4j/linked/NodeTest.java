package ru.job4j.linked;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.is;

public class NodeTest {

    @Test
    public <T> void whenNodeTest() {
        Node<T> tNode = new Node<>(null, null);
        Node<T> nodeNext = tNode.getNext();
        T nodeValue = tNode.getValue();
        Assert.assertNull(nodeNext);
        Assert.assertNull(nodeValue);
        String nodeNext1 = "second";
        String nodeValue1 = "first";
        Assert.assertThat("second", is(nodeNext1));
        Assert.assertThat("first", is(nodeValue1));
    }
}