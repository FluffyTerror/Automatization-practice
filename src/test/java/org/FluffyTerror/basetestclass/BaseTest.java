package org.FluffyTerror.basetestclass;

import org.FluffyTerror.managers.DriverManager;
import org.FluffyTerror.managers.PageManager;
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
        driverManager.getDriver().get(TestPropManager.getTestPropManager().getProperty(BASE_URL));

    }
    @AfterClass
    public static void afterAll() {
        InitManager.quitFramework();
    }

}
