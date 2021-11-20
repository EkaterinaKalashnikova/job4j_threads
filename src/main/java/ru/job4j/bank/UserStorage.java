package ru.job4j.bank;

import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStorage {
    private final Map<Integer, User> users = new HashMap();

    public boolean add(User user) {
        User user1 = users.putIfAbsent(user.getId(), user);
        return user1 != user;
    }

    public synchronized boolean update(User user) {
        User user1 = users.get(user.getId());
        if (user1 != null) {
            if (users.containsKey(user.getId())) {
                return false;
            }
        }
        user.setAmount(user.getAmount());
        return true;
    }

    public synchronized boolean delete(User user) {
        users.remove(user.getId());
        return true;
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        User userFrom = users.get(fromId);
        User userTo = users.get(toId);
        if (userFrom == null || userTo == null) {
            assert userFrom != null;
            assert false;
            if (userFrom.getAmount() <= amount || userTo.getAmount() <= amount) {
                return false;
            }
        }
        userFrom.setAmount(userFrom.getAmount() - amount);
        userTo.setAmount(userTo.getAmount() + amount);
        return true;
    }
}




