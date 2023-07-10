package org.cars.model;

import io.swagger.v3.oas.annotations.media.Schema;

public class Car {

    @Schema(description = "id номер в таблице в БД",
            example = "1")
    //accessMode = Schema.AccessMode.READ_ONLY
    private int id;

    @Schema(description = "Наименование бренда автомобиля",
            example = "Toyota")
    private String brand;

    @Schema(description = "Наименование моделиавтомобиля",
            example = "Camry")
    private String model;

    @Schema(description = "год выпуска автомобиля",
            example = "2023")
    private int year;

    @Schema(description = "стоимость автомобиля",
            example = "10000.50")
    private double price;

    public Car() {
    }

    public Car(int id, String brand, String model, int year, double price) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String toString() {
        return ("id:" + id + ", " +
                "Brand: " + brand + ", " +
                "Model: " + model + ", " +
                "Year of manufacture: " + year + ", " +
                "Price: " + price + ".");
    }
}