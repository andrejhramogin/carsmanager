package org.cars;

import org.cars.service.consolemenuservice.ConsoleMenuService;
import org.cars.service.dbservice.DBService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        ConsoleMenuService consoleMenuService = context.getBean("consoleMenuService", ConsoleMenuService.class);

        consoleMenuService.chooseAction();

        context.close();

    }
}