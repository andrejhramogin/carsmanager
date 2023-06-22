package org.cars.service;

import org.cars.model.Car;

import java.util.List;

/**
 * Интерфейс определяет метод для вывода данных на сконсоль
 */
public interface OutPutService {
    void printList(List<Car> list);
}
