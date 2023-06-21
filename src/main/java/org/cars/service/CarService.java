package org.cars.service;

import org.cars.model.Car;

import java.util.List;

public interface CarService {

    List<Car> sortByPrice();

    List<Car> sortByBrand();

    double findMaxPrice();

    double findMinPrice();

    List<Car> findCarWithMaxPrice();

    List<Car> findCarWithMinPrice();

    List<Car> findCarByBrand(String brand);

    List<Car> findCarByModel(String model);

    List<Car> findCarInPriceDiapason(double min, double max);

    void printList();
}
