package org.cars.utils;

import org.cars.model.Car;

import java.util.List;

/**
 * Метод void printCarList(List<Car> list) - выводит на консоль List<Car>
 */
public class PrintListUtils {

    public static void printCarList(List<Car> list) {
        try {
            if (list.isEmpty()) {
                System.out.println("List is empty.");
            } else {
                list.forEach(System.out::println);
            }
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }

    }
}