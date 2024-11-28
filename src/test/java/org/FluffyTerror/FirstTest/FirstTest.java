package org.FluffyTerror.FirstTest;

import org.FluffyTerror.basetestclass.BaseTest;
import org.junit.jupiter.api.Test;


public class FirstTest extends BaseTest {

    @Test
    public void testFruit(){
        app.getHomePage()
                .selectBaseMenu("Песочница")
                .selectSubMenu("Продукты")
                .checkOpenFoodPage();


    }

}
