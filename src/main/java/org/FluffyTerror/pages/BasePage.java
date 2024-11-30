package org.FluffyTerror.pages;

import org.FluffyTerror.managers.DriverManager;
import org.FluffyTerror.managers.PageManager;
import org.FluffyTerror.managers.PropsManger;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    public void sleep()

    {
        try {
            Thread.sleep(1500);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    protected final DriverManager driverManager = DriverManager.getDriverManager();

    protected PageManager pageManager = PageManager.getPageManager();

    protected Actions action = new Actions(driverManager.getDriver());


    protected WebDriverWait wait = new WebDriverWait(driverManager.getDriver(), Duration.ofSeconds(10));

    private final PropsManger props = PropsManger.getPropsManager();

    public BasePage() {
        PageFactory.initElements(driverManager.getDriver(), this);
    }

    protected WebElement waitUtilElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected WebElement waitUtilElementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }


    /**
     * Общий метод по заполнения полей ввода
     *
     * @param field - веб-элемент поле ввода
     * @param value - значение вводимое в поле
     */

    protected void fillInputField(WebElement field, String value) {
        waitUtilElementToBeClickable(field).click();
        field.sendKeys(value);
    }


}
