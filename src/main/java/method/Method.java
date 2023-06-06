package method;

import car.Car;

import java.util.List;
import java.util.stream.Collectors;

public class Method {

    public static double findMaxPrice(List<Car> list) {
        double max = 0;
        for (Car car : list) {
            if (car.getPrice() > max) {
                max = car.getPrice();
            }
        }
        return max;
    }

    public static double findMinPrice(List<Car> list) {
        double min = list.get(0).getPrice();
        for (Car car : list) {
            if (car.getPrice() < min) {
                min = car.getPrice();
            }
        }
        return min;
    }

    //авто с самой большой ценой
    public static List<Car> createCarListMaxPrice(List<Car> list, double maxPrice) {
        return list.stream()
                .filter(i -> i.getPrice() == maxPrice)
                .collect(Collectors.toList());
    }

    //авто с минимальной ценой
    public static List<Car> createCarListMinPrice(List<Car> list, double minPrice) {
        return list.stream()
                .filter(i -> i.getPrice() == minPrice)
                .collect(Collectors.toList());
    }

    //список по бренду
    public static List<Car> createCarListByBrand(List<Car> list, String brand) {
        return list.stream()
                .filter(i -> i.getBrand().equalsIgnoreCase(brand))
                .collect(Collectors.toList());
    }

    //список по модели
    public static List<Car> createCarListByModel(List<Car> list, String model) {
        return list.stream()
                .filter(i -> i.getModel().equalsIgnoreCase(model))
                .collect(Collectors.toList());
    }


    //список по диапазону цен
    public static List<Car> createCarListInPriceDiapason(List<Car> list, double min, double max) {
        return list.stream()
                .filter(i -> i.getPrice() >= min && i.getPrice() <= max)
                .collect(Collectors.toList());
    }
}