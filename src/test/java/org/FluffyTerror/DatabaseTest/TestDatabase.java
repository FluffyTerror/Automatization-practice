package org.FluffyTerror.DatabaseTest;

import org.FluffyTerror.CustomExpressions.DuplicateException;
import org.FluffyTerror.FirstTest.FirstTest;
import org.junit.jupiter.api.Test;

import java.sql.*;


public class TestDatabase extends FirstTest {

    @Test
    public void testAddNewSql() throws SQLException {
        // Шаги из прошлых практик
        app.getHomePage()
                .selectBaseMenu("Песочница")
                .selectSubMenu("Товары")
                .checkOpenFoodPage()
                .selectAddPage()
                .checkOpenAddPage()
                .fillName("Артишок")
                .selectProductType("VEGETABLE")
                .makeExotic()
                .save_fin();

        // Выносим как отдельные переменные для удобства
        String jdbcUrl = "jdbc:h2:tcp://localhost:9092/mem:testdb";
        String user = "user";
        String password = "pass";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, user, password);
             Statement statement = connection.createStatement())
        {
            // Проверка добавления записи
            String query = "SELECT * FROM FOOD WHERE FOOD_NAME = 'Артишок'";
            try (ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("FOOD_ID");
                    String name = resultSet.getString("FOOD_NAME");
                    String type = resultSet.getString("FOOD_TYPE");
                    boolean exotic = resultSet.getBoolean("FOOD_EXOTIC");
                    System.out.printf("Нашли добавленный продукт: %d, %s, %s, %b%n", id, name, type, exotic);
                }
            }
            // Удаление записи
            String deleteQuery = "DELETE FROM FOOD WHERE FOOD_NAME = 'Артишок'";
            int rowsAffected = statement.executeUpdate(deleteQuery);
            System.out.printf("Удаленных строк: %d%n", rowsAffected);

            //вывод исходной бд
            String getAllQuery = "SELECT * FROM FOOD";
            try (ResultSet resultSet = statement.executeQuery(getAllQuery)) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("FOOD_ID");
                    String name = resultSet.getString("FOOD_NAME");
                    String type = resultSet.getString("FOOD_TYPE");
                    boolean exotic = resultSet.getBoolean("FOOD_EXOTIC");
                    System.out.printf("В исходной бд остались продукты: %d, %s, %s, %b%n", id, name, type, exotic);
                }
            }
        }
        System.out.println("________________________________________");
    }

    /**
     * Обращаю внимание на то, что данный тест немного отличается от указанного в тест кейсе,
     * а именно тем, что я тут вызвал свою собственную ошибку которую я хотел бы видеть при нахождении дубликатов
     * надеюсь это не сильно противоречит заданию
     * @throws SQLException
     */
    @Test
    public void testExistingSql() throws SQLException {
        // Шаги UI-тестирования
        app.getHomePage()
                .selectBaseMenu("Песочница")
                .selectSubMenu("Товары")
                .checkOpenFoodPage()
                .selectAddPage()
                .checkOpenAddPage()
                .fillName("Яблоко")
                .selectProductType("FRUIT")
                .save_fin();

        // Работа с базой данных
        String jdbcUrl = "jdbc:h2:tcp://localhost:9092/mem:testdb";
        String user = "user";
        String password = "pass";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, user, password);
             Statement statement = connection.createStatement()) {

            // Проверка наличия дублей
            try {
                String duplicateCheckQuery = "SELECT COUNT(*) AS count FROM FOOD WHERE FOOD_NAME = 'Яблоко'";
                try (ResultSet resultSet = statement.executeQuery(duplicateCheckQuery)) {
                    if (resultSet.next()) {
                        int count = resultSet.getInt("count");
                        if (count > 1) {
                            throw new DuplicateException("Найдено " + count + " дублирующихся записей для 'Яблоко'");
                        } else {
                            System.out.println("Дублирующихся записей не найдено.");
                        }
                    }
                }
            } catch (DuplicateException e) {
                System.err.println("Ошибка: " + e.getMessage());
            }
            System.out.println("________________________________________");

            // Проверка добавления записи
            String query = "SELECT * FROM FOOD WHERE FOOD_NAME = 'Яблоко'";
            try (ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("FOOD_ID");
                    String name = resultSet.getString("FOOD_NAME");
                    String type = resultSet.getString("FOOD_TYPE");
                    boolean exotic = resultSet.getBoolean("FOOD_EXOTIC");
                    System.out.printf("Нашли добавленный продукт: %d, %s, %s, %b%n", id, name, type, exotic);
                }
            }
            System.out.println("________________________________________");

            // Удаление записи
            String deleteQuery = "DELETE FROM FOOD WHERE FOOD_ID = (SELECT MAX(FOOD_ID) FROM FOOD WHERE FOOD_NAME = 'Яблоко')";
            int rowsAffected = statement.executeUpdate(deleteQuery);
            System.out.printf("Удаленных строк: %d%n", rowsAffected);
            System.out.println("________________________________________");

            // Вывод оставшихся записей
            String getAllQuery = "SELECT * FROM FOOD";
            try (ResultSet resultSet = statement.executeQuery(getAllQuery)) {
                System.out.println("В исходной бд остались продукты:");
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
}

