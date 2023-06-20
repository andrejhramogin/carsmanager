package org.cars.service;

import org.cars.model.Car;
import org.cars.utils.MethodUtils;
import org.cars.utils.PrintListUtils;
import org.cars.utils.UIMessage;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ConsoleMenuService {

    //меню приложения
    public void selectAction() throws IOException {

        CarServicePostgres carServicePostgres = new CarServicePostgres();
        PostgresInOutServiceImpl inOut = new PostgresInOutServiceImpl();
        CarServiceImpl carService = new CarServiceImpl();
        System.out.println(UIMessage.MENU_SELECT_ACTION);

        int choice = MethodUtils.getInteger();

        switch (choice) {
            case 0:
                return;
            case 1:
                MethodUtils.createTableCars();
                break;
            case 2:
                inOut.setData(MethodUtils.createCarList());
                break;
            case 3:
                 carServicePostgres.addCarToTable(carService.createCar());
                break;
            case 4:
                System.out.println("Введите номер id удаляемой позиции:");
                int pos = MethodUtils.getInteger();
                carServicePostgres.deleteCarFromTable(pos);
                break;
            case 5:
                PrintListUtils.printCarList(inOut.getData("Select * From cars"));
                break;
            case 6:
                System.out.println("Максимальная цена: " + carServicePostgres.getMaxPrice());
                break;
            case 7:
                System.out.println("Минимальная цена: " + carServicePostgres.getMinPrice());
                break;
            case 8:
                PrintListUtils.printCarList(sortingData());
        }
        selectAction();
    }

    //операции сортировки данных из таблицы
    public List<Car> sortingData() throws IOException {
        Scanner scanner = new Scanner(System.in);
        PostgresInOutServiceImpl inOut = new PostgresInOutServiceImpl();
        System.out.println(UIMessage.MENU_SORTING_DATA);
        int choice = MethodUtils.getInteger();
        String query = null;

        switch (choice) {
            case 0:
                selectAction();
            case 1:
                query = "SELECT * FROM cars ORDER BY brand";
                break;
            case 2:
                query = "SELECT * FROM cars ORDER BY brand DESC";
                break;
            case 3:
                query = "SELECT * FROM cars ORDER BY price";
                break;
            case 4:
                query = "SELECT * FROM cars ORDER BY price DESC";
                break;
            case 5:
                System.out.println("Бренд:");
                String brand = scanner.nextLine();
                query = "SELECT * FROM cars WHERE lower (brand) = lower ('" + brand + "')";
                break;
            case 6:
                System.out.println("Модель:");
                String model = scanner.nextLine();
                query = "SELECT * FROM cars WHERE lower (model) = lower ('" + model + "')";
                break;
            case 7:
                System.out.println("Введите минимальную цену диапазона");
                double min = MethodUtils.getDouble();
                System.out.println("Введите максимальную цену диапазона");
                double max = MethodUtils.getDouble();
                query = "SELECT * FROM cars WHERE price between " + min + " and " + max + " order by price";
                break;
            case 8:
                query = "SELECT * from cars WHERE price = ( select  MAX(price) FROM cars)";
                break;
            case 9:
                query = "SELECT * from cars WHERE price = ( select  MIN(price) FROM cars)";
                break;
        }
        return inOut.getData(query);
    }
}
