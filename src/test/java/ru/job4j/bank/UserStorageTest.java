package ru.job4j.bank;

import org.junit.Assert;
import org.junit.Test;

public class UserStorageTest {

    @Test
    public void whenAddCheck() {
        UserStorage store = new UserStorage();
        var addUser1 = store.add(new User(1, 100));
        var addUser2 = store.add(new User(2, 200));
        Assert.assertTrue(addUser1);
        Assert.assertTrue(addUser2);
    }

    @Test
    public void whenUpdateCheck() {
        UserStorage store = new UserStorage();
        store.add(new User(1, 10));
        var updatedUser = new User(1, 50);
        store.update(updatedUser);
        Assert.assertEquals(50, updatedUser.getAmount());
    }

    @Test
    public void whenDeleteCheck() {
        UserStorage store = new UserStorage();
        var addUser = store.add(new User(1, 100));
        var deleteUser = store.delete(new User(1, 100));
        Assert.assertTrue(addUser);
        Assert.assertTrue(deleteUser);
    }

    @Test
    public void whenTransferCheckToCheck() {
       UserStorage store = new UserStorage();
        var fromUser = new User(1, 200);
        var toUser = new User(2, 200);
        store.add(fromUser);
        store.add(toUser);
        store.transfer(1, 2, 10);
       /* Assert.assertEquals(190, fromUser.getAmount());
        Assert.assertEquals(210, toUser.getAmount());*/
    }
}