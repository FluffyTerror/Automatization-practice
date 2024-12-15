package org.FluffyTerror.Cucumber.Steps;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojos.ObjectPojo;

import java.util.List;

import static io.restassured.RestAssured.given;
public class RESTSteps {
    private String jsessionId;
    private List<ObjectPojo> objects;

    @Дано("система сброшена")
    public void resetSystem() {
        RestAssured.baseURI = "https://qualit.applineselenoid.fvds.ru";
        given()
                .basePath("/api/data/reset")
                .when()
                .post();
    }

    @Когда("я добавляю новый элемент со следующими данными:")
    public void addNewItem(io.cucumber.datatable.DataTable dataTable) {
        var data = dataTable.asMaps().get(0); // Берем первую строку данных
        String requestBody = String.format("""
            {
                "name": "%s",
                "type": "%s",
                "exotic": %s
            }
        """, data.get("name"), data.get("type"), data.get("exotic"));

        Response response = given()
                .basePath("/api/food")
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract()
                .response();

        jsessionId = response.getCookie("JSESSIONID");
    }

    @Когда("список товаров должен содержать {int} элементов")
    public void verifyItemListSize(int expectedSize) {
        objects = given()
                .basePath("/api/food")
                .contentType("application/json")
                .cookie("JSESSIONID", jsessionId)
                .when()
                .get("/")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath().getList("", ObjectPojo.class);

        assertEquals(expectedSize, objects.size(), "Unexpected item list size");
    }
    @И("список должен включать {string} типа {string}, отмеченный как {string}")
    public void verifyItemInList(String name, String type, String exoticFlag) {
        boolean isExotic = exoticFlag.equalsIgnoreCase("экзотический");
        boolean itemExists = objects.stream().anyMatch(obj ->
                obj.getName().equals(name) &&
                        obj.getType().equals(type) &&
                        obj.isExotic() == isExotic
        );

        assertTrue(
                itemExists,
                String.format("Элемент '%s' типа '%s', отмеченный как '%s', не найден в списке", name, type, exoticFlag)
        );
    }
    @И("список должен включать {string} типа {string}")
    public void verifyItemInListWithoutExoticFlag(String name, String type) {
        boolean itemExists = objects.stream().anyMatch(obj ->
                obj.getName().equals(name) &&
                        obj.getType().equals(type)
        );

        assertTrue(
                itemExists,
                String.format("Элемент '%s' типа '%s' не найден в списке", name, type)
        );
    }

}
