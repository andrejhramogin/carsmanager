package org.cars.service;

import org.cars.model.Car;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBInOutServiceImpl implements InOutService {

    //принимает List<Car> и вносит данные в таблицу cars в базе данных
    @Override
    public void setData(List<Car> list) {
        String url = "jdbc:postgresql://localhost/postgres";
        String name = "postgres";
        String password = "password";
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();
            for (Car car : list) {
                statement.executeUpdate(String.format("INSERT INTO cars (brand, model, year, price) VALUES ('%s', '%s', '%d', '%f') ",
                        car.getBrand(), car.getModel(), car.getYear(), car.getPrice()));
            }
            connection.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    //Получает данные из таблицы и возвращает List<Car>
    public List<Car> getData(String query) {
        String url = "jdbc:postgresql://localhost/postgres";
        String name = "postgres";
        String password = "password";
        List<Car> list = new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Car car = new Car();
                car.setBrand(rs.getString("brand"));
                car.setModel(rs.getString("model"));
                car.setYear(rs.getInt("year"));
                car.setPrice(rs.getDouble("price"));
                list.add(car);
                connection.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
}