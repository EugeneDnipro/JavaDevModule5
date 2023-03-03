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

    public int getId() {
        return id;
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

    @Override
    public String toString() {
        return "WorkerDataDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday='" + birthday + '\'' +
                ", level='" + level + '\'' +
                ", salary=" + salary +
                '}';
    }
}
