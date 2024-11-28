package org.FluffyTerror.managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.FluffyTerror.utils.Const.PATH_CHROMEDRIVER;

public class DriverManager {
    private WebDriver driver;//переменная для хранения драйвера

    public static DriverManager INSTANCE = null;

    private final PropertyManager props = PropertyManager.getPropertyManager();

    private DriverManager() {
    }

    public static DriverManager getDriverManager() {
        if (INSTANCE == null) {
            INSTANCE = new DriverManager();
        }
        return INSTANCE;
    }

    public WebDriver getDriver() {
        if (driver == null) {
            initDriver();
        }
        return driver;
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    private void initDriver() {
        System.setProperty("webdriver.chrome.driver", props.getProperty(PATH_CHROMEDRIVER));
        driver = new ChromeDriver();
    }

}



