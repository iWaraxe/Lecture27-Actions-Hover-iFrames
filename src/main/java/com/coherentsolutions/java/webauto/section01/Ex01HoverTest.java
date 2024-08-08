package com.coherentsolutions.java.webauto.section01;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.IntStream;

/**
 * Demonstrates basic hover actions using Selenium WebDriver.
 */
public class Ex01HoverTest {

    private WebDriver driver;
    private static final String URL = "https://the-internet.herokuapp.com/hovers";

    // Locators for web elements
    private static final By AVATAR = By.cssSelector(".figure img");
    private static final By VIEW_PROFILE_LINK = By.cssSelector("[href*='users']");
    private static final By AVATAR_TEXT = By.cssSelector(".figcaption h5");
    private static final By PROFILE = By.cssSelector("h1");

    private final static String EXPECTED_PROFILE_TEXT = "Not Found";
    private final static String USER_1_TEXT = "user1";

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
     * Test that verifies the profile text after clicking a profile link.
     */
    @Test
    public void profileTextTest() {
        Actions actions = new Actions(driver);

        List<WebElement> avatars = driver.findElements(AVATAR);
        List<WebElement> links = driver.findElements(VIEW_PROFILE_LINK);

        // Hover over the first avatar and click the profile link
        actions.moveToElement(avatars.get(0)).perform();
        links.get(0).click();

        WebElement profile = driver.findElement(PROFILE);
        String actualProfileText = profile.getText();
        Assert.assertEquals(actualProfileText, EXPECTED_PROFILE_TEXT);
    }

    /**
     * Test that verifies the avatar text is displayed upon hovering.
     */
    @Test
    public void avatarTextTest() {
        Actions actions = new Actions(driver);

        WebElement avatar = driver.findElement(AVATAR);
        WebElement avatarText = driver.findElement(AVATAR_TEXT);

        // Hover over the avatar
        actions.moveToElement(avatar).perform();

        // Assert that the avatar text is displayed and contains the expected text
        Assert.assertTrue(avatarText.isDisplayed());
        Assert.assertTrue(avatarText.getText().contains(USER_1_TEXT));
    }

    /**
     * Test that verifies profile links are displayed after hovering over avatars.
     */
    @Test
    public void profileLinksTest() {
        Actions actions = new Actions(driver);

        List<WebElement> avatars = driver.findElements(AVATAR);
        List<WebElement> links = driver.findElements(VIEW_PROFILE_LINK);

        // Hover over each avatar and assert that the link is displayed
        IntStream.range(0, 3).forEach(i -> {
            actions.moveToElement(avatars.get(i)).perform();
            try {
                Thread.sleep(2000); // Pause for demonstration purposes
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Assert.assertTrue(links.get(i).isDisplayed());
        });
    }
}
