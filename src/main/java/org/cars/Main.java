package org.cars;

import org.cars.model.Car;
import org.cars.service.TxtServiceImpl;
import org.cars.utils.TestDataInitialisation;
import org.cars.service.CarServiceImpl;
import org.cars.utils.PrintListUtils;
import org.cars.service.JsonServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        CarServiceImpl carServiceImpl = new CarServiceImpl();
        JsonServiceImpl json = new JsonServiceImpl();
        TxtServiceImpl txtService = new TxtServiceImpl();

        List<Car> list = new ArrayList<>(TestDataInitialisation.createCarList());

        //самые дорогие машины.
        System.out.println("Most expensive cars:");
        PrintListUtils.printCarList(carServiceImpl.findCarMaxPrice(list));
        System.out.println();

        //самые дешевые машины.
        System.out.println("Most cheapest cars:");
        PrintListUtils.printCarList(carServiceImpl.findCarMinPrice(list));

//        JSON запись списка, отсортированного по бренду.
        json.writeCarList(carServiceImpl.createCarListByBrand(json.readCarList("cars"), "toyota"), "toyota");
        System.out.println();

//        Чтение из файла toyota.json
        System.out.println("Read List<Car> \"toyota\" from JSON file: ");
        PrintListUtils.printCarList(json.readCarList("toyota"));


        //Запись списка в файл .txt
        txtService.writeCarList(list, "cars");

        //Чтение списка из файла .txt
        System.out.println();
        System.out.println("List<Car> from file .txt");
        PrintListUtils.printCarList(txtService.readCarList("cars"));

    }
}