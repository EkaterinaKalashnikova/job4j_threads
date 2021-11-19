package ru.job4j.bank;

import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStorage {
    private static Map<Integer, User> users = new HashMap();

    public boolean add(User user) {
        if (user != null) {
            User user1 = users.put(user.getId(), user);
            return true;
        }
        return false;
    }

    public synchronized boolean update(User user) {
        User user1 = users.get(user.getId());
        if (user1 != null) {
            user1.setAmount(user.getAmount());
        }
        return false;
    }

    public synchronized boolean delete(User user) {
        if (user != null) {
            User user1 = users.remove(user.getId());
            return true;
        }
        return false;
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        User userFrom = users.get(fromId);
        User userTo = users.get(toId);
        if (fromId <= amount) {
            return false;
        }
        userFrom.setAmount(userFrom.getAmount() - amount);
        userTo.setAmount(userTo.getAmount() + amount);
        return true;
    }
}




