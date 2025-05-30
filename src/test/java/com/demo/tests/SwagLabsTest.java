package com.demo.tests;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SwagLabsTest {

    //declaring a driver
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        
        //setting up driver
        WebDriverManager.edgedriver().setup();

        driver = new EdgeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testLoginAndPrintProducts() {
        
        //accessing the site
        driver.get("https://www.saucedemo.com/v1/");

        //logging in using send keys
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        driver.findElement(By.className("btn_action")).click();

        
        //switch window handle to product page
        String productPage = driver.getWindowHandle();
        driver.switchTo().window(productPage);

        //Listing all the product link with "Sauce" in there name
        List<WebElement> elements = driver.findElements(By.partialLinkText("Sauce"));
        for (WebElement e : elements) {
            System.out.println(e.getText());
        }

        //adding elements to cart using xpath (here first we are reaching the element div using text and then moving up to access ancestor to access inventory item div and then moving to add to cart button
        driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']/ancestor::div[@class='inventory_item']//button")).click();
        driver.findElement(By.xpath("//div[text()='Sauce Labs Bike Light']/ancestor::div[@class='inventory_item']//button")).click();
        driver.findElement(By.xpath("//div[text()='Sauce Labs Bolt T-Shirt']/ancestor::div[@class='inventory_item']//button")).click();
        driver.findElement(By.xpath("//div[text()='Test.allTheThings() T-Shirt (Red)']/ancestor::div[@class='inventory_item']//button")).click();
       
        //viewing cart using class name xpath
        driver.findElement(By.xpath("//a[contains(@class, 'shopping_cart_link fa-layers fa-fw')]")).click();

        //switching to cartPage
        String cartPage = driver.getWindowHandle();
        driver.switchTo().window(cartPage);

        //clicking on the checkout button
        driver.findElement(By.xpath("//a[contains(@class, 'checkout_button')]")).click();

        //switching to checkoutInfo page
        String checkoutInfo = driver.getWindowHandle();
        driver.switchTo().window(checkoutInfo);

        //filling the checkout form




    }

//    @AfterClass
//    public void tearDown() {
//        driver.quit();
//    }
}
