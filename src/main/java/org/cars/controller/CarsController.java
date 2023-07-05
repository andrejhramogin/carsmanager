package org.cars.controller;

import org.cars.model.Car;
import org.cars.service.carservice.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
public class CarsController {

    // CRUD
    @Autowired
    @Qualifier("DBCarServiceImpl")
    private CarService carService;

    @PostMapping("/cars")
    public Car createCar(@RequestBody Car car) {
        // логика создания машины в БД и возвращение ее в ответе
        return carService.create(car);
    }

    @PutMapping("/cars/{id}")
    public Car updateCar(@RequestBody Car car, @PathVariable Long id) {
        // логика обнволения машины по id в БД и возвращение ее в ответе
        return carService.update(car);
    }

    @GetMapping("/cars")
    public List<Car> getCars() throws SQLException {
        // вернуть список всех машин которые лежат в бд
        return carService.getAllCars();
    }

    @GetMapping("/cars/{id}")
    public Car getCarById(@PathVariable Long id) {
        // вернуть машину по id
        return carService.getCarById(id);
    }

    @DeleteMapping("/cars/{id}")
    public void deleteCarById(@PathVariable Long id) {
        // удалить машину по id
        carService.deleteCarById(id);
    }
}
