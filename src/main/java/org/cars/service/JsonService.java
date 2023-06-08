package org.cars.service;

import org.cars.model.Car;

import java.io.IOException;
import java.util.List;

public interface JsonService {

    void jsonWriteCarList(List<Car> list, String fileName) throws IOException;

    List<Car> jsonReadCarList(String fileName) throws IOException;


}
