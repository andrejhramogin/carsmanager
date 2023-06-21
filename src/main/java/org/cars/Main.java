package org.cars;

import org.cars.service.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        ConsoleMenuService consoleMenuService = new ConsoleMenuService();
        consoleMenuService.selectAction();

    }
}