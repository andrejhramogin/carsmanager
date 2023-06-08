package org.cars;

import org.cars.utils.TestDataInitialisation;
import org.cars.service.CarServiceImpl;
import org.cars.utils.PrintListUtils;
import org.cars.service.JsonServiceImpl;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        CarServiceImpl carServiceImpl = new CarServiceImpl();
        JsonServiceImpl json = new JsonServiceImpl();

        //самые дорогие машины
        System.out.println("Most expensive cars:");
        PrintListUtils.printCarList(carServiceImpl.findCarMaxPrice(TestDataInitialisation.createCarList()));
        System.out.println();

        //самые дешевые машины
        System.out.println("Most cheapest cars:");
        PrintListUtils.printCarList(carServiceImpl.findCarMinPrice(TestDataInitialisation.createCarList()));

//        JSON запись списка отсортированного по бренду
        json.jsonWriteCarList(carServiceImpl.createCarListByBrand(json.jsonReadCarList("cars"), "toyota"), "toyota");
        System.out.println();

//        Чтение из файла toyota.json
        System.out.println("Read List<Car> \"toyota\" from JSON file: ");
        PrintListUtils.printCarList(json.jsonReadCarList("toyota"));
    }
}