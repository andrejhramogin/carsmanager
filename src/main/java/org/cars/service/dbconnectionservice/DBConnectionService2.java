package org.cars.service.dbconnectionservice;

import org.postgresql.ds.PGPoolingDataSource;

import java.sql.*;

/**
 * connection pool
 * Singleton
 * определяет параметры подключения
 * Создает подключение к DB
 * Закрывает подключение к DB
 */


public class DBConnectionService2 {

    private DBConnectionService2 instance;
    private PGPoolingDataSource ds;

    public DBConnectionService2(DBConnectionService2 instance) {
        this.instance = instance;
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

//    public DBConnectionService2 getInstance() {
//        if (instance == null) {
//            instance = new DBConnectionService2();
//        }
//        return instance;
//    }

    public void setInstance(DBConnectionService2 instance) {
        this.instance = instance;
    }

    public PGPoolingDataSource getDs() {
        return ds;
    }

    public void setDs(PGPoolingDataSource ds) {
        this.ds = ds;
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
