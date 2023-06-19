package org.cars;

import org.cars.service.CarServicePostgres;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        CarServicePostgres carServicePostgres = new CarServicePostgres();


         carServicePostgres.selectAction();

    }
}