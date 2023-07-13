package org.cars.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.cars.model.Car;
import org.cars.service.carservice.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController //@Controller + @ResponseBody
@Tag(name = "Cars API") //активирует Swagger
public class CarsController {

    //CRUD (create, read, update, delete)
    private static final Logger logger = LoggerFactory.getLogger(CarsController.class);

    @Autowired
    private CarService carService;

    /**
     * Создание нового car в БД и возвращение его в ответе.
     *
     * @param car - передается для помещения его в БД.
     * @return Ответ - новый созданный car в БД, полученный из БД.
     */
    @PostMapping("/cars")
    @Operation(summary = "Create a new car in DB", description = "Creates a new car in DB and returns it")
    @ApiResponse(responseCode = "200", description = "A car was created successfully")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public Car createCar(@RequestBody Car car) throws SQLException {
        logger.info("create new car: + {}.", car);
        return carService.createNewCar(car);
    }

    /**
     * Обновление существуещего car в БД по номеру id.
     *
     * @param car - передается в RequestBody для изменения полей car в БД.
     * @param id  - значение id для car, которая будет обновляться.
     * @return - Ответ - обновленный car, полученный из БД.
     */
    @PutMapping("/cars/{id}")
    @Operation(summary = "Update car", description = "Updates the car with the id number and returns it from the DB")
    @ApiResponse(responseCode = "200", description = "A car was updated successfully")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public Car updateCar(@RequestBody Car car, @PathVariable int id) throws SQLException {
        logger.info("car id {} updated", id);
        return carService.update(car, id);
    }

    /**
     * Получение cars из БД по дополнительным параметрам фильтрации и сортировки.
     *
     * @return Ответ - List<Car> - список car из таблицы 'cars' в БД.
     */
    @GetMapping("/cars")
    @Operation(summary = "Get cars", description = "Get cars according to the specified parameters from table 'cars'")
    @ApiResponse(responseCode = "200", description = "Cars from the table 'cars' were received successfully")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public List<Car> getCarsByRequestParam(
            @RequestParam("sortBy") String sortBy,
            @RequestParam("sortDirection") String sortDirection,
            @RequestParam("filter") String filter)
            throws SQLException {
        logger.info("Сars matching the request have been received.");
        return carService.getByParam(sortBy, sortDirection, filter);
    }

    /**
     * Получение car из БД по значению id.
     *
     * @param id - значение id, по которому будет найден и возвращен car.
     * @return Ответ - car, найденный по id значению.
     */
    @GetMapping("/cars/{id}")
    @Operation(summary = "Get car", description = "Get car with the id number from table 'cars'")
    @ApiResponse(responseCode = "200", description = "Car from the table 'cars' were received successfully")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public Car getCarById(@PathVariable int id) throws SQLException {
        logger.info("car is shown by id: {}.", id);
        return carService.findCarById(id);
    }

    /**
     * Удаление car из БД по значению id.
     *
     * @param id начение id, по которому будет найден и удален car.
     */
    @DeleteMapping("/cars/{id}")
    @Operation(summary = "Delete car", description = "Delete car with the id number from table 'cars'")
    @ApiResponse(responseCode = "200", description = "Car from the table 'cars' were deleted successfully")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public void deleteCarById(@PathVariable int id) throws SQLException {
        logger.info("car (id {}) has been removed.", id);
        carService.deleteCarById(id);
    }
}
