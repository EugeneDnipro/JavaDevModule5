package org.example;

public class WorkerDataDto {
    String name;
    String birthday;
    String level;
    long salary;

    public WorkerDataDto(String name, String birthday, String level, long salary) {
        this.name = name;
        this.birthday = birthday;
        this.level = level;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getLevel() {
        return level;
    }

    public long getSalary() {
        return salary;
    }
}
