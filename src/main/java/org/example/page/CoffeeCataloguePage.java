package org.example.page;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class CoffeeCataloguePage {
    public CoffeeCataloguePage(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;

    public void switchToTechnologyCoffee(String technologyCoffee){
        driver.findElement(By.xpath("//div[@class=\"ProductListTechnologies__name\" and contains(.,"+technologyCoffee+")]")).click();
        // Using "+parameter+" is to insert the method parameter
    }
    public boolean isProductDisplayed() {
        try {
            driver.findElement(By.xpath("//a[@data-product-code=\"8787.81\"]")).isDisplayed();
        } catch (NoSuchElementException e) {return false;
        }
        return false;
    }


}
