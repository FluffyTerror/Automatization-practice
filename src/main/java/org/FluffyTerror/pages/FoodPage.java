package org.FluffyTerror.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FoodPage extends BasePage {

    @FindBy(xpath = "//html/body/div/div[2]/div/h5")
    private WebElement foodTitle;

    @FindBy(xpath = "//button[@data-toggle ='modal']")
    private WebElement AddButton;

    public FoodPage checkOpenFoodPage() {
        waitUtilElementToBeVisible(foodTitle);
        Assertions.assertEquals("Список товаров", foodTitle.getText(), "Заголовок отсутствует/не соответствует требуемому");
        return this;
    }

    public AddPage selectAddPage() {
        waitUtilElementToBeClickable(AddButton).click();
        pause(1.5);
        return pageManager.getAddPage();
    }
}
