package org.example;

import org.example.service.CarListCreate;
import org.example.service.CarService;
import org.example.utils.PrintList;
import org.example.utils.JsonService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        //самые дорогие машины
        System.out.println("Most expensive cars:");
        PrintList.printCarList(CarService.findCarMaxPrice(CarListCreate.createCarList()));
        System.out.println();

        //самые дешевые машины
        System.out.println("Most cheapest cars:");
        PrintList.printCarList(CarService.findCarMinPrice(CarListCreate.createCarList()));

//        JSON запись списка отсортированного по бренду
        JsonService.jsonWriteCarList(CarService.createCarListByBrand(JsonService.jsonReadCarList("cars"), "toyota"), "toyota");
        System.out.println();
//        Чтение из файла toyota.json
        System.out.println("Read List<Car> \"toyota\" from JSON file: ");
        PrintList.printCarList(JsonService.jsonReadCarList("toyota"));
    }
}