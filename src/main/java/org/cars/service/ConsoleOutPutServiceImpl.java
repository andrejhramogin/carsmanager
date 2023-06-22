package org.cars.service;

import org.cars.model.Car;

import java.util.List;

/**
 * Класс содержит:
 * Метод void printList(List<Car> list) - выводит на консоль List<Car>
 */
public class ConsoleOutPutServiceImpl implements OutPutService {

    @Override
    public void printList(List<Car> list) {
        try {
            if (list.isEmpty()) {
                System.out.println("List is empty.");
            } else {
                list.forEach(System.out::println);
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }
}
