package com.coherentsolutions.java.webauto.section01;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Demonstrates the use of action chains for hover actions using Selenium WebDriver.
 */
public class Ex02HoverWithActionChain {

    private WebDriver driver;
    private static final String URL = "https://the-internet.herokuapp.com/hovers";

    private static final By AVATAR = By.cssSelector(".figure img");
    private static final By AVATAR_TEXT = By.cssSelector(".figcaption h5");

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
     * Test demonstrating the use of action chains to perform hover actions.
     */
    @Test
    public void hoverWithActionChainTest() {
        Actions actions = new Actions(driver);
        WebElement avatar = driver.findElement(AVATAR);

        // Creating an action chain for hover action
        Action hoverAction = actions.moveToElement(avatar).build();
        hoverAction.perform();

        // Verify that the text is displayed after hovering
        WebElement avatarText = driver.findElement(AVATAR_TEXT);
        System.out.println("Avatar Text: " + avatarText.getText());
    }
}
