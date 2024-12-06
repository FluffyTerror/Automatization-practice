package org.FluffyTerror.Cucumber.Hooks;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import org.FluffyTerror.managers.InitManager;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Hooks {

    private static Connection connection;

    @Before(order = 1)
    public void setupDriver() {
        InitManager.getDriver();
        InitManager.initFramework();
    }

    @Before(order = 2)
    public void connectDb() throws SQLException {
        String jdbcUrl = "jdbc:h2:tcp://localhost:9092/mem:testdb";
        String user = "user";
        String password = "pass";
        try {
            connection = DriverManager.getConnection(jdbcUrl, user, password);
            System.out.println("Соединение с бд успешно");
        } catch (SQLException e) {
            System.err.println("Не удалось соединиться с бд: " + e.getMessage());
            throw e;
        }
    }

    @After(order = 1)
    public void closeDbConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Соединение с бд закрыто");
            } catch (SQLException e) {
                System.err.println("\"Не удалось соединиться с бд: " + e.getMessage());
            }
        }
    }
    @AfterAll
    public static void tearDownDriver() {
        System.out.println("Закрываем веб-драйвер после всех тестов...");
        InitManager.quitFramework();
    }

    public static Connection getConnection() {
        return connection;
    }
}
