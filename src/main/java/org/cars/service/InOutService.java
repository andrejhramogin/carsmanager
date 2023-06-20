package org.cars.service;

import org.cars.model.Car;

import java.io.IOException;
import java.util.List;

public interface InOutService {

    void setData(List<Car> list) throws IOException;

    List<Car> getData(String str) throws IOException;
}
