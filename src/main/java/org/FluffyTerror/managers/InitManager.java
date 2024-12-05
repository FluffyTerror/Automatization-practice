package org.FluffyTerror.managers;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;
import static org.FluffyTerror.utils.Const.IMPLICITLY_WAIT;
import static org.FluffyTerror.utils.Const.PAGE_LOAD_TIMEOUT;

public class InitManager {

        /**
         * Менеджер properties
         *
         * @see PropsManger#getPropsManager()
         */
        private static final PropsManger props = PropsManger.getPropsManager();

        /**
         * Менеджер WebDriver
         *
         * @see DriverManager#getDriverManager()
         */
        private static final DriverManager driverManager = DriverManager.getDriverManager();

        /**
         * Инициализация framework и запуск браузера со страницей приложения
         *
         * @see DriverManager
         * @see PropsManger#getProperty(String)
         * @see org.FluffyTerror.utils.Const
         */
        public static void initFramework() {
            driverManager.getDriver().manage().window().maximize();
            driverManager.getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(props.getProperty(IMPLICITLY_WAIT)), TimeUnit.SECONDS);
            driverManager.getDriver().manage().timeouts().pageLoadTimeout(Integer.parseInt(props.getProperty(PAGE_LOAD_TIMEOUT)), TimeUnit.SECONDS);
        }

        /**
         * Завершения работы framework - гасит драйвер и закрывает сессию с браузером
         *
         * @see DriverManager#quitDriver()
         */
        public static WebDriver getDriver() {
             WebDriver driver = driverManager.getDriver();
            if (driver == null) {
                throw new IllegalStateException("WebDriver is not initialized. Call initFramework() first.");
            }
            return driver;
        }
        public static void quitFramework() {
            driverManager.quitDriver();
        }
        public static void closeFramework(){
            driverManager.closeDriver();
        }

}
