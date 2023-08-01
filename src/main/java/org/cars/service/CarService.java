package org.cars.service;

import org.cars.dto.CarDto;
import org.cars.dto.request.CreateCarRq;
import org.cars.entity.Car;
import org.cars.entity.CarPage;
import org.cars.entity.CarSearchCriteria;
import org.springframework.data.domain.Page;

/**
 * Интерфейс определяет методы для работы с данными автомобилей из БД.
 */

public interface CarService {

    void deleteCarById(int num);

    CarDto createNewCar(CreateCarRq createCarRq);

    CarDto findCarById(int id);

    CarDto update (CreateCarRq carRq, int id);

    Page<Car> getCarsWithSortingAndFiltration(CarPage carPage, CarSearchCriteria carSearchCriteria);
}
