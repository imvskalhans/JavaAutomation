package com.demo.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
public class LoginTest {
    WebDriver driver;

    @BeforeMethod
    public  void setUp(){
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize(); // Optional, won't do much in headless
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
