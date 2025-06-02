package com.demo.tests;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import base.UiBase;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SwagLabsTest extends UiBase {


    @Test
    public void testLoginAndPrintProducts() throws IOException, InterruptedException {
        
        //accessing the site
        driver.get("https://www.saucedemo.com/v1/");

        Thread.sleep(500);

        //logging in using send keys
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        driver.findElement(By.className("btn_action")).click();

        
        //switch window handle to product page
        String productPage = driver.getWindowHandle();
        driver.switchTo().window(productPage);

        Thread.sleep(500);

        //interacting with sort container
        // locating dropdown
        WebElement dropdown = driver.findElement(By.className("product_sort_container"));

        // creating Select object
        Select select = new Select(dropdown);

        // selecting by value
        select.selectByValue("lohi");

        Thread.sleep(500);

        //Listing all the product link with "Sauce" in there name
        List<WebElement> elements = driver.findElements(By.partialLinkText("Sauce"));
        for (WebElement e : elements) {
            System.out.println(e.getText());
        }

        Thread.sleep(500);

        //adding elements to cart using xpath (here first we are reaching the element div using text and then moving up to access ancestor to access inventory item div and then moving to add to cart button
        driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']/ancestor::div[@class='inventory_item']//button")).click();
        driver.findElement(By.xpath("//div[text()='Sauce Labs Bike Light']/ancestor::div[@class='inventory_item']//button")).click();
        driver.findElement(By.xpath("//div[text()='Sauce Labs Bolt T-Shirt']/ancestor::div[@class='inventory_item']//button")).click();
        driver.findElement(By.xpath("//div[text()='Test.allTheThings() T-Shirt (Red)']/ancestor::div[@class='inventory_item']//button")).click();

        Thread.sleep(500);

        //viewing cart using class name xpath
        driver.findElement(By.xpath("//a[contains(@class, 'shopping_cart_link fa-layers fa-fw')]")).click();

        //switching to cartPage
        String cartPage = driver.getWindowHandle();
        driver.switchTo().window(cartPage);

        Thread.sleep(500);


        //clicking on the checkout button
        driver.findElement(By.xpath("//a[contains(@class, 'checkout_button')]")).click();

        //switching to checkoutInfo page
        String checkoutInfo = driver.getWindowHandle();
        driver.switchTo().window(checkoutInfo);

        //Loading data from JSON to fill the form
        String path = "src/test/resources/checkoutInfo.json";
        String content = new String(Files.readAllBytes(Paths.get(path)));
        JSONObject json = new JSONObject(content);

        Thread.sleep(500);

        //filling out the checkoutInfo form using json
        driver.findElement(By.id("first-name")).sendKeys(json.getString("firstName"));
        driver.findElement(By.id("last-name")).sendKeys(json.getString("lastName"));
        driver.findElement(By.id("postal-code")).sendKeys(json.getString("postalCode"));


        Thread.sleep(500);


        //clicking continue
        driver.findElement(By.xpath("//input[contains(@class,'cart_button')]")).click();

        Thread.sleep(500);

        //completing the order
        driver.findElement(By.xpath("//a[contains(text(), 'FINISH')]")).click();

        driver.findElement(By.xpath("//button[text()='Open Menu']")).click();

        Thread.sleep(500);  // half-second pause to let sidebar fully open (use WebDriverWait preferred)

        driver.findElement(By.xpath("//*[@id='logout_sidebar_link']")).click();




    }
}
