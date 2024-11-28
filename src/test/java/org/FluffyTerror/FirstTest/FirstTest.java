package org.FluffyTerror.FirstTest;

import org.FluffyTerror.basetestclass.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class FirstTest extends BaseTest {

    @Test
    public void testFruit(){
        WebDriver driver = new ChromeDriver();
        System.setProperty("webdriver.chromedriver.driver","src/test/resources/chromedriver.exe");

        driver.manage().timeouts().pageLoadTimeout(5,TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

        driver.manage().window().maximize();

        driver.get("http://localhost:8080/");
        WebElement element = driver.findElement(By.xpath("//a [@href= '#']"));
        element.click();


        WebElement foodButton = element.findElement(By.xpath("//..//a[@href='/food']"));

        foodButton.click();
        WebElement foodTitle = driver.findElement(By.xpath("/html/body/div/div[2]/div/h5"));//так как там 2 h5
        //пришлось явно указать элемент с которым нужно свериться
        Assertions.assertEquals("Список товаров", foodTitle.getText(),"не перешли на страницу");


    }

}
