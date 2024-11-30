package org.FluffyTerror.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FoodPage extends BasePage {

    @FindBy(xpath = "//html/body/div/div[2]/div/h5")
    private WebElement foodTitle;

    @FindBy(xpath = "//button[@data-toggle ='modal']")
    private WebElement AddButton;

    /**
     * Проверяет, что страница "FoodPage" открыта, ожидая видимости заголовка.
     *
     * @return FoodPage - возвращает текущий объект FoodPage.
     */
    public FoodPage checkOpenFoodPage() {
        waitUtilElementToBeVisible(foodTitle);
        Assertions.assertEquals("Список товаров", foodTitle.getText(),
                "Заголовок отсутствует/не соответствует требуемому");
        return this;
    }

    /**
     * Переходит на страницу добавления товаров, кликая на кнопку "Add".
     *
     * @return AddPage - возвращает объект AddPage для работы с новой страницей.
     */
    public AddPage selectAddPage() {
        waitUtilElementToBeClickable(AddButton).click();
        sleep(1500);
        return pageManager.getAddPage();
    }
}