package org.FluffyTerror.managers;
import org.FluffyTerror.pages.AddPage;
import org.FluffyTerror.pages.FoodPage;
import org.FluffyTerror.pages.HomePage;

public class PageManager {

    /**
     * Страничка выбора списка продуктов
     */
    private FoodPage foodPage;

    /**
     * Начальная страничка
     */
    private HomePage homePage;

    /**
     * Страничка добавления товара в список продуктов
     */
    private AddPage addPage;

    /**
     * Менеджер страничек
     */
    private static PageManager pageManager;

    /**
     * Ленивая инициализация FoodPage
     *
     * @return FoodPage
     */
    public FoodPage getFoodPage(){
        if(foodPage == null){
            foodPage = new FoodPage();
        }
        return foodPage;
    }
    /**
     * Ленивая инициализация HomePage
     *
     * @return HomePage
     */
    public HomePage getHomePage(){
        if(homePage==null){
            homePage =  new HomePage();

        }
        return homePage;
    }
    /**
     * Ленивая инициализация AddPage
     *
     * @return AddPage
     */
    public AddPage getAddPage() {
        if (addPage==null){
            addPage = new AddPage();
        }
        return addPage;
    }

    /**
     * конструктор PageManager
     */
    private PageManager() {
    }

    /**
     * Ленивая инициализация PageManager
     *
     * @return PageManager
     */

    public static PageManager getPageManager() {
        if (pageManager == null) {
            pageManager = new PageManager();
        }
        return pageManager;
    }




}
