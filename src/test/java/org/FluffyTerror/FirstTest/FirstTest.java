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
                .selectProductType("Овощ")
                .makeExotic()
                .save()
                .checkOpenAddPage()
                .fillName("Картошка")
                .selectProductType("Овощ")
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
                .selectProductType("Фрукт")
                .makeExotic()
                .save()
                .checkOpenAddPage()
                .fillName("Груша")
                .selectProductType("Фрукт")
                .save_fin();
        clearData();
    }


}
