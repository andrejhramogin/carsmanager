package org.cars.utils;

import org.cars.model.Car;

import java.util.List;

public class PrintListUtils {

    public static void printCarList(List<Car> carList) {
        if (carList.isEmpty()) {
            System.out.println("List is empty.");
        } else {
            carList.forEach(System.out::println);
        }
    }
}