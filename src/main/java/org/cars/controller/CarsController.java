package org.cars.controller;

import org.cars.model.Car;
import org.cars.service.carservice.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

//@Slf4j
@RestController
@SpringBootApplication
public class CarsController {

    //CRUD
    private final CarService carService;

    @Autowired
    public CarsController(@Qualifier("dBCarServiceImpl") CarService carService) {
        this.carService = carService;
    }

    //логика создания машины в БД и возвращение ее в ответе
    @PostMapping("/cars")
    public Car createCar(@RequestBody Car car) throws SQLException {

        return carService.createNewCar(car);
    }

    @PutMapping("/cars/{id}")
    public Car updateCar(@RequestBody Car car, @PathVariable int id) {
        // логика обнволения машины по id в БД и возвращение ее в ответе
        return null;
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
