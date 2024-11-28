package org.FluffyTerror.basetestclass;

import org.FluffyTerror.managers.DriverManager;
import org.FluffyTerror.managers.InitManager;
import org.FluffyTerror.managers.PageManager;
import org.FluffyTerror.managers.PropertyManager;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import static org.FluffyTerror.utils.Const.BASE_URL;


public class BaseTest {
    protected PageManager app = PageManager.getPageManager();

    private final DriverManager driverManager = DriverManager.getDriverManager();


    @BeforeClass
    public static void beforeAll(){
        InitManager.initFramework();
    }

    @Before
    public void  beforeEach(){
        driverManager.getDriver().get(PropertyManager.getPropertyManager().getProperty("https://qualit.appline.ru/"));

    }
    @AfterClass
    public static void afterAll() {
        InitManager.quitFramework();
    }

}
