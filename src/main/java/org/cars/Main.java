package org.cars;

import org.cars.model.Car;
import org.cars.utils.PrintListUtils;
import org.cars.utils.TestDataInitialisation;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Car> list = getDataPostgres();
        PrintListUtils.printCarList(list);

    }


    public static List<Car> getDataPostgres() {
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
                car.setPrice(rs.getInt("price"));
                list.add(car);

                connection.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    public static void fillTable() {
        List<Car> list = new ArrayList<>(TestDataInitialisation.createCarList());


    }
}