package method;

import car.Car;

import java.util.List;

public class Sort {

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
}
