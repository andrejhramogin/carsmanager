package org.cars.service;

import org.cars.model.Car;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PostgresInOutServiceImpl implements InOutService {

    //принимает List<Car> и вносит данные в таблицу cars в базе данных
    @Override
    public void setData(List<Car> list) throws IOException{
        String url = "jdbc:postgresql://127.0.0.1/postgres";
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

    //считывает данные из базы и возвращает их в виде List<Car>
    @Override
    public List<Car> getData() {
        String url = "jdbc:postgresql://127.0.0.1/postgres";
        String name = "postgres";
        String password = "password";
        List<Car> list = new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("Select * From cars");
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

    public List<Car> getDataByQuery(String query) {
        String url = "jdbc:postgresql://127.0.0.1/postgres";
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