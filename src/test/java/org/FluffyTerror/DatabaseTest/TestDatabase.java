package org.FluffyTerror.DatabaseTest;

import org.FluffyTerror.CustomExpressions.DuplicateException;
import org.FluffyTerror.FirstTest.FirstTest;
import org.junit.jupiter.api.Test;

import java.sql.*;


public class TestDatabase extends FirstTest {

    @Test
    public void testAddNewSql() throws SQLException {
        //параметры для теста
        String name = "Артишок";
        String type = "VEGETABLE";
        boolean isExotic = true;

        app.getHomePage()
                .selectBaseMenu("Песочница")
                .selectSubMenu("Товары")
                .checkOpenFoodPage()
                .selectAddPage()
                .checkOpenAddPage()
                .fillName(name)
                .selectProductType(type)
                .makeExotic()
                .save_fin();

        String jdbcUrl = "jdbc:h2:tcp://localhost:9092/mem:testdb";
        String user = "user";
        String password = "pass";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, user, password)) {
            // Добавление
            String insertQuery = "INSERT INTO FOOD (FOOD_NAME, FOOD_TYPE, FOOD_EXOTIC) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, type);
                preparedStatement.setBoolean(3, isExotic);
                preparedStatement.executeUpdate();
            }
            System.out.println("________________________________________");
            // Проверка записи
            String selectQuery = "SELECT * FROM FOOD WHERE FOOD_NAME = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setString(1, name);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("FOOD_ID");
                        String foodName = resultSet.getString("FOOD_NAME");
                        String foodType = resultSet.getString("FOOD_TYPE");
                        boolean exotic = resultSet.getBoolean("FOOD_EXOTIC");
                        System.out.printf("Продукт: %d, %s, %s, %b%n", id, foodName, foodType, exotic);
                    }
                }
            }
            System.out.println("________________________________________");
            // Удаление записи
            String deleteQuery = "DELETE FROM FOOD WHERE FOOD_NAME = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setString(1, name);
                int rowsAffected = preparedStatement.executeUpdate();
                System.out.printf("Удалено строк: %d%n", rowsAffected);
            }
            System.out.println("________________________________________");
            // Вывод оставшихся записей
            String getAllQuery = "SELECT * FROM FOOD";
            try (PreparedStatement preparedStatement = connection.prepareStatement(getAllQuery);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                System.out.println("Оставшиеся продукты в БД:");
                while (resultSet.next()) {
                    int id = resultSet.getInt("FOOD_ID");
                    String foodName = resultSet.getString("FOOD_NAME");
                    String foodType = resultSet.getString("FOOD_TYPE");
                    boolean exotic = resultSet.getBoolean("FOOD_EXOTIC");
                    System.out.printf("%d, %s, %s, %b%n", id, foodName, foodType, exotic);
                }
            }
        }
    }

    @Test
    public void testExistingSql() throws SQLException {
        //параметры для теста
        String name = "Яблоко";
        String type = "FRUIT";

        app.getHomePage()
                .selectBaseMenu("Песочница")
                .selectSubMenu("Товары")
                .checkOpenFoodPage()
                .selectAddPage()
                .checkOpenAddPage()
                .fillName(name)
                .selectProductType(type)
                .save_fin();

        String jdbcUrl = "jdbc:h2:tcp://localhost:9092/mem:testdb";
        String user = "user";
        String password = "pass";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, user, password)) {
            // Проверка на дубли
            String duplicateCheckQuery = "SELECT COUNT(*) AS count FROM FOOD WHERE FOOD_NAME = ? AND FOOD_TYPE = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(duplicateCheckQuery)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, type);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int count = resultSet.getInt("count");
                        if (count > 1) {
                            throw new DuplicateException(
                                    String.format("Найдено %d дублирующихся записей для '%s' с типом '%s'", count, name, type)
                            );
                        } else {
                            System.out.println("Дублирующихся записей не найдено.");
                        }
                    }
                } catch (DuplicateException e) {
                    System.err.println("Ошибка: " + e.getMessage());
                }
            }
            System.out.println("________________________________________");
            // Удаление всех повторяющихся строк
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

            System.out.println("________________________________________");

            // Вывод оставшихся записей
            String getAllQuery = "SELECT * FROM FOOD";
            try (PreparedStatement preparedStatement = connection.prepareStatement(getAllQuery);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                System.out.println("Оставшиеся продукты в БД:");
                while (resultSet.next()) {
                    int id = resultSet.getInt("FOOD_ID");
                    String foodName = resultSet.getString("FOOD_NAME");
                    String foodType = resultSet.getString("FOOD_TYPE");
                    boolean exotic = resultSet.getBoolean("FOOD_EXOTIC");
                    System.out.printf("%d, %s, %s, %b%n", id, foodName, foodType, exotic);
                }
            }
        }
    }


}