package org.FluffyTerror.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.junit.Assert;


import java.util.List;

public class HomePage extends BasePage {
    @FindBy(xpath = "//*[@id='navbarSupportedContent']/ul/li[2]")
    private List<WebElement> listBaseMenu;

    @FindBy(xpath = "//*[@id=\"navbarSupportedContent\"]/ul/li[2]/div/a[1]")
    private List<WebElement> listSubMenu;

    public HomePage selectBaseMenu(String nameBaseMenu) {
        for (WebElement menuItem : listBaseMenu) {
            if (menuItem.getText().trim().equalsIgnoreCase(nameBaseMenu)) {
                waitUtilElementToBeClickable(menuItem).click();
                return this;
            }
        }
        Assert.fail("Меню '" + nameBaseMenu + "' не было найдено на стартовой странице!");
        return this;
    }

    public FoodPage selectSubMenu(String nameSubMenu) {
        for (WebElement menuItem : listSubMenu) {
            if (menuItem.getText().equalsIgnoreCase(nameSubMenu)) {
                waitUtilElementToBeClickable(menuItem).click();
                return pageManager.getFoodPage().checkOpenFoodPage();
            }
        }
        Assert.fail("Подменю '" + nameSubMenu + "' не было найдено на стартовой странице!");
        return pageManager.getFoodPage();
    }


}
