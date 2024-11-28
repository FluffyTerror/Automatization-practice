package org.FluffyTerror.managers;

import org.FluffyTerror.pages.AddPage;
import org.FluffyTerror.pages.FoodPage;
import org.FluffyTerror.pages.HomePage;

public class PageManager {

    private FoodPage foodPage;

    private HomePage homePage;

    private AddPage addPage;

    private static PageManager pageManager;


    public FoodPage getFoodPage(){
        if(foodPage == null){
            foodPage = new FoodPage();
        }
        return foodPage;
    }

    public HomePage getHomePage(){
        if(homePage==null){
            homePage =  new HomePage();

        }
        return homePage;
    }

    public AddPage getAddPage() {
        if (addPage==null){
            addPage = new AddPage();
        }
        return addPage;
    }

    private PageManager() {
    }

    public static PageManager getPageManager() {
        if (pageManager == null) {
            pageManager = new PageManager();
        }
        return pageManager;
    }


}
