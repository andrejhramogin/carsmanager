package org.cars.service.carservice;

import org.cars.entity.Car;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Интерфейс определяет методы для работы с данными автомобилей из БД.
 */

public interface CarService {

    void deleteCarById(int num) throws SQLException;

    org.cars.entity.Car createNewCar(org.cars.entity.Car car) throws SQLException;

    Optional<Car> findCarById(int id) throws SQLException;

    Car update(Car car, int id) throws SQLException;

    List<Car> getByParam(String sortBy, String sortDirection, String filter) throws SQLException;
}
