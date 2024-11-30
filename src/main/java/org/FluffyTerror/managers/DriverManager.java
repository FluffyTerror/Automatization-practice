package org.FluffyTerror.managers;

import org.apache.commons.exec.OS;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.FluffyTerror.utils.Const.*;

public class DriverManager {





    /**
     * Переменна для хранения объекта веб-драйвера
     *
     * @see WebDriver
     */
    private WebDriver driver;


    /**
     * Переменна для хранения объекта DriverManager
     */
    private static DriverManager INSTANCE = null;



    private final PropsManger props = PropsManger.getPropsManager();


    /**
     * Конструктор специально был объявлен как private (singleton паттерн)
     *
     * @see DriverManager#getDriverManager()
     */
    private DriverManager() {
    }

    /**
     * Метод ленивой инициализации DriverManager
     *
     * @return DriverManager - возвращает DriverManager
     */

    public static DriverManager getDriverManager() {
        if (INSTANCE == null) {
            INSTANCE = new DriverManager();
        }
        return INSTANCE;
    }
    
    /**
     * Метод ленивой инициализации веб драйвера
     *
     * @return WebDriver - возвращает веб драйвер
     */

    public WebDriver getDriver() {
        if (driver == null) {
            initDriver();
        }
        return driver;
    }
    

    /**
     * Метод для закрытия сессии драйвера и браузера
     *
     * @see WebDriver#quit()
     */

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }


    private void initDriver() {
        System.setProperty("webdriver.chrome.driver", props.getProperty(PATH_CHROMEDRIVER));
        driver = new ChromeDriver();


    /**
     * Метод инициализирующий веб драйвер
     */
    private void initDriver() {
        if (OS.isFamilyWindows()) {
            initDriverWindowsOsFamily();
        }
    }

    /**
     * Метод инициализирующий веб драйвер под ОС семейства Windows
     */
    private void initDriverWindowsOsFamily(){
        initDriverAnyOsFamily(PATH_GECKO_DRIVER, PATH_CHROME_DRIVER);
    }
    

    private void initDriverAnyOsFamily(String gecko, String chrome) {
        switch (props.getProperty(TYPE_BROWSER)) {
            case "firefox":
                System.setProperty("webdriver.gecko.driver", props.getProperty(gecko));
                driver = new FirefoxDriver();
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver", props.getProperty(chrome));
                driver = new ChromeDriver();
                break;
            default:
                Assertions.fail("Типа браузера '" + props.getProperty(TYPE_BROWSER) + "' не существует во фреймворке");
        }
    }

}



