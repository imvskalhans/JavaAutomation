package com.demo.tests;

import base.UiBase;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.io.File;

import static org.testng.Assert.*;
@Test
public class FormInteractionTest extends UiBase {

    @Test
    public void fillPracticeForm() throws InterruptedException {
        driver.get("https://demoqa.com/automation-practice-form");

        //remove ad overlays
        removeAdOverlay();

        driver.findElement(By.id("firstName")).sendKeys("Vishal");
        driver.findElement(By.id("lastName")).sendKeys("Singh");
        driver.findElement(By.id("userEmail")).sendKeys("vishal@example.com");

        // Safely click gender radio button
        safeClick(By.xpath("//label[text()='Male']"));

        driver.findElement(By.id("userNumber")).sendKeys("9999999999");

        // Set Date of Birth
        driver.findElement(By.id("dateOfBirthInput")).click();
        new Select(driver.findElement(By.className("react-datepicker__year-select"))).selectByVisibleText("1995");
        new Select(driver.findElement(By.className("react-datepicker__month-select"))).selectByVisibleText("August");
        driver.findElement(By.xpath("//div[text()='15']")).click();

        // Subject input
//        WebElement subjectInput = driver.findElement(By.id("subjectsInput"));
//        subjectInput.sendKeys("Maths");
//        subjectInput.sendKeys(Keys.TAB);

        // Add multiple subjects
        WebElement subjectInput = driver.findElement(By.id("subjectsInput"));
        String[] subjectsToAdd = {"Maths", "English", "Computer Science", "Physics", "Chemistry", "Economics"};
        for (String subject : subjectsToAdd) {
            subjectInput.sendKeys(subject);
            Thread.sleep(300); // Let dropdown suggestions appear
            subjectInput.sendKeys(Keys.ENTER); // Confirm selection
        }

// Waiting for subjects to be added visually
        Thread.sleep(500);

// Removing subjects
        try {
            WebElement englishRemove = driver.findElement(By.xpath("//div[text()='English']/following-sibling::div"));
            englishRemove.click();
            WebElement csRemove = driver.findElement(By.xpath("//div[text()='Computer Science']/following-sibling::div"));
            csRemove.click();
        } catch (NoSuchElementException e) {
            System.out.println("Some subjects were not found for removal.");
        }


        // Hobbies
        safeClick(By.xpath("//label[text()='Sports']"));
        safeClick(By.xpath("//label[text()='Reading']"));

        // Upload picture
        File file = new File("src/test/resources/testfile.png");
        driver.findElement(By.id("uploadPicture")).sendKeys(file.getAbsolutePath());

        // Address
        driver.findElement(By.id("currentAddress")).sendKeys("1234 Test Street, City");

        // State and City selection using keyboard
        WebElement state = driver.findElement(By.id("react-select-3-input"));
        state.sendKeys("NCR");
        state.sendKeys(Keys.ENTER);

        WebElement city = driver.findElement(By.id("react-select-4-input"));
        city.sendKeys("Gurgaon");
        city.sendKeys(Keys.ENTER);

        // Submit button
        safeClick(By.id("submit"));

        // Verify modal
        WebElement modalTitle = driver.findElement(By.id("example-modal-sizes-title-lg"));
        assertEquals(modalTitle.getText(), "Thanks for submitting the form");

        // Waiting for visually reading the form entries and verify
        Thread.sleep(5000);

        // Close modal
        WebElement closeBtn = driver.findElement(By.id("closeLargeModal"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", closeBtn);
    }
}
