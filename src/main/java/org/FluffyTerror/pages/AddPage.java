package org.FluffyTerror.pages;

import org.FluffyTerror.managers.PageManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class AddPage extends BasePage {

    @FindBy(xpath = "//h5[@id ='editModalLabel']")
    private WebElement addTitle;

    @FindBy(xpath = "//input[@id ='name']")
    private WebElement inputName;

    @FindBy(xpath = "//select[@id ='type']")
    private WebElement selectTypeDropdown;

    @FindBy(xpath = "//input[@id ='exotic']")
    private WebElement exoticCheckbox;

    @FindBy(xpath = "//button[@id ='save']")
    private WebElement saveButton;


    /**
     * Заполняет поле имени
     */
    public AddPage fillName(String name) {
        fillInputField(inputName, name);
        return this;
    }

    /**
     * Выбирает тип продукта
     */
    public AddPage selectProductType(String productType) {
        waitUtilElementToBeVisible(selectTypeDropdown);
        sleep(1500);
        Select dropdown = new Select(selectTypeDropdown);
        try {
            dropdown.selectByValue(productType); // Выбор значения по значению (FRUIT или VEGETABLE)
        } catch (Exception e) {
            Assertions.fail("Тип продукта '" + productType + "' недоступен в списке.");
        }
        return this;
    }

    /**
     * Ставит чекбокс экзотический в состояние активен
     */
    public AddPage makeExotic(){
        waitUtilElementToBeVisible(exoticCheckbox).click();
        return this;
    }

    /**
     * Функция нажатия на кнопку сохранить
     */
    public AddPage save(){
        waitUtilElementToBeVisible(saveButton).click();
        sleep(1500);
        return PageManager.getPageManager().getFoodPage().selectAddPage();
    }
    /**
     * Вспомогательная функция которая выполняет то же что и save, но возвращает другой тип данных
     */
    public AddPage save_fin(){
        waitUtilElementToBeVisible(saveButton).click();
        sleep(1500);
        return PageManager.getPageManager().getAddPage();
    }

    /**
     * Проверяет, что страница добавления открыта
     */
    public AddPage checkOpenAddPage() {
        Assertions.assertEquals("Добавление товара", addTitle.getText(), "Заголовок отсутствует/не соответствует требуемому");
        return this;
    }
}

