package org.FluffyTerror.basetestclass;

import org.FluffyTerror.managers.DriverManager;
import org.FluffyTerror.managers.InitManager;
import org.FluffyTerror.managers.PageManager;

import org.FluffyTerror.managers.PropsManger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;


import static org.FluffyTerror.utils.Const.BASE_URL;


public class BaseTest {
    protected PageManager app = PageManager.getPageManager();

    private final DriverManager driverManager = DriverManager.getDriverManager();


    @BeforeAll
    public static void beforeAll(){
        InitManager.initFramework();
    }

    @BeforeEach
    public void  beforeEach(){
        driverManager.getDriver().get(PropsManger.getPropsManager().getProperty(BASE_URL));
    }

    @AfterAll
    public static void afterAll() {
        InitManager.quitFramework();
    }

}
