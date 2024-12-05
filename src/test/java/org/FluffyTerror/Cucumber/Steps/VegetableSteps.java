package org.FluffyTerror.Cucumber.Steps;

import io.cucumber.java.ru.*;
import org.FluffyTerror.basetestclass.BaseTest;
import org.FluffyTerror.managers.DriverManager;
import org.FluffyTerror.managers.InitManager;

public class VegetableSteps extends BaseTest {
    public void sleep(long sec){
        try {
            Thread.sleep(sec);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @Допустим("открыта страница по адресу {string}")
    public void открыта_страница_по_адресу(String string) {
        DriverManager.getDriverManager().getDriver().get("https://qualit.appline.ru/");
    }

    @Допустим("^выполнено нажатие на раздел \"([^\"]*)\"$")
    public void выполнено_нажатие_на_раздел(String baseMenu) {
        app.getHomePage().selectBaseMenu(baseMenu);
    }
    @Допустим("выбран подраздел {string}")
    public void выбран_подраздел(String string) {
        app.getHomePage().selectSubMenu(string);
    }
    @Допустим("проверка, что страница с товарами открыта")
    public void проверка_что_страница_с_товарами_открыта() {
        app.getFoodPage().checkOpenFoodPage();
    }
    @Допустим("выполнено нажатие на {string}")
    public void выполнено_нажатие_на(String string) {
        app.getFoodPage().selectAddPage();
        sleep(2);
    }
    @Допустим("проверка, что страница добавления товара открыта")
    public void проверка_что_страница_добавления_товара_открыта() {
        app.getAddPage().checkOpenAddPage();
    }
    @Допустим("поле {string} заполнено значением {string}")
    public void поле_заполнено_значением(String string, String string2) {
        app.getAddPage().fillName(string2);
    }
    @Допустим("выбран тип продукта {string}")
    public void выбран_тип_продукта(String string) {
        app.getAddPage().selectProductType(string);
    }
    @Допустим("продукт отмечен как экзотический")
    public void продукт_отмечен_как_экзотический() {
        app.getAddPage().makeExotic();
    }
    @Допустим("кнопка сохранить нажата")
    public void кнопка_сохранить_нажата() {
        app.getAddPage().save_fin();
    }
    @Допустим("выполнен сброс данных")
    public void выполнен_сброс_данных() {
        clearData();
    }

}
