package org.example.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
public class HomePage {
    WebDriver driver;
    @FindBy(id="_evidon-accept-button")
    WebElement cookieAcceptButton;
    @FindBy(css="li.HeaderNavigationBarItem>a[href*=\"capsules\"]")
    WebElement professionalMenuCatalogue;
    //By cookieAcceptButton = By.id("_evidon-accept-button");
    //By professionalMenuCatalogue = By.cssSelector("li.HeaderNavigationBarItem>a[href*=\"capsules\"]");
    public void waitMethod(long durationWait, WebElement elementToWaitFor){
        durationWait = 100;
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofMillis(durationWait));
        webDriverWait.until(ExpectedConditions.and(elementToBeClickable(elementToWaitFor),presenceOfElementLocated((By) elementToWaitFor)));
    }
    public HomePage(WebDriver driver) {
        this.driver = driver;
        //to make selenium instantiate the @Find elements
        PageFactory.initElements(driver,this);
    }
    public void openProfessionalCatalogue(){
        //waitMethod(100, professionalMenuCatalogue);
        //driver.findElement(professionalMenuCatalogue).click();
        professionalMenuCatalogue.click();
    }
    public void  acceptCookies() {
        //waitMethod(100, cookieAcceptButton);
        //driver.findElement(cookieAcceptButton).click();
        cookieAcceptButton.click();
    }
}
