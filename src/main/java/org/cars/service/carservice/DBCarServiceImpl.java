package org.cars.service.carservice;

import org.cars.entity.Car;
import org.cars.service.dbconnectionservice.DBConnectionService;
import org.cars.service.inoutservice.DBInOutServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.cars.repository.CarJpaRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
    private CarJpaRepository carJpaRepository;

    public DBCarServiceImpl() throws SQLException {
    }

    //удаление авто по id
    @Override
    public void deleteCarById(int id) throws SQLException {
        carJpaRepository.deleteById(id);
    }

    //создаёт новое авто в таблице "cars" и возвращает его.
    public Car createNewCar(Car car) {
        return carJpaRepository.save(car);
    }

    //находит в БД и возвращает авто по id.
    @Override
    public Optional<Car> findCarById(int id) {
        return carJpaRepository.findById(id);
    }

    //обновляет поля и возвращает обновленную car
    @Override
    public Car update(Car car, int id) throws SQLException {
        Car updatedCar = carJpaRepository.getReferenceById(id);
        car.setId(updatedCar.getId());
        return  carJpaRepository.save(car); //? изменяет авто, но не возвращает. Ошибка 500.
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