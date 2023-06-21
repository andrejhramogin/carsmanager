package org.cars.service;

import org.cars.model.Car;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * В классе находятся методы для создания таблицы в БД, добавления нового Car в таблицу,
 * удаления Car из таблицы по номеру ID.
 */

public class DBService {

    //создает таблицу cars  в базе данных
    public static void createTableCars(){
        String url = "jdbc:postgresql://127.0.0.1/postgres";
        String name = "postgres";
        String password = "password";
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();

            statement.executeUpdate("CREATE TABLE cars (\n" +
                    "    id serial PRIMARY key not null,\n" +
                    "    brand varchar (100) NOT NULL,\n" +
                    "    model varchar(100) NOT NULL,\n" +
                    "    year smallint NOT NULL,\n" +
                    "    price float NOT NULL\n" +
                    "  )");
            connection.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

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
}
