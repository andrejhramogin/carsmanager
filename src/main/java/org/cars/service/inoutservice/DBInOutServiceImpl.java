package org.cars.service.inoutservice;

import org.cars.model.Car;
import org.cars.service.dbconnectionservice.DBConnectionService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBInOutServiceImpl implements InOutService {

    public DBInOutServiceImpl() throws SQLException {}

    DBConnectionService dbConnection = DBConnectionService.getInstance();
    Connection connection = dbConnection.newConnection();
    Statement statement = connection.createStatement();
    ResultSet rs;

    //принимает List<Car> и вносит данные в таблицу cars в базе данных
    @Override
    public void setData(List<Car> list) {

        try {
            for (Car car : list) {
                statement.executeUpdate(String.format("INSERT INTO cars2 (brand, model, year, price) VALUES ('%s', '%s', '%d', '%f') ",
                        car.getBrand(), car.getModel(), car.getYear(), car.getPrice()));
            }
            System.out.println("Данные вненсены.");
            dbConnection.closeConnection();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    //Получает данные из таблицы и возвращает List<Car>
    public List<Car> getData(String query) throws SQLException {
        List<Car> list = new ArrayList<>();
        rs = statement.executeQuery(query);

        try {
            while (rs.next()) {
                Car car = new Car();
                car.setBrand(rs.getString("brand"));
                car.setModel(rs.getString("model"));
                car.setYear(rs.getInt("year"));
                car.setPrice(rs.getDouble("price"));
                list.add(car);
            }
            dbConnection.closeConnection();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
}