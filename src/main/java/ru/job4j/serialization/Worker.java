package ru.job4j.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public record Worker(String name, Contact contact, double salary, String[] tasks, boolean active) {
    @Override
    public String toString() {
        return "Worker{"
                + "name='" + name + '\''
                + ", contact=" + contact
                + ", salary=" + salary
                + ", tasks=" + Arrays.toString(tasks)
                + ", active=" + active
                + '}';
    }

    public static void main(String[] args) {
        Worker worker = new Worker("Johnny", new Contact(123456, "+7 (111) 111-11-11"),
                300000.0, new String[]{"Coding", "Testing"}, true);
        Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(worker));
        String stringJson =
                "{"
                        + "\"name\":\"Johnny\","
                        + "\"contact\":{\"zipCode\":123456,\"phone\":\"+7 (111) 111-11-11\"},"
                        + "\"salary\":300000.0,"
                        + "\"tasks\":[\"Coding\",\"Testing\"],"
                        + "\"active\":true"
                        + "}";
        Worker workerFromJson = gson.fromJson(stringJson, Worker.class);
        System.out.println(workerFromJson);
    }
}
