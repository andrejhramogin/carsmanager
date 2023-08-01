package org.cars.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.cars.dto.CarDto;
import org.cars.dto.request.CreateCarRq;
import org.cars.entity.Car;
import org.cars.entity.CarPage;
import org.cars.entity.CarSearchCriteria;
import org.cars.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
     * @return Ответ - новый созданный car в БД (ответ получается из БД) в виде CarDto.
     * @RequestBody CreateCarRq carRq - принмаемый запрос, из данных которого создается
     * CarDto, которое преобразуется в entity (car), которая помещается в БД.
     * Данные, поступающие в  CreateCarRq carRq валидируются в record CreateCarRq.java
     */
    @PostMapping("/cars")
    @Operation(summary = "Create a new car in DB", description = "Creates a new car in DB and returns it")
    @ApiResponse(responseCode = "200", description = "A car was created successfully")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")

     public CarDto createCar(@Valid @RequestBody CreateCarRq carRq){
        return carService.createNewCar(carRq);
    }

    /**
     * Обновление существуещего car в БД по номеру id.
     *
     * @param carRq - передается в RequestBody для изменения полей car в БД.
     * Данные, поступающие в  CreateCarRq carRq валидируются в record CreateCarRq.java
     * @param id  - значение id для car, которая будет обновляться.
     * @return - Ответ - обновленный car, полученный из БД в виде CarDto
     *  Если ID не существует, возвращается ответ: "statusCode": 404, "message": "Car with id {id} not found"
     */
    @PutMapping("/cars/{id}")
    @Operation(summary = "Update car", description = "Updates the car with the id number and returns it from the DB")
    @ApiResponse(responseCode = "200", description = "A car was updated successfully")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public CarDto updateCar(@Valid @RequestBody CreateCarRq carRq, @PathVariable int id){
        logger.info("car id {} updated", id);
        return carService.update(carRq, id);
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

    public ResponseEntity<Page<Car>> getCars(CarPage carPage,
                                             CarSearchCriteria carSearchCriteria) {
        return new ResponseEntity<>(carService.getCarsWithSortingAndFiltration(carPage, carSearchCriteria), HttpStatus.OK);
    }

    /**
     * Получение car из БД по значению id в виде CarDto.
     *
     * @param id - значение id, по которому будет найден и возвращен car.
     * @return Ответ - car, найденный по id значению.
     * Если ID не существует, возвращается ответ: "statusCode": 404, "message": "Car with id {id} not found"
     */
    @GetMapping("/cars/{id}")
    @Operation(summary = "Get car", description = "Get car with the id number from table 'cars'")
    @ApiResponse(responseCode = "200", description = "Car from the table 'cars' were received successfully")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")

//    Отрабатывает правильно через обработчик ошибок
    public CarDto getCar(@PathVariable int id){
        return carService.findCarById(id);
    }

//    отрабатывает правильно
//
//    public ResponseEntity<?> getCar(@PathVariable int id) {
//        try {
//            Car car = carService.findCarById(id);
//            return new ResponseEntity<>(car, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(),
//                    "Car with id " + id + " not found"), HttpStatus.NOT_FOUND);
//        }
//    }

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
    public void deleteCarById(@PathVariable int id){
        logger.info("car (id {}) has been removed.", id);
        carService.deleteCarById(id);
    }
}
