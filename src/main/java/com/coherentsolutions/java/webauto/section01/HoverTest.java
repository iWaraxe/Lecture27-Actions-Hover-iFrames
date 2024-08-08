package com.coherentsolutions.java.webauto.section01;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.IntStream;

public class HoverTest {

    private WebDriver driver;
    private static final String URL = "https://the-internet.herokuapp.com/hovers";

    private static final By AVATAR = By.cssSelector(".figure img");
    private static final By VIEW_PROFILE_LINK = By.cssSelector("[href*='users']");
    private static final By AVATAR_TEXT = By.cssSelector(".figcaption h5");
    private static final By PROFILE = By.cssSelector("h1");

    private final static String EXPECTED_PROFILE_TEXT = "Not Found";
    private final static String USER_1_TEXT = "user1";

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
    public void profileTextTest() {
        Actions actions = new Actions(driver);

        List<WebElement> avatars = driver.findElements(AVATAR);
        List<WebElement> links = driver.findElements(VIEW_PROFILE_LINK);

        actions.moveToElement(avatars.get(0)).perform();
        links.get(0).click();

        WebElement profile = driver.findElement(PROFILE);
        String actualProfileText = profile.getText();
        Assert.assertEquals(actualProfileText, EXPECTED_PROFILE_TEXT);
    }

    @Test
    public void avatarTextTest() {
        Actions actions = new Actions(driver);

        WebElement avatar = driver.findElement(AVATAR);
        WebElement avatarText = driver.findElement(AVATAR_TEXT);

        actions.moveToElement(avatar).perform();

        Assert.assertTrue(avatarText.isDisplayed());
        Assert.assertTrue(avatarText.getText().contains(USER_1_TEXT));
    }

    @Test
    public void profileLinksTest() {
        Actions actions = new Actions(driver);

        List<WebElement> avatars = driver.findElements(AVATAR);
        List<WebElement> links = driver.findElements(VIEW_PROFILE_LINK);

        WebElement element = driver.findElement(By.id("elementId"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView()", element);
        actions.moveToElement(element).perform();

        IntStream.range(0, 3).forEach(i -> {
            actions.moveToElement(avatars.get(i)).perform();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Assert.assertTrue(links.get(i).isDisplayed());
        });
    }
}
