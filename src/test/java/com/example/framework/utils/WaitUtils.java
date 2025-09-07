package com.example.framework.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

/**
 * Utility class for common wait operations in Selenium tests
 */
public class WaitUtils {
    
    private static final Logger logger = LoggerFactory.getLogger(WaitUtils.class);
    private final WebDriver driver;
    private final WebDriverWait wait;
    
    public WaitUtils(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }
    
    public WaitUtils(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    /**
     * Wait for element to be visible
     */
    public WebElement waitForElementToBeVisible(WebElement element) {
        logger.debug("Waiting for element to be visible");
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
    
    /**
     * Wait for element to be visible by locator
     */
    public WebElement waitForElementToBeVisible(By locator) {
        logger.debug("Waiting for element to be visible: {}", locator);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    /**
     * Wait for element to be clickable
     */
    public WebElement waitForElementToBeClickable(WebElement element) {
        logger.debug("Waiting for element to be clickable");
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    
    /**
     * Wait for element to be clickable by locator
     */
    public WebElement waitForElementToBeClickable(By locator) {
        logger.debug("Waiting for element to be clickable: {}", locator);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    
    /**
     * Wait for element to be present in DOM
     */
    public WebElement waitForElementToBePresent(By locator) {
        logger.debug("Waiting for element to be present: {}", locator);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    
    /**
     * Wait for element to be invisible
     */
    public boolean waitForElementToBeInvisible(WebElement element) {
        logger.debug("Waiting for element to be invisible");
        return wait.until(ExpectedConditions.invisibilityOf(element));
    }
    
    /**
     * Wait for element to be invisible by locator
     */
    public boolean waitForElementToBeInvisible(By locator) {
        logger.debug("Waiting for element to be invisible: {}", locator);
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
    
    /**
     * Wait for text to be present in element
     */
    public boolean waitForTextToBePresentInElement(WebElement element, String text) {
        logger.debug("Waiting for text '{}' to be present in element", text);
        return wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }
    
    /**
     * Wait for text to be present in element located by locator
     */
    public boolean waitForTextToBePresentInElement(By locator, String text) {
        logger.debug("Waiting for text '{}' to be present in element: {}", text, locator);
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }
    
    /**
     * Wait for element to have specific text
     */
    public boolean waitForElementToHaveText(WebElement element, String text) {
        logger.debug("Waiting for element to have text: {}", text);
        return wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }
    
    /**
     * Wait for element to have specific text by locator
     */
    public boolean waitForElementToHaveText(By locator, String text) {
        logger.debug("Waiting for element to have text '{}': {}", text, locator);
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }
    
    /**
     * Wait for element to be selected
     */
    public boolean waitForElementToBeSelected(WebElement element) {
        logger.debug("Waiting for element to be selected");
        return wait.until(ExpectedConditions.elementToBeSelected(element));
    }
    
    /**
     * Wait for element to be selected by locator
     */
    public boolean waitForElementToBeSelected(By locator) {
        logger.debug("Waiting for element to be selected: {}", locator);
        return wait.until(ExpectedConditions.elementToBeSelected(locator));
    }
    
    /**
     * Wait for all elements to be visible
     */
    public List<WebElement> waitForAllElementsToBeVisible(By locator) {
        logger.debug("Waiting for all elements to be visible: {}", locator);
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }
    
    /**
     * Wait for at least one element to be visible
     */
    public List<WebElement> waitForAtLeastOneElementToBeVisible(By locator) {
        logger.debug("Waiting for at least one element to be visible: {}", locator);
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }
    
    /**
     * Wait for URL to contain specific text
     */
    public boolean waitForUrlToContain(String urlFragment) {
        logger.debug("Waiting for URL to contain: {}", urlFragment);
        return wait.until(ExpectedConditions.urlContains(urlFragment));
    }
    
    /**
     * Wait for URL to be specific URL
     */
    public boolean waitForUrlToBe(String url) {
        logger.debug("Waiting for URL to be: {}", url);
        return wait.until(ExpectedConditions.urlToBe(url));
    }
    
    /**
     * Wait for title to contain specific text
     */
    public boolean waitForTitleToContain(String title) {
        logger.debug("Waiting for title to contain: {}", title);
        return wait.until(ExpectedConditions.titleContains(title));
    }
    
    /**
     * Wait for title to be specific title
     */
    public boolean waitForTitleToBe(String title) {
        logger.debug("Waiting for title to be: {}", title);
        return wait.until(ExpectedConditions.titleIs(title));
    }
    
    /**
     * Wait for alert to be present
     */
    public org.openqa.selenium.Alert waitForAlert() {
        logger.debug("Waiting for alert to be present");
        return wait.until(ExpectedConditions.alertIsPresent());
    }
    
    /**
     * Wait for frame to be available and switch to it
     */
    public WebDriver waitForFrameToBeAvailableAndSwitchToIt(String frameNameOrId) {
        logger.debug("Waiting for frame to be available: {}", frameNameOrId);
        return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameNameOrId));
    }
    
    /**
     * Wait for frame to be available and switch to it by index
     */
    public WebDriver waitForFrameToBeAvailableAndSwitchToIt(int frameIndex) {
        logger.debug("Waiting for frame to be available by index: {}", frameIndex);
        return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
    }
    
    /**
     * Wait for frame to be available and switch to it by WebElement
     */
    public WebDriver waitForFrameToBeAvailableAndSwitchToIt(WebElement frameElement) {
        logger.debug("Waiting for frame to be available by element");
        return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
    }
    
    /**
     * Wait for custom condition
     */
    public <T> T waitForCondition(ExpectedCondition<T> condition) {
        logger.debug("Waiting for custom condition");
        return wait.until(condition);
    }
    
    /**
     * Wait for custom condition with timeout
     */
    public <T> T waitForCondition(ExpectedCondition<T> condition, Duration timeout) {
        logger.debug("Waiting for custom condition with timeout: {}", timeout);
        WebDriverWait customWait = new WebDriverWait(driver, timeout);
        return customWait.until(condition);
    }
    
    /**
     * Wait for a specific amount of time
     */
    public void waitForSeconds(int seconds) {
        logger.debug("Waiting for {} seconds", seconds);
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.warn("Wait was interrupted", e);
        }
    }
    
    /**
     * Wait for a specific amount of time in milliseconds
     */
    public void waitForMilliseconds(long milliseconds) {
        logger.debug("Waiting for {} milliseconds", milliseconds);
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.warn("Wait was interrupted", e);
        }
    }
}
