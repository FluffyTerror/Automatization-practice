package org.FluffyTerror.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomePage extends BasePage{

    @FindBy(xpath = "//li[@class ='nav-item dropdown']")
    private List<WebElement> listBaseMenu;

    @FindBy(xpath = "//a[@href ='/food']")
    private List<WebElement> listSubMenu;

    @FindBy(xpath = "//a[@id ='reset']")
    private WebElement ResetButton;

    /**
     * Функция наведения мыши на любой пункт меню
     *
     * @param nameBaseMenu - наименование меню
     * @return HomePage - т.е. остаемся на этой странице
     */
    public HomePage selectBaseMenu(String nameBaseMenu) {
        for (WebElement menuItem : listBaseMenu) {
            if (menuItem.getText().trim().equalsIgnoreCase(nameBaseMenu)) {
                sleep(3500);

                waitUtilElementToBeClickable(menuItem).click();
                return this;
            }
        }
        Assertions.fail("Меню '" + nameBaseMenu + "' не было найдено на стартовой странице!");
        return this;
    }
    /**
     * Функция клика на любое подменю
     *
     * @param nameSubMenu - наименование подменю
     * @return InsurancePage - т.е. переходим на страницу {@link HomePage}
     */
    public FoodPage selectSubMenu(String nameSubMenu) {
        for (WebElement menuItem : listSubMenu) {
            if (menuItem.getText().equalsIgnoreCase(nameSubMenu)) {
                sleep(1500);
                waitUtilElementToBeClickable(menuItem).click();

                return pageManager.getFoodPage().checkOpenFoodPage();
            }
        }
        Assertions.fail("Подменю '" + nameSubMenu + "' не было найдено на стартовой странице!");
        return pageManager.getFoodPage();
    }

    /**
     * Выполняет сброс данных, кликая по кнопке "Reset", и возвращает страницу "FoodPage".
     *
     * @return FoodPage - возвращает объект FoodPage для работы после сброса данных.
     */
    public FoodPage selectReset() {
        waitUtilElementToBeClickable(ResetButton).click();
        sleep(1500);
        return pageManager.getFoodPage();
    }

}
