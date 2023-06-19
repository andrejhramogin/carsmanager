package org.cars.utils;

import org.cars.model.Car;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.*;

public class MethodUtils {

    //создает List<Car> как тестовые данные
    public static List<Car> createCarList() {
        return new ArrayList<>(Arrays.asList(
                new Car("Toyota", "Camry", 2010, 10000.0),
                new Car("Toyota", "Camry", 2012, 12000.5),
                new Car("Toyota", "Camry", 2014, 14000.6),
                new Car("Toyota", "Solara", 2003, 6000.8),
                new Car("Toyota", "Solara", 2013, 16000.0),
                new Car("BMW", "320", 2003, 3000.8),
                new Car("BMW", "520", 2013, 3000.8),
                new Car("BMW", "720", 2015, 16000.0),
                new Car("Opel", "Astra", 2003, 4000.8),
                new Car("Opel", "Corsa", 2020, 13000.8),
                new Car("Opel", "Zafira", 2019, 11000)));
    }


    //считывает и возвращает int значение (используется в меню программы)
    public static int getInteger() {
        Scanner scanner = new Scanner(System.in);
        int i;
        try {
            i = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Вы ввели не целое число");
            while (true) {
                scanner.next();
                if (scanner.hasNextInt()) {
                    i = scanner.nextInt();
                    break;
                }
                System.out.println("Вы ввели не целое число");
            }
        }
        return i;
    }

    public static double getDouble() {
        Scanner scanner = new Scanner(System.in);
        double d;
        try {
            d = scanner.nextDouble();
        } catch (InputMismatchException e) {
            System.out.println("Вы ввели не число");
            while (true) {
                scanner.next();
                if (scanner.hasNextDouble()) {
                    d = scanner.nextDouble();
                    break;
                }
                System.out.println("Вы ввели не число");
            }
        }
        return d;
    }


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
}
