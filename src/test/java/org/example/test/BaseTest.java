package org.example.test;

import org.example.page.CoffeeCataloguePage;
import org.example.page.HomePage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class BaseTest {
    WebDriver driver;
    HomePage homePage;
    //CoffeeCataloguePage coffeeCataloguePage;
    @Before
    public void setUp() {
        System.setProperty("web-driver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(90));
        driver.get("https://www-staging.nespresso.com/pro/be/en");
        homePage = new HomePage(driver);
        //coffeeCataloguePage = new CoffeeCataloguePage(driver);

    }
    @Test
    public void testURL() {
        // FluentWait to be used in line 47
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(1));
        String capsulesUnits = "100";

        /* li.HeaderNavigationBarItem>a[href*="/order/capsules/"]
        WebElement coffeeMenu = driver.findElement(By.xpath("//div[@class=\"HeaderNavigationBarItem__title\"//[text()=\"Coffee\"]"));
        coffeeMenu.click();
         */
        WebElement professionalCoffeeMenu = driver.findElement(By.cssSelector("li.HeaderNavigationBarItem>a[href*=\"capsules\"]"));
        professionalCoffeeMenu.click();
        //xpath -> article[@data-product-item-id="8787.81"]//div[@class="QuantitySelector__container"]
        WebElement coffeeProductSelection = driver.findElement (By.cssSelector("article[data-product-item-id=\"8787.81\"] button"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript ("arguments [0].scrollIntoView ();", coffeeProductSelection);

        //WebElement coffeeProductSelection = driver.findElement(By.cssSelector("article[data-product-item-id=\"8787.81\"] button"));
        coffeeProductSelection.click();

        //coffeeProductSelection.sendKeys(capsulesUnits + Keys.ENTER);
        WebElement chooseQuantityElement = driver.findElement(By.id("ta-quantity-selector__predefined-2"));
        chooseQuantityElement.click();
        //chooseQuantityElement.sendKeys("100");
        WebElement miniBasketContain = wait.until(elementToBeClickable(By.xpath("//span[@class=\"notranslate\" and contains(.,100)]")));


        /*
        miniBasketContain.click();
        WebElement checkQuantity = driver.findElement(By.cssSelector("div[class=\"AddToBagButtonSmall__quantity"));
        */
        Assert.assertEquals(miniBasketContain.getText(),capsulesUnits);
        System.out.println(miniBasketContain.getText());
        /*
        WebElement miniBasket = driver.findElement(By.cssSelector("button[id=\"ta-mini-basket__open\"]"));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("argument[0].click()",miniBasket);
        //Assert.assertTrue(firstProduct.isDisplayed());
         */

    }
    @Test
    public void openCatalogueProfessionalTest(){
        String productID = "8787.81";
        homePage.acceptCookies();
        homePage.openProfessionalCatalogue();
        CoffeeCataloguePage coffeeCataloguePage = new CoffeeCataloguePage(driver);
        coffeeCataloguePage.switchToTechnologyCoffee("Professional");
        System.out.println(coffeeCataloguePage.getClass());
        Assert.assertTrue("Product is not displayed", coffeeCataloguePage.isProductDisplayed());
    }
    @After
    public void endBrowser() {
        driver.quit();
    }
}
