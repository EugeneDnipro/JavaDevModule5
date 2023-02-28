package org.example;

public class MaxProjectCountClientDto {
    private String name;
    private int projectCount;

    public MaxProjectCountClientDto(String name, int projectCount) {
        this.name = name;
        this.projectCount = projectCount;
    }

    @Override
    public String toString() {
        return '\n' + "MaxProjectCountClientDto{" +
                "name='" + name + '\'' +
                ", projectCount=" + projectCount +
                '}';
    }
}
