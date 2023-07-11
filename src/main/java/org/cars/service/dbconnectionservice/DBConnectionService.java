package org.cars.service.dbconnectionservice;

import org.postgresql.ds.PGPoolingDataSource;
import org.springframework.stereotype.Component;

import java.sql.*;

/**
 * connection pool
 * Singleton
 * определяет параметры подключения
 * Создает подключение к DB
 * Закрывает подключение к DB
 */

@Component("dBConnectionService")
public class DBConnectionService {

    private static DBConnectionService instance;
    private static PGPoolingDataSource ds;

    private DBConnectionService() {
    }

    /**
     * определяет параметры создания подключения
     */

    public void createConnection() {
        ds = new PGPoolingDataSource();
        ds.setServerName("localhost");
        ds.setDatabaseName("postgres");
        ds.setUser("postgres");
        ds.setPassword("password");
        ds.setMaxConnections(100);
        ds.setInitialConnections(20);
    }

    /**
     * Создает единственный экземпляр класса
     */

    public static DBConnectionService getInstance() {
        if (instance == null) {
            instance = new DBConnectionService();
        }
        return instance;
    }

    /**
     * создает новое подключение
     */
    public Connection newConnection() throws SQLException {
        if (ds == null) {
            createConnection();
        }
        return ds.getConnection();
    }

    /**
     * закрывает подключение
     */
    public void closeConnection() throws SQLException {
        if (ds.getConnection() != null) {
            try {
                ds.getConnection().close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}