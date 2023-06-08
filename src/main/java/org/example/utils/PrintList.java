package org.example.utils;

import org.example.model.Car;

import java.util.List;

public class PrintList {


    public static void printCarList(List<Car> carList) {
        if (carList.isEmpty()) {
            System.out.println("List is empty");
        } else {
            carList.forEach(System.out::println);
        }
    }
}

