package org.cars;

import org.cars.service.consolemenuservice.ConsoleMenuService;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {

        ConsoleMenuService consoleMenuService = new ConsoleMenuService();
        consoleMenuService.chooseAction();

    }
}