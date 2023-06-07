package car;

import java.util.List;
import java.util.stream.Collectors;

public class CarService {
    //сортировка по цене
    public static List<Car> sortPrice(List<Car> list) {

        boolean isSorted = false;

        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < list.size() - 1; i++) {
                if (list.get(i).getPrice() > list.get(i + 1).getPrice()) {
                    Car buf;
                    buf = list.get(i);
                    list.set(i, list.get(i + 1));
                    list.set(i + 1, buf);
                    isSorted = false;
                }
            }
        }
        return list;
    }

    //сортировка по бренду
    public static List<Car> sortBrand(List<Car>list){

        boolean isSorted = false;

        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < list.size() - 1; i++) {
                if (list.get(i).getBrand().toLowerCase().compareTo(list.get(i + 1).getBrand().toLowerCase())>0) {
                    Car buf;
                    buf = list.get(i);
                    list.set(i, list.get(i + 1));
                    list.set(i + 1, buf);
                    isSorted = false;
                }
            }
        }
        return list;
    }

    //максимальная цена
    public static double findMaxPrice(List<Car> list) {
        double max = 0;
        for (Car car : list) {
            if (car.getPrice() > max) {
                max = car.getPrice();
            }
        }
        return max;
    }

    //минимальная цена
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
    public static List<Car> findCarMaxPrice(List<Car> list) {
        double max = 0;
        for (Car car : list) {
            if (car.getPrice() > max) {
                max = car.getPrice();
            }
        }
        double finalMax = max;
        return list.stream()
                .filter(i -> i.getPrice() == finalMax)
                .collect(Collectors.toList());
    }

    //авто с минимальной ценой
    public static List<Car> findCarMinPrice(List<Car> list) {
        double min = list.get(0).getPrice();
        for (Car car : list) {
            if (car.getPrice() < min) {
                min = car.getPrice();
            }
        }
        double finalMin = min;
        return list.stream()
                .filter(i -> i.getPrice() == finalMin)
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