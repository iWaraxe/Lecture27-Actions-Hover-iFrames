package com.coherentsolutions.java.webauto.section02.advanced;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Demonstrates interaction with nested iFrames using Selenium WebDriver.
 */
public class Ex01IFrameNested {

    private WebDriver driver;
    private static final String URL = "https://the-internet.herokuapp.com/nested_frames";

    // Constants for nested frame content
    private static final By NESTED_FRAME_CONTENT = By.tagName("body");

    /**
     * Sets up the WebDriver before each test.
     */
    @BeforeMethod
    public void openBrowser() {
        // Set up the WebDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();

        // Initialize the WebDriver instance
        driver = new ChromeDriver();
        driver.get(URL);
    }

    /**
     * Closes the WebDriver after each test.
     */
    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }

    /**
     * Test to interact with nested iFrames and verify content.
     */
    @Test
    public void nestedFrameTest() {
        // Switch to the top frame
        driver.switchTo().frame("frame-top");

        // Switch to the left frame within the top frame
        driver.switchTo().frame("frame-left");

        // Find the content within the nested frame
        WebElement nestedContent = driver.findElement(NESTED_FRAME_CONTENT);
        System.out.println("Nested Frame Content: " + nestedContent.getText());

        // Assert that the content is not empty
        Assert.assertFalse(nestedContent.getText().isEmpty());

        // Switch back to the main content
        driver.switchTo().defaultContent();
    }
}
