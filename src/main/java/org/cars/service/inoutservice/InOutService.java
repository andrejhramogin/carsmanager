package org.cars.service.inoutservice;

import org.cars.entity.Car;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface InOutService {

    void setData(List<Car> list) throws IOException;

    List<Car> getData(String str) throws IOException, SQLException;
}
