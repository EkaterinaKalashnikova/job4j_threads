package ru.job4j.ref;

import org.junit.Test;
import java.util.List;

public class UserCacheTest {
    @Test
    public void whenGetFindAll() {
        UserCache userCache = new UserCache();
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        userCache.add(user1);
        userCache.add(user2);
        userCache.add(user3);
        List<User> userCacheAll = userCache.findAll();
        System.out.println(userCacheAll);
    }
}