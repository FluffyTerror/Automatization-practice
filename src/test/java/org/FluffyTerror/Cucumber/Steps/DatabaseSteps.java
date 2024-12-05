package org.FluffyTerror.Cucumber.Steps;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;

import java.sql.*;

import org.FluffyTerror.Cucumber.Hooks.Hooks;


public class DatabaseSteps {

    private Connection connection = Hooks.getConnection();

    @Тогда("в таблице {string} есть элемент с именем {string}")
    public void verifyElementInTable(String tableName, String name) throws SQLException {
        {
            String query = "SELECT * FROM " + tableName + " WHERE FOOD_NAME = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, name);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    System.out.println("в исходной таблице остались товары:");
                    while (resultSet.next()) {
                        int id = resultSet.getInt("FOOD_ID");
                        String namee = resultSet.getString("FOOD_NAME");
                        String type = resultSet.getString("FOOD_TYPE");
                        boolean exotic = resultSet.getBoolean("FOOD_EXOTIC");
                        System.out.printf("В исходной бд остались продукты: %d, %s, %s, %b%n", id, namee, type, exotic);
                    }
                }
            }
            System.out.println("________________________________________");
        }
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
        {
            String query = "SELECT * FROM " + tableName;
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    System.out.println("в исходной таблице остались товары:");
                    while (resultSet.next()) {
                        int id = resultSet.getInt("FOOD_ID");
                        String namee = resultSet.getString("FOOD_NAME");
                        String type = resultSet.getString("FOOD_TYPE");
                        boolean exotic = resultSet.getBoolean("FOOD_EXOTIC");
                        System.out.printf("В исходной бд остались продукты: %d, %s, %s, %b%n", id, namee, type, exotic);
                    }
                }
            }
            System.out.println("________________________________________");
        }
    }

    @Когда("выполнена проверка на дубли для продукта {string} с типом {string}")
    public void checkForDuplicates(String name, String type) throws SQLException {
        String query = "SELECT COUNT(*) AS count FROM FOOD WHERE FOOD_NAME = ? AND FOOD_TYPE = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, type);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt("count");
                    if (count > 1) {
                        throw new SQLException("Найдено " + count + " дублирующихся записей для '"
                                + name + "' с типом '" + type + "'");
                    } else {
                        System.out.println("Дублирующихся записей не найдено.");
                    }
                }
            }
        }
    }

    @Когда("удалены все повторяющиеся записи для {string} из базы данных")
    public void deleteDuplicates(String name) throws SQLException {
        String deleteDuplicatesQuery = """
                DELETE FROM FOOD
                WHERE FOOD_ID NOT IN (
                SELECT MIN(FOOD_ID)
                FROM FOOD
                WHERE FOOD_NAME = ?
                GROUP BY FOOD_NAME, FOOD_TYPE, FOOD_EXOTIC
                )
                AND FOOD_NAME = ?
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteDuplicatesQuery)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, name);
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.printf("Удалено строк: %d%n", rowsAffected);
        }
    }

    @Тогда("оставшиеся записи в базе данных должны быть выведены на экран")
    public void verifyRemainingRecords() throws SQLException {
        String getAllQuery = "SELECT * FROM FOOD";
        try (PreparedStatement preparedStatement = connection.prepareStatement(getAllQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            System.out.println("Оставшиеся продукты в БД:");
            while (resultSet.next()) {
                int id = resultSet.getInt("FOOD_ID");
                String name = resultSet.getString("FOOD_NAME");
                String type = resultSet.getString("FOOD_TYPE");
                boolean exotic = resultSet.getBoolean("FOOD_EXOTIC");
                System.out.printf("%d, %s, %s, %b%n", id, name, type, exotic);
            }
        }
    }
}



