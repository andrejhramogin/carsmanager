package org.cars.service;

import org.cars.model.Car;
import org.postgresql.ds.PGPoolingDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CarServicePostgres {

    //добавляет новый Car в таблицу
    public void addCarToTable(Car car) {

        String url = "jdbc:postgresql://127.0.0.1/postgres";
        String name = "postgres";
        String password = "password";
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();

            statement.executeUpdate(String.format("INSERT INTO cars (brand, model, year, price) VALUES ('%s', '%s', '%d', '%f') ",
                    car.getBrand(), car.getModel(), car.getYear(), car.getPrice()));

            connection.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    //удаляет из таблицы авто по id
    public void deleteCarFromTable(int num) {
        String url = "jdbc:postgresql://127.0.0.1/postgres";
        String name = "postgres";
        String password = "password";
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();

            statement.executeUpdate("DELETE FROM cars WHERE id = " + num);

            connection.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public double getMaxPrice() {
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

    public double getMinPrice() {
        String url = "jdbc:postgresql://127.0.0.1/postgres";
        String name = "postgres";
        String password = "password";
        double min = 0;

        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("Select max (price) from cars");
            while (rs.next()) {
                min = rs.getDouble("min");
                connection.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return min;
    }
}
