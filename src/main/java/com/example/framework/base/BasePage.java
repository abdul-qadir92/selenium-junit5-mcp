package com.example.framework.base;

import com.example.framework.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Base page class for Page Object Model implementation
 */
public abstract class BasePage {
    protected static final Logger logger = LoggerFactory.getLogger(BasePage.class);
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Find element with explicit wait
     */
    protected WebElement findElement(By locator) {
        logger.debug("Finding element: {}", locator);
        return WaitUtils.waitForElementVisible(driver, locator);
    }

    /**
     * Find element with custom timeout
     */
    protected WebElement findElement(By locator, int timeoutSeconds) {
        logger.debug("Finding element: {} with timeout: {}s", locator, timeoutSeconds);
        return WaitUtils.waitForElementVisible(driver, locator, timeoutSeconds);
    }

    /**
     * Find elements with explicit wait
     */
    protected List<WebElement> findElements(By locator) {
        logger.debug("Finding elements: {}", locator);
        WaitUtils.waitForElementPresent(driver, locator);
        return driver.findElements(locator);
    }

    /**
     * Click on element with explicit wait
     */
    protected void click(By locator) {
        logger.debug("Clicking element: {}", locator);
        WebElement element = WaitUtils.waitForElementClickable(driver, locator);
        element.click();
    }

    /**
     * Click on element with custom timeout
     */
    protected void click(By locator, int timeoutSeconds) {
        logger.debug("Clicking element: {} with timeout: {}s", locator, timeoutSeconds);
        WebElement element = WaitUtils.waitForElementClickable(driver, locator, timeoutSeconds);
        element.click();
    }

    /**
     * Send keys to element with explicit wait
     */
    protected void sendKeys(By locator, String text) {
        logger.debug("Sending keys '{}' to element: {}", text, locator);
        WebElement element = WaitUtils.waitForElementVisible(driver, locator);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Send keys to element with custom timeout
     */
    protected void sendKeys(By locator, String text, int timeoutSeconds) {
        logger.debug("Sending keys '{}' to element: {} with timeout: {}s", text, locator, timeoutSeconds);
        WebElement element = WaitUtils.waitForElementVisible(driver, locator, timeoutSeconds);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Get text from element with explicit wait
     */
    protected String getText(By locator) {
        logger.debug("Getting text from element: {}", locator);
        WebElement element = WaitUtils.waitForElementVisible(driver, locator);
        return element.getText();
    }

    /**
     * Get text from element with custom timeout
     */
    protected String getText(By locator, int timeoutSeconds) {
        logger.debug("Getting text from element: {} with timeout: {}s", locator, timeoutSeconds);
        WebElement element = WaitUtils.waitForElementVisible(driver, locator, timeoutSeconds);
        return element.getText();
    }

    /**
     * Get attribute value from element
     */
    protected String getAttribute(By locator, String attributeName) {
        logger.debug("Getting attribute '{}' from element: {}", attributeName, locator);
        WebElement element = WaitUtils.waitForElementVisible(driver, locator);
        return element.getAttribute(attributeName);
    }

    /**
     * Check if element is displayed
     */
    protected boolean isDisplayed(By locator) {
        try {
            logger.debug("Checking if element is displayed: {}", locator);
            WebElement element = WaitUtils.waitForElementVisible(driver, locator, 5);
            return element.isDisplayed();
        } catch (Exception e) {
            logger.debug("Element not displayed: {}", locator);
            return false;
        }
    }

    /**
     * Check if element is enabled
     */
    protected boolean isEnabled(By locator) {
        try {
            logger.debug("Checking if element is enabled: {}", locator);
            WebElement element = WaitUtils.waitForElementVisible(driver, locator, 5);
            return element.isEnabled();
        } catch (Exception e) {
            logger.debug("Element not enabled: {}", locator);
            return false;
        }
    }

    /**
     * Check if element is selected
     */
    protected boolean isSelected(By locator) {
        try {
            logger.debug("Checking if element is selected: {}", locator);
            WebElement element = WaitUtils.waitForElementVisible(driver, locator, 5);
            return element.isSelected();
        } catch (Exception e) {
            logger.debug("Element not selected: {}", locator);
            return false;
        }
    }

    /**
     * Wait for element to be visible
     */
    protected void waitForElementVisible(By locator) {
        WaitUtils.waitForElementVisible(driver, locator);
    }

    /**
     * Wait for element to be clickable
     */
    protected void waitForElementClickable(By locator) {
        WaitUtils.waitForElementClickable(driver, locator);
    }

    /**
     * Wait for text to be present in element
     */
    protected boolean waitForTextToBePresent(By locator, String text) {
        return WaitUtils.waitForTextToBePresentInElement(driver, locator, text);
    }

    /**
     * Wait for element to disappear
     */
    protected boolean waitForElementToDisappear(By locator) {
        return WaitUtils.waitForElementToDisappear(driver, locator);
    }

    /**
     * Get current page title
     */
    protected String getPageTitle() {
        String title = driver.getTitle();
        logger.debug("Current page title: {}", title);
        return title;
    }

    /**
     * Get current URL
     */
    protected String getCurrentUrl() {
        String url = driver.getCurrentUrl();
        logger.debug("Current URL: {}", url);
        return url;
    }

    /**
     * Navigate to URL
     */
    protected void navigateToUrl(String url) {
        logger.info("Navigating to URL: {}", url);
        driver.get(url);
    }

    /**
     * Refresh page
     */
    protected void refreshPage() {
        logger.info("Refreshing page");
        driver.navigate().refresh();
    }
}
