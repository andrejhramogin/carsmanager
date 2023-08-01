package org.cars.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @Column(name = "id", insertable = false)
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Schema(description = "id номер в таблице в БД",
            example = "1")
    private Integer id;

    @Column(name  = "brand", nullable = false)
    @Schema(description = "Наименование бренда автомобиля",
            example = "Toyota")
    private String brand;

    @Column(name  = "model", nullable = false)
    @Schema(description = "Наименование моделиавтомобиля",
            example = "Camry")
    private String model;

    @Column(name  = "year", nullable = false)
    @Schema(description = "год выпуска автомобиля",
            example = "2023")
    private Integer year;

    @Column(name  = "price", nullable = false)
    @Schema(description = "стоимость автомобиля",
            example = "10000.50")
    private Double price;

    public Car(){}//?

    public Car( String brand, String model, Integer year, Double price) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id){
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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
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
