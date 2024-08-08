package com.coherentsolutions.java.webauto.section02;

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
 * Demonstrates basic interaction with an iFrame using Selenium WebDriver.
 */
public class Ex01IFrameBasic {

    private WebDriver driver;
    private static final String URL = "https://the-internet.herokuapp.com/iframe";

    // Constants for frame and content
    private static final String FRAME_ID = "mce_0_ifr";
    private static final String CUSTOM_TEXT = "Hello world";
    private static final By CONTENT_BODY = By.id("tinymce");

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
     * Test to interact with an iFrame and verify content changes.
     */
    @Test
    public void frameTest() {
        // Switch to the iFrame
        driver.switchTo().frame(FRAME_ID);

        // Find the content body and input text
        WebElement contentBody = driver.findElement(CONTENT_BODY);
        contentBody.clear(); // Clear existing text
        contentBody.sendKeys(CUSTOM_TEXT);

        // Assert that the content matches the expected text
        Assert.assertEquals(contentBody.getText(), CUSTOM_TEXT);
    }
}

