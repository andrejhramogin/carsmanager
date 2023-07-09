package org.cars.controller;

import org.cars.model.Car;
import org.cars.service.carservice.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

//@Slf4j
//@Log4j
@RestController
public class CarsController {

    private static final Logger logger = LoggerFactory.getLogger(CarsController.class);
    //CRUD
    @Autowired
    private CarService carService;

    //логика создания машины в БД и возвращение ее в ответе
    @PostMapping("/cars")
    public Car createCar(@RequestBody Car car) throws SQLException {
        logger.info("create new car: + {}", car); // выводится в логи
        return carService.createNewCar(car);
    }

    // логика обнволения машины по id в БД и возвращение ее в ответе
    @PutMapping("/cars/{id}")
    public Car updateCar(@RequestBody Car car, @PathVariable int id) throws SQLException {
        return carService.update(car, id);
    }

    //вернуть список всех машин которые лежат в бд
    @GetMapping("/cars")
     public List<Car> getCars() throws SQLException {
        logger.info("Show all cars");//выводится в логи
        return carService.getAllCars();
    }

    //вернуть машину по id
    @GetMapping("/cars/{id}")
    public Car getCarById(@PathVariable int id) throws SQLException {
        logger.info("show car by id ");//не выводится в логи
        return carService.findCarById(id);
    }

    //удалить машину по id
    @DeleteMapping("/cars/{id}")
    public void deleteCarById(@PathVariable int id) throws SQLException {
        logger.info("car has been removed");//не выводится в логи
        carService.deleteCarById(id);
    }
}
