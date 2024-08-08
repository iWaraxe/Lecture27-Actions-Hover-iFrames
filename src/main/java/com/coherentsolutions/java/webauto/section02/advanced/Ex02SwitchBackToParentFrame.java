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
 * Demonstrates switching back to the parent frame from a nested frame.
 */
public class Ex02SwitchBackToParentFrame {

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
     * Test to switch to a nested iFrame and back to the parent frame.
     */
    @Test
    public void switchBackToParentFrameTest() {
        // Switch to the top frame
        driver.switchTo().frame("frame-top");

        // Switch to the middle frame within the top frame
        driver.switchTo().frame("frame-middle");

        // Find the content within the middle frame
        WebElement middleFrameContent = driver.findElement(NESTED_FRAME_CONTENT);
        System.out.println("Middle Frame Content: " + middleFrameContent.getText());

        // Assert that the content is not empty
        Assert.assertFalse(middleFrameContent.getText().isEmpty());

        // Switch back to the parent frame (frame-top)
        driver.switchTo().parentFrame();

        // Switch to the right frame within the top frame
        driver.switchTo().frame("frame-right");

        // Find the content within the right frame
        WebElement rightFrameContent = driver.findElement(NESTED_FRAME_CONTENT);
        System.out.println("Right Frame Content: " + rightFrameContent.getText());

        // Assert that the content is not empty
        Assert.assertFalse(rightFrameContent.getText().isEmpty());

        // Switch back to the main content
        driver.switchTo().defaultContent();
    }
}
