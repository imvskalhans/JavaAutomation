package com.demo.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
public class LoginTest {
    WebDriver driver;

    @BeforeMethod
    public  void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
    @Test
    public void openWebPage(){

        driver.get("https://www.saucedemo.com/v1/");
        System.out.println("Page Title: " + driver.getTitle());

    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
