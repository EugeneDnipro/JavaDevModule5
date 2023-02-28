package org.example;

public class LongestProjectDto {
    private String name;
    private int month_count;

    public LongestProjectDto(String name, int month_count) {
        this.name = name;
        this.month_count = month_count;
    }

    @Override
    public String toString() {
        return '\n' + "LongestProjectDto{" +
                "name='" + name + '\'' +
                ", month_count=" + month_count +
                '}';
    }
}
