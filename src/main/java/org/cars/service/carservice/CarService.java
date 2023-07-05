package org.cars.service.carservice;

import org.cars.model.Car;

import java.sql.SQLException;
import java.util.List;

/**
 * Интерфейс определяет методы для работы с данными базы данных автомобилей ( из БД, фалоф .txt, .json)
 */
public interface CarService {

    List<Car> sortByPrice() throws SQLException;

    List<Car> sortByBrand() throws SQLException;

    double findMaxPrice();

    double findMinPrice();

    List<Car> findCarWithMaxPrice() throws SQLException;

    List<Car> findCarWithMinPrice() throws SQLException;

    List<Car> findCarByBrand(String brand) throws SQLException;

    List<Car> findCarByModel(String model) throws SQLException;

    List<Car> findCarInPriceDiapason(double min, double max) throws SQLException;

    void setList(List<Car>list);

}
