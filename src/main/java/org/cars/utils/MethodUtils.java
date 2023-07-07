package org.cars.utils;

import org.cars.model.Car;

import java.util.*;

/**
 * В классе содержатся вспомогательные методы для:
 *  List<Car> createCarList() - создания List<Car> с тестовыми данными
 *  int getInteger() - получения значения int с коносоли
 *  double getDouble() - получения значения double с консоли
 *  Car createCar() - создание нового экземпляра Car
 */

public class MethodUtils {

    //создает List<Car> как тестовые данные
    public static List<Car> createCarList() {
        return new ArrayList<>(Arrays.asList(
                new Car(1, "Toyota", "Camry", 2010, 10000.0),
                new Car(2,"Toyota", "Camry", 2012, 12000.5),
                new Car(3,"Toyota", "Camry", 2014, 14000.6),
                new Car(4, "Toyota", "Solara", 2003, 6000.8),
                new Car(5,"Toyota", "Solara", 2013, 16000.0),
                new Car(6,"BMW", "320", 2003, 3000.8),
                new Car(7,"BMW", "520", 2013, 3000.8),
                new Car(8,"BMW", "720", 2015, 16000.0),
                new Car(9,"Opel", "Astra", 2003, 4000.8),
                new Car(10,"Opel", "Corsa", 2020, 13000.8),
                new Car(11, "Opel", "Zafira", 2019, 11000)));
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

//    создает новый экземпляр Car
    public static Car createCar() {
        int id = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Brand:");
        String brand = scanner.nextLine();
        System.out.println("Model:");
        String model = scanner.nextLine();
        System.out.println("Year of production:");
        int year = getInteger();
        System.out.println("Price:");
        double price = getDouble();
        return new Car(id, brand, model, year, price);
    }
}
