package org.FluffyTerror.managers;

import java.util.concurrent.TimeUnit;

import static org.FluffyTerror.utils.Const.IMPLICITLY_WAIT;
import static org.FluffyTerror.utils.Const.PAGE_LOAD_TIMEOUT;

public class InitManager {

    private static final PropertyManager props = PropertyManager.getPropertyManager();

    private static final DriverManager driverManager = DriverManager.getDriverManager();

    public static void initFramework() {
        driverManager.getDriver().manage().window().maximize();
        driverManager.getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(props.getProperty(IMPLICITLY_WAIT)), TimeUnit.SECONDS);
        driverManager.getDriver().manage().timeouts().pageLoadTimeout(Integer.parseInt(props.getProperty(PAGE_LOAD_TIMEOUT)), TimeUnit.SECONDS);
    }

    public static void quitFramework() {
        driverManager.quitDriver();
    }






}
