package method;

import car.Car;

import java.util.List;

public class PrintList {

    public static void printCar(Car car) {
        System.out.println(
                "Brand: " + car.getBrand() + ", " +
                        "Model: " + car.getModel() + ", " +
                        "Year of manufacture: " + car.getYear() + ", " +
                        "Price: " + car.getPrice() + ".");
    }

    public static void printCarList(List<Car> carList) {
        if (carList.isEmpty()) {
            System.out.println("List is empty");
        } else {
            carList.forEach(PrintList::printCar);
        }
    }
}

