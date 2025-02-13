package ru.job4j.map;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        Map<User, Object> map = new HashMap<>(16);
        Calendar calendar = Calendar.getInstance();
        User user1 = new User("User", 5, calendar);
        int hashCode1 = user1.hashCode();
        int hash1 = hashCode1 ^ (hashCode1 >>> 16);
        int bucket1 = hash1 & 15;
        User user2 = new User("User", 5, calendar);
        int hashCode2 = user2.hashCode();
        int hash2 = hashCode2 ^ (hashCode2 >>> 16);
        int bucket2 = hash2 & 15;
        map.put(user1, new Object());
        map.put(user2, new Object());
        System.out.printf("User%1$d: {hashcode%1$d = %2$d} {hash%1$d = %3$d} {bucket%1$d = %4$d}%n",
                1, hashCode1, hash1, bucket1);
        System.out.printf("User%1$d: {hashcode%1$d = %2$d} {hash%1$d = %3$d} {bucket%1$d = %4$d}",
                2, hashCode2, hash2, bucket2);
    }
}
