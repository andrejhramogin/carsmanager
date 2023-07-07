package org.cars;

import org.cars.controller.CarsController;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {
        ConfigurableApplicationContext context = SpringApplication.run(CarsController.class, args);
    }
}