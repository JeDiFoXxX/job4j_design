package ru.job4j.serialization;

import org.json.JSONObject;

public class JsonFormat {
    public static void main(String[] args) {
        var worker = new Worker("Johnny", new Contact(123456, "+7 (111) 111-11-11"),
                300000.0, new String[]{"Coding", "Testing"}, true);
        System.out.println(new JSONObject(worker).toString(2));
    }
}
