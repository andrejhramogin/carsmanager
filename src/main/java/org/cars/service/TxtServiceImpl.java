package org.cars.service;

import org.cars.model.Car;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TxtServiceImpl implements JsonService {

       @Override
    public void writeCarList(List<Car> list, String fileName) {
           try (FileWriter writer = new FileWriter(fileName+".txt", false)) {
               for (Car car : list) {
                   writer.write(car.getBrand() + " " + car.getModel() + " " + car.getYear() + " " + car.getPrice());
                   writer.append('\n').flush();
               }
           } catch (IOException e) {
               System.out.println(e.getMessage());
           }
    }

    @Override
    public List<Car> readCarList(String fileName){
        List<Car> list = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(fileName+ ".txt"))) {
            while (scanner.hasNextLine()) {
                String[] cars = scanner.nextLine().split(" ");
                list.add(new Car(cars[0], cars[1], Integer.parseInt(cars[2]), Double.parseDouble(cars[3])));
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
}
