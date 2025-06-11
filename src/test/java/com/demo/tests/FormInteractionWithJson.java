package com.demo.tests;

import base.UiBase;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.io.File;
@Test
public class FormInteractionWithJson extends UiBase {

    @Test
    public void fillPracticeForm() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode data = mapper.readTree(new File("src/test/resources/formData.json"));

        driver.get("https://demoqa.com/automation-practice-form");
        removeAdOverlay();

        if (data.has("firstName")) driver.findElement(By.id("firstName")).sendKeys(data.get("firstName").asText());
        if (data.has("lastName")) driver.findElement(By.id("lastName")).sendKeys(data.get("lastName").asText());
        if (data.has("email")) driver.findElement(By.id("userEmail")).sendKeys(data.get("email").asText());

        // Gender
        if (data.has("gender")) {
            try {
                removeAdOverlay();
                safeClick(By.xpath("//label[text()='" + data.get("gender").asText() + "']"));
            } catch (Exception e) {
                System.out.println("Gender click failed: " + e.getMessage());
            }
        }

        if (data.has("mobile")) driver.findElement(By.id("userNumber")).sendKeys(data.get("mobile").asText());

        // DOB
        if (data.has("dob")) {
            safeClick(By.id("dateOfBirthInput"));
            Select year = new Select(driver.findElement(By.className("react-datepicker__year-select")));
            Select month = new Select(driver.findElement(By.className("react-datepicker__month-select")));

            year.selectByVisibleText(data.get("dob").get("year").asText());
            month.selectByVisibleText(data.get("dob").get("month").asText());

            String day = data.get("dob").get("day").asText();
            safeClick(By.xpath("//div[not(contains(@class,'outside-month')) and text()='" + day + "']"));
        }

        // Subjects
        if (data.has("subjects")) {
            WebElement subjectInput = driver.findElement(By.id("subjectsInput"));
            for (JsonNode subject : data.get("subjects")) {
                subjectInput.sendKeys(subject.asText());
                subjectInput.sendKeys(Keys.ENTER);
            }
        }

        // Hobbies
        if (data.has("hobbies")) {
            for (JsonNode hobby : data.get("hobbies")) {
                try {
                    safeClick(By.xpath("//label[text()='" + hobby.asText() + "']"));
                } catch (Exception e) {
                    System.out.println("Hobby click failed: " + e.getMessage());
                }
            }
        }

        // File Upload
        if (data.has("filePath")) {
            File file = new File(data.get("filePath").asText());
            driver.findElement(By.id("uploadPicture")).sendKeys(file.getAbsolutePath());
        }

        // Address
        if (data.has("address")) {
            driver.findElement(By.id("currentAddress")).sendKeys(data.get("address").asText());
        }

        // State and City
        if (data.has("state")) {
            WebElement state = driver.findElement(By.id("react-select-3-input"));
            state.sendKeys(data.get("state").asText());
            state.sendKeys(Keys.ENTER);
        }

        if (data.has("city")) {
            WebElement city = driver.findElement(By.id("react-select-4-input"));
            city.sendKeys(data.get("city").asText());
            city.sendKeys(Keys.ENTER);
        }

        // Submit
        safeClick(By.id("submit"));

        // Validation
        WebElement modalTitle = driver.findElement(By.id("example-modal-sizes-title-lg"));
        assert modalTitle.getText().equals("Thanks for submitting the form");

        safeClick(By.id("closeLargeModal"));
    }
}
