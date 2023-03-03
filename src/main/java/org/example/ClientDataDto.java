package org.example;

public class ClientDataDto {
    int id;
    String name;

    public ClientDataDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
