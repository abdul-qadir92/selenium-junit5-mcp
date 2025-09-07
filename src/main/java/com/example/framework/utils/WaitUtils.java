package com.example.framework.utils;

import com.example.framework.config.TestConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

/**
 * Utility class for handling various wait conditions
 */
public class WaitUtils {
    private static final Logger logger = LoggerFactory.getLogger(WaitUtils.class);
    private static final TestConfig config = TestConfig.getInstance();

    /**
     * Wait for element to be visible
     */
    public static WebElement waitForElementVisible(WebDriver driver, By locator) {
        return waitForElementVisible(driver, locator, config.getExplicitWait());
    }

    public static WebElement waitForElementVisible(WebDriver driver, By locator, int timeoutSeconds) {
        logger.debug("Waiting for element to be visible: {}", locator);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Wait for element to be clickable
     */
    public static WebElement waitForElementClickable(WebDriver driver, By locator) {
        return waitForElementClickable(driver, locator, config.getExplicitWait());
    }

    public static WebElement waitForElementClickable(WebDriver driver, By locator, int timeoutSeconds) {
        logger.debug("Waiting for element to be clickable: {}", locator);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Wait for element to be present in DOM
     */
    public static WebElement waitForElementPresent(WebDriver driver, By locator) {
        return waitForElementPresent(driver, locator, config.getExplicitWait());
    }

    public static WebElement waitForElementPresent(WebDriver driver, By locator, int timeoutSeconds) {
        logger.debug("Waiting for element to be present: {}", locator);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Wait for text to be present in element
     */
    public static boolean waitForTextToBePresentInElement(WebDriver driver, By locator, String text) {
        return waitForTextToBePresentInElement(driver, locator, text, config.getExplicitWait());
    }

    public static boolean waitForTextToBePresentInElement(WebDriver driver, By locator, String text, int timeoutSeconds) {
        logger.debug("Waiting for text '{}' to be present in element: {}", text, locator);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    /**
     * Wait for URL to contain specific text
     */
    public static boolean waitForUrlToContain(WebDriver driver, String urlText) {
        return waitForUrlToContain(driver, urlText, config.getExplicitWait());
    }

    public static boolean waitForUrlToContain(WebDriver driver, String urlText, int timeoutSeconds) {
        logger.debug("Waiting for URL to contain: {}", urlText);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.urlContains(urlText));
    }

    /**
     * Wait for page title to contain specific text
     */
    public static boolean waitForTitleToContain(WebDriver driver, String titleText) {
        return waitForTitleToContain(driver, titleText, config.getExplicitWait());
    }

    public static boolean waitForTitleToContain(WebDriver driver, String titleText, int timeoutSeconds) {
        logger.debug("Waiting for title to contain: {}", titleText);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.titleContains(titleText));
    }

    /**
     * Wait for element to disappear
     */
    public static boolean waitForElementToDisappear(WebDriver driver, By locator) {
        return waitForElementToDisappear(driver, locator, config.getExplicitWait());
    }

    public static boolean waitForElementToDisappear(WebDriver driver, By locator, int timeoutSeconds) {
        logger.debug("Waiting for element to disappear: {}", locator);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    /**
     * Fluent wait for custom conditions
     */
    public static <T> T fluentWait(WebDriver driver, java.util.function.Function<WebDriver, T> condition, int timeoutSeconds, int pollingIntervalSeconds) {
        logger.debug("Using fluent wait with timeout: {}s, polling: {}s", timeoutSeconds, pollingIntervalSeconds);
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutSeconds))
                .pollingEvery(Duration.ofSeconds(pollingIntervalSeconds))
                .ignoring(NoSuchElementException.class);
        
        return wait.until(condition);
    }

    /**
     * Wait for a specific number of seconds
     */
    public static void waitForSeconds(int seconds) {
        try {
            logger.debug("Waiting for {} seconds", seconds);
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.warn("Wait interrupted", e);
        }
    }
}
