package org.cars.service.carservice;

import org.cars.entity.Car;
import org.cars.entity.CarPage;
import org.cars.entity.CarSearchCriteria;
import org.cars.error.ResourceNotFoundException;
import org.cars.repository.CarCriteriaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.cars.repository.CarJpaRepository;

import java.sql.*;
import java.util.List;
import java.util.Optional;

/**
 * Имплементирует интерфейс CarService
 * Переопределяет методы интерфейса CarService
 */
@Component("dBCarServiceImpl")
public class DBCarServiceImpl implements CarService {

    private static final Logger logger = LoggerFactory.getLogger(DBCarServiceImpl.class);

    @Autowired
    private CarJpaRepository carJpaRepository;
    @Autowired
    private CarCriteriaRepository carCriteriaRepository;

    public DBCarServiceImpl(CarJpaRepository carJpaRepository,
                            CarCriteriaRepository carCriteriaRepository) {
        this.carJpaRepository = carJpaRepository;
        this.carCriteriaRepository = carCriteriaRepository;
    }

    public DBCarServiceImpl() {
    }

    @Override
    public Page<Car> getCarsWithSortingAndFiltration(CarPage carPage,
                                                     CarSearchCriteria carSearchCriteria) {
        return carCriteriaRepository.findAllWithFilters(carPage, carSearchCriteria);
    }

    //удаление авто по id
    @Override
    public void deleteCarById(int id) throws SQLException {
        carJpaRepository.deleteById(id);
    }

    //создаёт новое авто в таблице "cars" и возвращает его.
    public Car createNewCar(Car car) {
        return carJpaRepository.save(car);
    }

    //находит в БД и возвращает авто по id.
    @Override
    public Car findCarById(int id) {
        return carJpaRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Car with id " + id + " not found")
        );
    }

    //обновляет поля и возвращает обновленную car
    @Override
    public Car update(Car car, int id) throws SQLException {
        Car updatedCar = carJpaRepository.getReferenceById(id);
        car.setId(updatedCar.getId());
        return carJpaRepository.save(car); //? изменяет авто, но не возвращает. Ошибка 500.
    }
}