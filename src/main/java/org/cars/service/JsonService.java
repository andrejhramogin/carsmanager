package org.cars.service;

import org.cars.model.Car;

import java.io.IOException;
import java.util.List;

public interface JsonService {

    void writeCarList(List<Car> list, String fileName) throws IOException;

    List<Car> readCarList(String fileName) throws IOException;
}
