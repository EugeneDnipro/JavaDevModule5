package org.example;

import java.time.LocalDate;

public class YoungestEldestWorkersDto {
    private String type;
    private String name;
    private LocalDate birthday;

    public YoungestEldestWorkersDto(String type, String name, LocalDate birthday) {
        this.type = type;
        this.name = name;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return '\n' + "YoungestEldestWorkersDto{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
