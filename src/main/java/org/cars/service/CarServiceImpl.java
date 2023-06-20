package org.cars.service;
import org.cars.model.Car;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CarServiceImpl implements CarService{
    //сортировка по цене
    @Override
    public List<Car> sortPrice(List<Car> list) {

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
    public List<Car> sortBrand(List<Car>list){

        boolean isSorted = false;

        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < list.size() - 1; i++) {
                if (list.get(i).getBrand().toLowerCase().compareTo(list.get(i + 1).getBrand().toLowerCase())>0) {
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
    public double findMaxPrice(List<Car> list) {
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
    public double findMinPrice(List<Car> list) {
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
    public List<Car> findCarMaxPrice(List<Car> list) {
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
    public  List<Car> findCarMinPrice(List<Car> list) {
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
    public List<Car> createCarListByBrand(List<Car> list, String brand) {
        return list.stream()
                .filter(i -> i.getBrand().equalsIgnoreCase(brand))
                .collect(Collectors.toList());
    }

    //список по модели
    @Override
    public List<Car> createCarListByModel(List<Car> list, String model) {
        return list.stream()
                .filter(i -> i.getModel().equalsIgnoreCase(model))
                .collect(Collectors.toList());
    }


    //список по диапазону цен
    @Override
    public List<Car> createCarListInPriceDiapason(List<Car> list, double min, double max) {
        return list.stream()
                .filter(i -> i.getPrice() >= min && i.getPrice() <= max)
                .collect(Collectors.toList());
    }


    //создает новый экземпляр Car
    public Car createCar(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Brand:");
        String brand = scanner.nextLine();
        System.out.println("Model:");
        String model = scanner.nextLine();
        System.out.println("Year of production:");
        int year = scanner.nextInt();
        System.out.println("Price:");
        double price = scanner.nextDouble();
        return new Car(brand, model, year, price);
    }
}