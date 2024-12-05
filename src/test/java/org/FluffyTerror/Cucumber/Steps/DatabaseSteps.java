package org.FluffyTerror.Cucumber.Steps;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;

import java.sql.*;

import org.FluffyTerror.Cucumber.Hooks.Hooks;



public class DatabaseSteps {

    // Используем подключение из Hooks
    private Connection connection = Hooks.getConnection();

    @Тогда("в таблице {string} есть элемент с именем {string}")
    public void verifyElementInTable(String tableName, String name) throws SQLException {
        {
            Statement statement = connection.createStatement();
            // Проверка добавления записи
            String query = "SELECT * FROM FOOD WHERE FOOD_NAME = 'Артишок'";
            try (ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("FOOD_ID");
                    String namee = resultSet.getString("FOOD_NAME");
                    String type = resultSet.getString("FOOD_TYPE");
                    boolean exotic = resultSet.getBoolean("FOOD_EXOTIC");
                    System.out.printf("Нашли добавленный продукт: %d, %s, %s, %b%n", id, namee, type, exotic);
                }
            }
            //вывод исходной бд
            String getAllQuery = "SELECT * FROM FOOD";
            try (ResultSet resultSet = statement.executeQuery(getAllQuery)) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("FOOD_ID");
                    String namee = resultSet.getString("FOOD_NAME");
                    String type = resultSet.getString("FOOD_TYPE");
                    boolean exotic = resultSet.getBoolean("FOOD_EXOTIC");
                    System.out.printf("В бд теперь продукты: %d, %s, %s, %b%n", id, namee, type, exotic);
                }
            }
        }
        System.out.println("________________________________________");
    }

    @Когда("выполнено удаление элемента с именем {string} из таблицы {string}")
    public void deleteElementFromTable(String name, String tableName) throws SQLException {
        String query = "DELETE FROM " + tableName + " WHERE FOOD_NAME = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            int rowsAffected = preparedStatement.executeUpdate();
            Assertions.assertTrue(rowsAffected > 0, "Элемент с именем '" + name + "' не был удалён.");
            System.out.printf("Элемент '%s' удалён из таблицы '%s'.%n", name, tableName);
        }
    }

    @Тогда("в таблице {string} нет элемента с именем {string}")
    public void verifyElementNotInTable(String tableName, String name) throws SQLException {
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM " + tableName + " WHERE FOOD_NAME = ?";
        //вывод исходной бд
        String getAllQuery = "SELECT * FROM FOOD";
        try (ResultSet resultSet = statement.executeQuery(getAllQuery)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("FOOD_ID");
                String namee = resultSet.getString("FOOD_NAME");
                String type = resultSet.getString("FOOD_TYPE");
                boolean exotic = resultSet.getBoolean("FOOD_EXOTIC");
                System.out.printf("В бд остались продукты: %d, %s, %s, %b%n", id, namee, type, exotic);
            }
        }
        System.out.println("________________________________________");
    }
}


