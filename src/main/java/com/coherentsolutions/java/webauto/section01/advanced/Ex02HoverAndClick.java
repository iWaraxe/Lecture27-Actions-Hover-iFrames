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
 * Demonstrates hover and click actions combined using Selenium WebDriver.
 */
public class Ex02HoverAndClick {

    private WebDriver driver;
    private static final String URL = "https://the-internet.herokuapp.com/hovers";

    private static final By AVATAR = By.cssSelector(".figure img");
    private static final By VIEW_PROFILE_LINK = By.cssSelector("[href*='users']");

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
     * Test demonstrating a combined hover and click action.
     */
    @Test
    public void hoverAndClickTest() {
        Actions actions = new Actions(driver);

        WebElement avatar = driver.findElement(AVATAR);
        WebElement viewProfileLink = driver.findElement(VIEW_PROFILE_LINK);

        // Hover over the avatar and then click the profile link
        actions.moveToElement(avatar)
                .click(viewProfileLink)
                .build()
                .perform();

        // Verifying the action with simple output
        System.out.println("Navigated to Profile Page.");
    }
}
