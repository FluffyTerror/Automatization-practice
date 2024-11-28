package org.FluffyTerror.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Класс для выполнения общих действий на страницах
 */

public class FoodPage {

    @FindBy(xpath = "/html/body/div/div[2]/div/h5")
    private WebElement title;

    /**
     * Проверка открытия страницы, путём проверки title страницы
     *
     * @return InsurancePage - т.е. остаемся на этой странице
     */
    public FoodPage checkOpenFoodPage() {
        Assert.assertEquals("Заголовок отсутствует/не соответствует требуемому",
                "Страхование путешественников", title.getText());
        return this;
    }

}
