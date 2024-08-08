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

public class IFrameTest {

    private WebDriver driver;
    private static final String URL = "https://the-internet.herokuapp.com/iframe";

    private static final String FRAME_ID = "mce_0_ifr";
    private static final String CUSTOM_TEXT = "Hello world";
    private static final By CONTENT_BODY = By.id("tinymce");

    @BeforeMethod
    public void openBrowser() {
        // Set up the WebDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();

        // Initialize the WebDriver instance
        driver = new ChromeDriver();
        driver.get(URL);
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void frameTest() {
        driver.switchTo().frame(FRAME_ID);
        WebElement contentBody = driver.findElement(CONTENT_BODY);
        contentBody.sendKeys(CUSTOM_TEXT);
        Assert.assertEquals(CUSTOM_TEXT, contentBody.getText());
    }
}
