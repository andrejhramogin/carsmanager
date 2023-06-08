package org.cars;

import org.cars.utils.TestDataInitialisation;
import org.cars.service.CarService;
import org.cars.utils.PrintListUtils;
import org.cars.service.JsonService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        CarService carService = new CarService();
        JsonService jsonService = new JsonService();

        //самые дорогие машины
        System.out.println("Most expensive cars:");
        PrintListUtils.printCarList(carService.findCarMaxPrice(TestDataInitialisation.createCarList()));
        System.out.println();

        //самые дешевые машины
        System.out.println("Most cheapest cars:");
        PrintListUtils.printCarList(carService.findCarMinPrice(TestDataInitialisation.createCarList()));

//        JSON запись списка отсортированного по бренду
        jsonService.jsonWriteCarList(carService.createCarListByBrand(jsonService.jsonReadCarList("cars"), "toyota"), "toyota");
        System.out.println();

//        Чтение из файла toyota.json
        System.out.println("Read List<Car> \"toyota\" from JSON file: ");
        PrintListUtils.printCarList(jsonService.jsonReadCarList("toyota"));
    }
}