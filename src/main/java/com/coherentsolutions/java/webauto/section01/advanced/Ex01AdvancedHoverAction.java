package com.coherentsolutions.java.webauto.section01.advanced;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Demonstrates advanced hover actions, including multiple hover actions in a single chain.
 */
public class Ex01AdvancedHoverAction {

    private WebDriver driver;
    private static final String URL = "https://the-internet.herokuapp.com/hovers";

    private static final By AVATAR_1 = By.xpath("(//div[@class='figure'])[1]");
    private static final By AVATAR_2 = By.xpath("(//div[@class='figure'])[2]");

    /**
     * Opens the browser and navigates to the specified URL before each test.
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
     * Closes the browser after each test.
     */
    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }

    /**
     * Test demonstrating advanced hover actions with multiple elements.
     */
    @Test
    public void advancedHoverActionTest() {
        Actions actions = new Actions(driver);

        WebElement avatar1 = driver.findElement(AVATAR_1);
        WebElement avatar2 = driver.findElement(AVATAR_2);

        // Chaining multiple hover actions
        actions.moveToElement(avatar1)
                .pause(1000) // Waits for 1 second before moving to the next element
                .moveToElement(avatar2)
                .pause(1000)
                .build()
                .perform();

        // This is an advanced demonstration showing hover over two elements
    }
}
