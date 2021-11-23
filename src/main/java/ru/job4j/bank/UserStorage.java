package ru.job4j.bank;

import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStorage {
    private final Map<Integer, User> users = new HashMap();

    public boolean add(User user) {
        return users.putIfAbsent(user.getId(), user) != user;
    }

    public synchronized boolean update(User user) {
        return users.replace(user.getId(), users.get(user.getId())) != user;
    }

    public synchronized boolean delete(User user) {
        return users.remove(user.getId(), user);
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        User userFrom = users.get(fromId);
        User userTo = users.get(toId);
        if (userFrom == null || userFrom.getAmount() <= amount) {
                return false;
        }
        userFrom.setAmount(userFrom.getAmount() - amount);
        userTo.setAmount(userTo.getAmount() + amount);
        return true;
    }
}




