package org.example.service;

import org.example.model.Car;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CarListCreate {

    public static List<Car> createCarList() {
        return new ArrayList<>(Arrays.asList(
                new Car("Toyota", "Camry", 2010, 10000.0),
                new Car("Toyota", "Camry", 2012, 12000.5),
                new Car("Toyota", "Camry", 2014, 14000.6),
                new Car("Toyota", "Solara", 2003, 6000.8),
                new Car("Toyota", "Solara", 2013, 16000.0),
                new Car("BMW", "320", 2003, 3000.8),
                new Car("BMW", "520", 2013, 3000.8),
                new Car("BMW", "720", 2015, 16000.0),
                new Car("Opel", "Astra", 2003, 4000.8),
                new Car("Opel", "Corsa", 2020, 13000.8),
                new Car("Opel", "Zafira", 2019, 11000.8)));
    }
}
