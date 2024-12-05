package org.FluffyTerror.FirstTest;

import org.FluffyTerror.basetestclass.BaseTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;



public class FirstTest extends BaseTest {

    @Test
    @Disabled("Другая практическая")
    public void testVegetable(){
        app.getHomePage()
                .selectBaseMenu("Песочница")
                .selectSubMenu("Товары")
                .checkOpenFoodPage()
                .selectAddPage()
                .checkOpenAddPage()
                .fillName("Артишок")
                .selectProductType("VEGETABLE")
                .makeExotic()
                .save()
                .checkOpenAddPage()
                .fillName("Картошка")
                .selectProductType("VEGETABLE")
                .save_fin();
        clearData();
    }

    @Test
    @Disabled("Другая практическая")
    public void testFruit(){
        app.getHomePage()
                .selectBaseMenu("Песочница")
                .selectSubMenu("Товары")
                .checkOpenFoodPage()
                .selectAddPage()
                .checkOpenAddPage()
                .fillName("Манго")
                .selectProductType("FRUIT")
                .makeExotic()
                .save()
                .checkOpenAddPage()
                .fillName("Груша")
                .selectProductType("FRUIT")
                .save_fin();
        clearData();
    }


}
