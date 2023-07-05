package org.cars.service.consolemenuservice;

import org.cars.service.carservice.CarService;
import org.cars.service.inoutservice.InOutService;
import org.cars.service.consoleoutputservice.ConsoleOutputServiceImpl;
import org.cars.utils.MethodUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * В классе содержатся методы:
 * void selectAction()throws IOException, SQLException - выбирает место хранения данных (БД, файл .txt, файл .json),
 * из которого они будут извлечены для дальнейших операций с ними.
 * void chooseOperation() throws SQLException - выбирает и реализует операции с данными.
 */

@Component
public class ConsoleMenuService {

    private final Scanner scanner = new Scanner(System.in);

    private CarService carService;
    private final CarService dBCarServiceImpl;
    private final CarService carServiceImpl;

    private InOutService inOutService;
    private final InOutService dBInOutService;
    private final InOutService txtInOutService;
    private final InOutService jsonInOutService;

    String str;
    int actionChoice;
    int operationChoice;

    @Autowired
    public ConsoleMenuService(@Qualifier("dBInOutServiceImpl") InOutService dBInOutService,
                              @Qualifier("txtInOutServiceImpl") InOutService txtInOutService,
                              @Qualifier("jsonInOutServiceImpl") InOutService jsonInOutService,
                              @Qualifier("dBCarServiceImpl") CarService dBCarServiceImpl,
                              @Qualifier("carServiceImpl") CarService carServiceImpl)
    {
        this.dBInOutService = dBInOutService;
        this.txtInOutService = txtInOutService;
        this.jsonInOutService = jsonInOutService;
        this.dBCarServiceImpl = dBCarServiceImpl;
        this.carServiceImpl = carServiceImpl;
    }

    //Выбирает источник данных автомобилей (в DB, файлах .txt, .json - по выбору пользователя).
    public void chooseAction() throws IOException, SQLException {

        System.out.println("\nНажмите:\n" +
                "1 - Для работы с данными, хранящимися в базе данных.\n" +
                "2 - Для работы с данными, хранящимися в  файле .txt.\n" +
                "3 - Для работы с данными, хранящимися в файле .json.\n");

        actionChoice = MethodUtils.getInteger();

        if (actionChoice == 1) {
            str = "Select * From cars";
            inOutService = dBInOutService;
            carService = dBCarServiceImpl;

        } else if (actionChoice == 2) {
            str = "cars";
            inOutService = txtInOutService;
            carService = carServiceImpl;
            carServiceImpl.setList(txtInOutService.getData(str));
//            carService = new CarServiceImpl(txtInOutService.getData(str));

        } else if (actionChoice == 3) {
            str = "cars";
            inOutService = jsonInOutService;
            carService = carServiceImpl;
            carServiceImpl.setList(jsonInOutService.getData(str));
//            carService = new CarServiceImpl(jsonInOutService.getData(str));

        } else {
            System.out.println("Выберите из предложенных вариантов");
            chooseAction();
        }
        chooseOperation();
    }

    public void chooseOperation() throws SQLException, IOException {

        ConsoleOutputServiceImpl consoleOutput = new ConsoleOutputServiceImpl();

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

            operationChoice = MethodUtils.getInteger();

            if (carService != null) {
                switch (operationChoice) {
                    case 0:
                        return;
                    case 1:
                        consoleOutput.printList(inOutService.getData(str));
                        break;
                    case 2:
                        System.out.println("Максимальная цена: " + carService.findMaxPrice());
                        break;
                    case 3:
                        System.out.println("Минимальная цена: " + carService.findMinPrice());
                        break;
                    case 4:
                        consoleOutput.printList(carService.sortByBrand());
                        break;
                    case 5:
                        consoleOutput.printList(carService.sortByPrice());
                        break;
                    case 6:
                        System.out.println("Бренд:");
                        String brand = scanner.nextLine();
                        consoleOutput.printList(carService.findCarByBrand(brand));
                        break;
                    case 7:
                        System.out.println("Модель:");
                        String model = scanner.nextLine();
                        consoleOutput.printList(carService.findCarByModel(model));
                        break;
                    case 8:
                        System.out.println("Введите минимальную цену:");
                        double min = MethodUtils.getDouble();
                        System.out.println("Введите максимальную цену:");
                        double max = MethodUtils.getDouble();
                        consoleOutput.printList(carService.findCarInPriceDiapason(min, max));
                        break;
                    case 9:
                        consoleOutput.printList(carService.findCarWithMinPrice());
                        break;
                    case 10:
                        consoleOutput.printList(carService.findCarWithMaxPrice());
                        break;
                    case 11:
                        chooseAction();
                }
            }
        }
    }
}
