package org.cars.service.dbservice;

import org.cars.model.Car;
import org.cars.service.dbconnectionservice.DBConnectionService;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * В классе находятся методы для:
 * void createTableCars() - создания таблицы в БД,
 * void addCarToTable(Car car) - добавления нового Car в таблицу,
 * void deleteCarFromTable(int num) - удаления Car из таблицы по номеру ID.
 */

public class DBService {

    DBConnectionService dbConnection = DBConnectionService.getInstance();
    Connection connection = dbConnection.newConnection();
    Statement statement = connection.createStatement();

    public DBService() throws SQLException {
    }

    //создает таблицу cars в базе данных.
    public void createTableCars() {
        try {
            statement.executeUpdate("CREATE TABLE cars2 (\n" +
                    "    id serial PRIMARY key not null,\n" +
                    "    brand varchar (100) NOT NULL,\n" +
                    "    model varchar(100) NOT NULL,\n" +
                    "    year smallint NOT NULL,\n" +
                    "    price float NOT NULL\n" +
                    "  )");
            System.out.println("Таблица создана.");
            dbConnection.closeConnection();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    //добавляет новый Car в таблицу.
    public void addCarToTable(Car car) {
        try {
            statement.executeUpdate(String.format("INSERT INTO cars (brand, model, year, price) VALUES ('%s', '%s', '%d', '%f') ",
                    car.getBrand(), car.getModel(), car.getYear(), car.getPrice()));
            System.out.println("Автомобиль добавлен в таблицу.");
            dbConnection.closeConnection();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    //удаляет из таблицы авто по номеру ID.
    public void deleteCarFromTable(int num) throws SQLException {
        try {
            statement.executeUpdate("DELETE FROM cars WHERE id = " + num);
            System.out.println("Строка с номером ID = " + num + "удалена из таблицы");
            dbConnection.closeConnection();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
