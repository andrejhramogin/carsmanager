package org.cars;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws IOException {

        String url = "jdbc:postgresql://localhost:5432/postgres";
        String name = "postgres";
        String password = "password";

        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("Select * From user WHERE id > 2 AND id < 10");
            while (rs.next()) {
                System.out.println("Номер в выборке: " + rs.getRow() + "Номер в базе: " + rs.getInt("id" +
                        "Имя: " + rs.getString("firstname")));
                connection.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}