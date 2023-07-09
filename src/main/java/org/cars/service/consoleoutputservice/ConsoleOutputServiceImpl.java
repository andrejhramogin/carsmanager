package org.cars.service.consoleoutputservice;

import org.cars.model.Car;
import org.cars.service.dbconnectionservice.DBConnectionService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Класс содержит методы для вывода данных на консоль:
 * void printList(List<Car> list) - выводит на консоль List<Car>
 * void printTable() throws SQLException - выводит на консоль таблицу "cars"
 */
public class ConsoleOutputServiceImpl {

    //выводит на консоль List<Car>

    public void printList(List<Car> list) {
        try {
            if (list.isEmpty()) {
                System.out.println("List is empty.");
            } else {
                list.forEach(System.out::println);
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    //выводит на консоль таблицу из DB

    public void printAllTable() throws SQLException {
        DBConnectionService dbConnection = DBConnectionService.getInstance();
        Connection connection = dbConnection.newConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM cars");
        while (rs.next()) {
            System.out.println("id: " + rs.getInt("id") +
                    ", Brand: " + rs.getString("brand") +
                    ", Model: " + rs.getString("model") +
                    ", Year of manufacture: " + rs.getInt("year") +
                    ", Price: " + rs.getDouble("price") + ".");
        }
    }

    public void printCarFromTable(int id) throws SQLException {
        DBConnectionService dbConnection = DBConnectionService.getInstance();
        Connection connection = dbConnection.newConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM cars WHERE id = " + id);
        while (rs.next()) {
            System.out.println("id: " + rs.getInt("id") +
                    ", Brand: " + rs.getString("brand") +
                    ", Model: " + rs.getString("model") +
                    ", Year of manufacture: " + rs.getInt("year") +
                    ", Price: " + rs.getDouble("price") + ".");
        }
    }
}
