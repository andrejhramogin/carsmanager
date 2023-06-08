package org.cars.service;

import org.cars.model.Car;
import org.cars.model.CarList;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class WriteRead {

    public static void writeCarList(List<Car> list, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName + ".bin")))) {
            oos.writeObject(new CarList(list));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<Car> readCarList(String fileName) {
        CarList carList = new CarList();
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(fileName + ".bin")))) {
            carList = (CarList) ois.readObject();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return carList.getCarList();
    }
}
