package org.cars.service;

import org.cars.model.Car;
import org.cars.utils.PrintListUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;


public class DBCarServiceImpl implements CarService {

    DBInOutServiceImpl dbInOutService = new DBInOutServiceImpl();

    //Сортирует по цене в порядке возрастания
    @Override
    public List<Car> sortByPrice() {
        return dbInOutService.getData("SELECT * FROM cars ORDER BY price");
    }

    //Сортирует по названию бренда в порядке возрастания
    @Override
    public List<Car> sortByBrand() {
        return dbInOutService.getData("SELECT * FROM cars ORDER BY brand");
    }

    //Находит максимальную цену
    @Override
    public double findMaxPrice() {
        String url = "jdbc:postgresql://127.0.0.1/postgres";
        String name = "postgres";
        String password = "password";
        double max = 0;

        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("Select max (price) from cars");
            while (rs.next()) {
                max = rs.getDouble("max");
                connection.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return max;
    }

    //Находит минимальную цену
    @Override
    public double findMinPrice() {
        String url = "jdbc:postgresql://127.0.0.1/postgres";
        String name = "postgres";
        String password = "password";
        double min = 0;

        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("Select min (price) from cars");
            while (rs.next()) {
                min = rs.getDouble("min");
                connection.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return min;
    }

    //Находит все авто с максимальной ценой
    @Override
    public List<Car> findCarWithMaxPrice() {
        return dbInOutService.getData("SELECT * from cars WHERE price = ( select  MAX(price) FROM cars)");
    }

    //Находит все авто с минимальной ценой
    @Override
    public List<Car> findCarWithMinPrice() {
        return dbInOutService.getData("SELECT * from cars WHERE price = ( select  MIN(price) FROM cars)");
    }

    //Находит все авто по названию бренда
    @Override
    public List<Car> findCarByBrand(String brand) {
        return dbInOutService.getData("SELECT * FROM cars WHERE lower (brand) = lower ('" + brand + "')");
    }

    //Находит все авто по названию модели
    @Override
    public List<Car> findCarByModel(String model) {
        return dbInOutService.getData("SELECT * FROM cars WHERE lower (model) = lower ('" + model + "')");
    }

    //Находит все авто в задаваемом диапазоне цен
    @Override
    public List<Car> findCarInPriceDiapason(double min, double max) {
        return dbInOutService.getData("SELECT * FROM cars WHERE price between " + min + " and " + max + " order by price");
    }

    public void printList() {
        PrintListUtils.printCarList(dbInOutService.getData("Select * From cars"));
    }
}
