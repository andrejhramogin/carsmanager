package org.cars.service;

import org.cars.model.Car;

import java.util.List;

public interface CarService {

    List<Car> sortPrice(List<Car> list);
    List<Car> sortBrand(List<Car>list);
    double findMaxPrice(List<Car> list);
    double findMinPrice(List<Car> list);
    List<Car> findCarMaxPrice(List<Car> list);
    List<Car> findCarMinPrice(List<Car> list);
    List<Car> createCarListByBrand(List<Car> list, String brand);
    public List<Car> createCarListByModel(List<Car> list, String model);
    List<Car> createCarListInPriceDiapason(List<Car> list, double min, double max);
}
