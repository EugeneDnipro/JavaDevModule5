package org.example;

public class ProjectPriceDto {
    private String name;
    private long price;

    public ProjectPriceDto(String name, long price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return '\n' + "ProjectPriceDto{" +
                " name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
