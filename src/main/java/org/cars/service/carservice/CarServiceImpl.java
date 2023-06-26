package org.cars.service.carservice;

import org.cars.model.Car;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс имплементирет интерфейс CarService и переопределяет его методы.
 */

public class CarServiceImpl implements CarService {

    private final List<Car> list;

    public CarServiceImpl(List<Car> list) {
        this.list = list;
    }

    //сортировка по цене
    @Override
    public List<Car> sortByPrice() {

        boolean isSorted = false;

        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < list.size() - 1; i++) {
                if (list.get(i).getPrice() > list.get(i + 1).getPrice()) {
                    Car buf;
                    buf = list.get(i);
                    list.set(i, list.get(i + 1));
                    list.set(i + 1, buf);
                    isSorted = false;
                }
            }
        }
        return list;
    }

    //сортировка по бренду
    @Override
    public List<Car> sortByBrand() {

        boolean isSorted = false;

        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < list.size() - 1; i++) {
                if (list.get(i).getBrand().toLowerCase().compareTo(list.get(i + 1).getBrand().toLowerCase()) > 0) {
                    Car buf;
                    buf = list.get(i);
                    list.set(i, list.get(i + 1));
                    list.set(i + 1, buf);
                    isSorted = false;
                }
            }
        }
        return list;
    }

    //максимальная цена
    @Override
    public double findMaxPrice() {
        double max = 0;
        for (Car car : list) {
            if (car.getPrice() > max) {
                max = car.getPrice();
            }
        }
        return max;
    }

    //минимальная цена
    @Override
    public double findMinPrice() {
        double min = list.get(0).getPrice();
        for (Car car : list) {
            if (car.getPrice() < min) {
                min = car.getPrice();
            }
        }
        return min;
    }

    //авто с самой большой ценой
    @Override
    public List<Car> findCarWithMaxPrice() {
        double max = 0;
        for (Car car : list) {
            if (car.getPrice() > max) {
                max = car.getPrice();
            }
        }
        double finalMax = max;
        return list.stream()
                .filter(i -> i.getPrice() == finalMax)
                .collect(Collectors.toList());
    }

    //авто с минимальной ценой
    @Override
    public List<Car> findCarWithMinPrice() {
        double min = list.get(0).getPrice();
        for (Car car : list) {
            if (car.getPrice() < min) {
                min = car.getPrice();
            }
        }
        double finalMin = min;
        return list.stream()
                .filter(i -> i.getPrice() == finalMin)
                .collect(Collectors.toList());
    }

    //список по бренду
    @Override
    public List<Car> findCarByBrand(String brand) {
        return list.stream()
                .filter(i -> i.getBrand().equalsIgnoreCase(brand))
                .collect(Collectors.toList());
    }

    //список по модели
    @Override
    public List<Car> findCarByModel(String model) {
        return list.stream()
                .filter(i -> i.getModel().equalsIgnoreCase(model))
                .collect(Collectors.toList());
    }


    //список по диапазону цен
    @Override
    public List<Car> findCarInPriceDiapason(double min, double max) {
        return list.stream()
                .filter(i -> i.getPrice() >= min && i.getPrice() <= max)
                .collect(Collectors.toList());
    }
}
