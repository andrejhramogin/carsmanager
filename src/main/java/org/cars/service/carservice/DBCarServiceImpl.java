package org.cars.service.carservice;

import org.cars.model.Car;
import org.cars.service.dbconnectionservice.DBConnectionService;
import org.cars.service.inoutservice.DBInOutServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.CarRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Имплементирует интерфейс CarService
 * Переопределяет методы интерфейса CarService
 */
@Component("dBCarServiceImpl")
public class DBCarServiceImpl implements CarService {

    private static final Logger logger = LoggerFactory.getLogger(DBCarServiceImpl.class);
    private DBConnectionService dbConnection = DBConnectionService.getInstance();
    private DBInOutServiceImpl dbInOutService = new DBInOutServiceImpl();
    private Connection connection = dbConnection.newConnection();
    private Statement statement = connection.createStatement();
    private ResultSet rs;

    @Autowired
    private CarRepository carRepository;

    public DBCarServiceImpl() throws SQLException {
    }

    //Сортирует по цене в порядке возрастания
    @Override
    public List<Car> sortByPrice() throws SQLException {
        return dbInOutService.getData("SELECT * FROM cars ORDER BY price");
    }

    //Сортирует по названию бренда в порядке возрастания
    @Override
    public List<Car> sortByBrand() throws SQLException {
        return dbInOutService.getData("SELECT * FROM cars ORDER BY brand");
    }

    //Находит максимальную цену
    @Override
    public double findMaxPrice() {
        double max = 0;
        try {
            rs = statement.executeQuery("Select max (price) from cars");
            while (rs.next()) {
                max = rs.getDouble("max");
            }
            dbConnection.closeConnection();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return max;
    }

    //Находит минимальную цену
    @Override
    public double findMinPrice() {
        double min = 0;
        try {
            rs = statement.executeQuery("Select min (price) from cars");
            while (rs.next()) {
                min = rs.getDouble("min");
            }
            dbConnection.closeConnection();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return min;
    }

    //Находит все авто с максимальной ценой
    @Override
    public List<Car> findCarWithMaxPrice() throws SQLException {
        return dbInOutService.getData("SELECT * from cars WHERE price = ( select  MAX(price) FROM cars)");
    }

    //Находит все авто с минимальной ценой
    @Override
    public List<Car> findCarWithMinPrice() throws SQLException {
        return dbInOutService.getData("SELECT * from cars WHERE price = ( select  MIN(price) FROM cars)");
    }

    //Находит все авто по названию бренда
    @Override
    public List<Car> findCarByBrand(String brand) throws SQLException {
        return dbInOutService.getData("SELECT * FROM cars WHERE lower (brand) = lower ('" + brand + "')");
    }

    //Находит все авто по названию модели
    @Override
    public List<Car> findCarByModel(String model) throws SQLException {
        return dbInOutService.getData("SELECT * FROM cars WHERE lower (model) = lower ('" + model + "')");
    }

    //Находит все авто в задаваемом диапазоне цен
    @Override
    public List<Car> findCarInPriceDiapason(double min, double max) throws SQLException {
        return dbInOutService.getData("SELECT * FROM cars WHERE price between " + min + " and " + max + " order by price");
    }

    //Выводит весь список машин
    @Override
    public List<Car> getAllCars() throws SQLException {
        List<Car> list = new ArrayList<>();
        rs = statement.executeQuery("SELECT * FROM cars ORDER by id");
        try {
            while (rs.next()) {
                Car car = new Car();
                car.setId(rs.getInt("id"));
                car.setBrand(rs.getString("brand"));
                car.setModel(rs.getString("model"));
                car.setYear(rs.getInt("year"));
                car.setPrice(rs.getDouble("price"));
                list.add(car);
            }
            dbConnection.closeConnection();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    //удаление авто по id
    @Override
    public void deleteCarById(int id) throws SQLException {
        try {
            statement.executeUpdate("DELETE FROM cars WHERE id = " + id);

            dbConnection.closeConnection();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("Строка с номером ID = " + id + " удалена из таблицы");
    }

    //создаёт новое авто в таблице "cars", возвращает его по id, которое возвращается через "RETURNING id".
    public entity.Car createNewCar(entity.Car car) throws SQLException {
        return carRepository.save(car);
    }

    //находит в БД и возвращает авто по id.
    @Override
    public Car findCarById(int id) throws SQLException {
        Car car = new Car();
        rs = statement.executeQuery("SELECT * FROM cars WHERE id = " + id);
        try {
            while (rs.next()) {
                car.setId(rs.getInt("id"));
                car.setBrand(rs.getString("brand"));
                car.setModel(rs.getString("model"));
                car.setYear(rs.getInt("year"));
                car.setPrice(rs.getDouble("price"));
            }
            dbConnection.closeConnection();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return car;
    }

    //обновляет поля и возвращает обновленную car
    @Override
    public Car update(Car car, int id) throws SQLException {
        int idReturn = 0;
        try {
            rs = statement.executeQuery(String.format("UPDATE cars SET brand = '%s',  " +
                            "model = '%s', year = '%d', price = '%f' WHERE id = %d RETURNING id",
                    car.getBrand(), car.getModel(), car.getYear(), car.getPrice(), id));
            while (rs.next()) {
                idReturn = rs.getInt("id");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return findCarById(idReturn);
    }

    //Выводит список машин по задаваемым параметрам (@RequestParam ("sortBy") String sortBy,
    //                               @RequestParam ("sortDirection") String sortDirection,
    //                               @RequestParam ("filter") String filter

    @Override
    public List<Car> getByParam(String sortBy, String sortDirection, String filter) throws SQLException {
        List<Car> list = new ArrayList<>();
        String query;

        if (Objects.equals(filter, "")) {
            query = "SELECT * FROM cars ORDER BY " + sortBy;
        } else {
            String[] queryArr = filter.split("\\.");
            String equality = null;
            if (queryArr[0].equals("not_equals")) {
                equality = "!=";
            } else if (queryArr[0].equals("equals")) {
                equality = "=";
            }
            String filterParam = queryArr[1];
            String value = queryArr[2];

            query = "SELECT * FROM cars WHERE " + filterParam + " " + equality + " " + value + " ORDER BY " +
                    sortBy + " " + sortDirection;
        }
        rs = statement.executeQuery(query);
        try {
            while (rs.next()) {
                Car car = new Car();
                car.setId(rs.getInt("id"));
                car.setBrand(rs.getString("brand"));
                car.setModel(rs.getString("model"));
                car.setYear(rs.getInt("year"));
                car.setPrice(rs.getDouble("price"));
                list.add(car);
            }
            dbConnection.closeConnection();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
}