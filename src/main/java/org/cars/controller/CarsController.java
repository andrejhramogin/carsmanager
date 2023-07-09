package org.cars.controller;

import org.cars.model.Car;
import org.cars.service.carservice.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;


@RestController
public class CarsController {

    //CRUD

    @Autowired
//    @Qualifier("dBCarServiceImpl")
    private CarService carService;

    //логика создания машины в БД и возвращение ее в ответе
    @PostMapping("/cars")
    public Car createCar(@RequestBody Car car) throws SQLException {
        return carService.createNewCar(car);
    }

    @PutMapping("/cars/{id}")
    public Car updateCar(@RequestBody Car car, @PathVariable int id) throws SQLException {
        // логика обнволения машины по id в БД и возвращение ее в ответе
        return carService.update(car, id);
    }

    //вернуть список всех машин которые лежат в бд
    @GetMapping("/cars")
    public List<Car> getCars() throws SQLException {
        return carService.getAllCars();
    }

    //вернуть машину по id
    @GetMapping("/cars/{id}")
    public Car getCarById(@PathVariable int id) throws SQLException {
        return carService.findCarById(id);
    }

    //удалить машину по id
    @DeleteMapping("/cars/{id}")
    public void deleteCarById(@PathVariable int id) throws SQLException {
        carService.deleteCarById(id);
    }
}
