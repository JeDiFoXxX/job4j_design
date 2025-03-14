package ru.job4j.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "worker")
@XmlAccessorType(XmlAccessType.FIELD)
public final class Worker {
    @XmlAttribute
    private String name;
    private Contact contact;
    @XmlAttribute
    private double salary;
    @XmlElementWrapper(name = "tasks")
    @XmlElement(name = "task")
    private String[] tasks;
    @XmlAttribute
    private boolean active;

    public Worker() { }

    public Worker(String name, Contact contact, double salary, String[] tasks, boolean active) {
        this.name = name;
        this.contact = contact;
        this.salary = salary;
        this.tasks = tasks;
        this.active = active;
    }

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
