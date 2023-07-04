package org.cars.service.carservice;

import org.cars.model.Car;
import org.cars.service.dbconnectionservice.DBConnectionService;
import org.cars.service.inoutservice.DBInOutServiceImpl;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;

/**
 * Имплементирует интерфейс CarService
 * Переопределяет методы интерфейса CarService
 */

@Component("dBCarServiceImpl")
public class DBCarServiceImpl implements CarService {

    DBConnectionService dbConnection = DBConnectionService.getInstance();
    DBInOutServiceImpl dbInOutService = new DBInOutServiceImpl();
    Connection connection = dbConnection.newConnection();
    Statement statement = connection.createStatement();
    ResultSet rs;

    public DBCarServiceImpl() throws SQLException {
    }

    //Сортирует по цене в порядке возрастания
    @Override
    public List<Car> sortByPrice() throws SQLException {
        return dbInOutService.getData("SELECT * FROM cars ORDER BY price");
    }

    //Сортирует по названию бренда в порядке возрастания
    @Override
    public List<Car> sortByBrand() throws SQLException {
        return dbInOutService.getData("SELECT * FROM cars ORDER BY brand");
    }

    //Находит максимальную цену
    @Override
    public double findMaxPrice() {
        double max = 0;
        try {
            rs = statement.executeQuery("Select max (price) from cars");
            while (rs.next()) {
                max = rs.getDouble("max");
            }
            dbConnection.closeConnection();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return max;
    }

    //Находит минимальную цену
    @Override
    public double findMinPrice() {
        double min = 0;
        try {
            rs = statement.executeQuery("Select min (price) from cars");
            while (rs.next()) {
                min = rs.getDouble("min");
            }
            dbConnection.closeConnection();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return min;
    }

    //Находит все авто с максимальной ценой
    @Override
    public List<Car> findCarWithMaxPrice() throws SQLException {
        return dbInOutService.getData("SELECT * from cars WHERE price = ( select  MAX(price) FROM cars)");
    }

    //Находит все авто с минимальной ценой
    @Override
    public List<Car> findCarWithMinPrice() throws SQLException {
        return dbInOutService.getData("SELECT * from cars WHERE price = ( select  MIN(price) FROM cars)");
    }

    //Находит все авто по названию бренда
    @Override
    public List<Car> findCarByBrand(String brand) throws SQLException {
        return dbInOutService.getData("SELECT * FROM cars WHERE lower (brand) = lower ('" + brand + "')");
    }

    //Находит все авто по названию модели
    @Override
    public List<Car> findCarByModel(String model) throws SQLException {
        return dbInOutService.getData("SELECT * FROM cars WHERE lower (model) = lower ('" + model + "')");
    }

    //Находит все авто в задаваемом диапазоне цен
    @Override
    public List<Car> findCarInPriceDiapason(double min, double max) throws SQLException {
        return dbInOutService.getData("SELECT * FROM cars WHERE price between " + min + " and " + max + " order by price");
    }

    @Override
    public void setList(List<Car> list) {

    }
}
