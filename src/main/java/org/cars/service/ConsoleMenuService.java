package org.cars.service;

import org.cars.utils.MethodUtils;
import org.cars.utils.PrintListUtils;

import java.io.IOException;
import java.util.Scanner;

/**
 * В классе содержится метод void selectAction(), который позволяет выбрать место хранения данных
 * (БД, файл .txt, файл .json), из которого они будут извлечены для дальнейших операций с ними, и сами операции.
 */
public class ConsoleMenuService {

    //Операции по сортировке, нахождению данных в базе автомобилей (в DB, файлах .txt, .json - по выбору пользователя).
    public void selectAction() throws IOException {

        Scanner scanner = new Scanner(System.in);

        ConsoleOutPutServiceImpl consoleOutPutService = new ConsoleOutPutServiceImpl();
        DBInOutServiceImpl dbInOutService = new DBInOutServiceImpl();

        CarService carService = null;
        InOutService inOutService;

        System.out.println("\nНажмите:\n" +
                "1 - Для работы с данными, хранящимися в базе данных.\n" +
                "2 - Для работы с данными, хранящимися в  файле .txt.\n" +
                "3 - Для работы с данными, хранящимися в файле .json.\n" +
                "0 - закончить работу.");

        int choiceSource = MethodUtils.getInteger();

        if (choiceSource == 1) {
            carService = new DBCarServiceImpl();
        } else if (choiceSource == 2) {
            inOutService = new TxtInOutServiceImpl();
            carService = new CarServiceImpl(inOutService.getData("cars"));
        } else if (choiceSource == 3) {
            inOutService = new JsonInOutServiceImpl();
            carService = new CarServiceImpl(inOutService.getData("cars"));
        } else if (choiceSource == 0) {
            return;
        } else {
            System.out.println("Выберите из предложенных вариантов");
            selectAction();
        }

        int choice;
        while (true) {
            System.out.println("\nНажмите:\n" +
                    "0 - закончить работу\n" +
                    "1 - вывести все данные из таблицы на консоль\n" +
                    "2 - вывести максимальную цену\n" +
                    "3 - вывести минимальную цену\n" +
                    "4 - вывести список, отсортированный по бренду в порядке возрастания\n" +
                    "5 - вывести список, отсортированный по цене в порядке возрастания\n" +
                    "6 - вывести список автомобилей по заданному названию бренда\n" +
                    "7 - вывести список, отсортированный по заданному названию модели \n" +
                    "8 - вывести список автомобилей с ценой в диапазоне\n" +
                    "9 - вывести список автомобилей с минимальной ценой\n" +
                    "10 - вывести список автомобилей с максимальной ценой\n" +
                    "11 - возврат к выбору места хранения данных.");

            choice = MethodUtils.getInteger();

            if (carService != null) {
                switch (choice) {
                    case 0:
                        return;
                    case 1:
                        consoleOutPutService.printList(dbInOutService.getData("Select * From cars"));
                        break;
                    case 2:
                        System.out.println("Максимальная цена: " + carService.findMaxPrice());
                        break;
                    case 3:
                        System.out.println("Минимальная цена: " + carService.findMinPrice());
                        break;
                    case 4:
                        PrintListUtils.printCarList(carService.sortByBrand());
                        break;
                    case 5:
                        PrintListUtils.printCarList(carService.sortByPrice());
                        break;
                    case 6:
                        System.out.println("Бренд:");
                        String brand = scanner.nextLine();
                        PrintListUtils.printCarList(carService.findCarByBrand(brand));
                        break;
                    case 7:
                        System.out.println("Модель:");
                        String model = scanner.nextLine();
                        PrintListUtils.printCarList(carService.findCarByModel(model));
                        break;
                    case 8:
                        System.out.println("Введите минимальную цену:");
                        double min = MethodUtils.getDouble();
                        System.out.println("Введите максимальную цену:");
                        double max = MethodUtils.getDouble();
                        PrintListUtils.printCarList(carService.findCarInPriceDiapason(min, max));
                        break;
                    case 9:
                        PrintListUtils.printCarList(carService.findCarWithMinPrice());
                        break;
                    case 10:
                        PrintListUtils.printCarList(carService.findCarWithMaxPrice());
                        break;
                    case 11:
                        selectAction();
                }
            }
        }
    }
}
