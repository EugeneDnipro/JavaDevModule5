package org.example;

public class WorkerDataDto {
    int id;
    String name;
    String birthday;
    String level;
    long salary;

    public WorkerDataDto(int id, String name, String birthday, String level, long salary) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.level = level;
        this.salary = salary;
    }
}
