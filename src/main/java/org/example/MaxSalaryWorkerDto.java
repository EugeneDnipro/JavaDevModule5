package org.example;

public class MaxSalaryWorkerDto {
    private String name;
    private long salary;

    public MaxSalaryWorkerDto(String name, long salary) {
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return '\n' + "MaxSalaryWorkerDto{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}
