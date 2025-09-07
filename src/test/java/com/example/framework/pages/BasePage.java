package com.example.framework.pages;

import com.example.framework.managers.WebDriverManager;
import com.example.framework.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

/**
 * Base page class providing common functionality for all page objects
 */
public abstract class BasePage {
    
    protected static final Logger logger = LoggerFactory.getLogger(BasePage.class);
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected WaitUtils waitUtils;
    
    public BasePage() {
        this.driver = WebDriverManager.getInstance().getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.waitUtils = new WaitUtils(driver, wait);
        org.openqa.selenium.support.PageFactory.initElements(driver, this);
        logger.debug("Initialized page: {}", this.getClass().getSimpleName());
    }
    
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.waitUtils = new WaitUtils(driver, wait);
        org.openqa.selenium.support.PageFactory.initElements(driver, this);
        logger.debug("Initialized page: {}", this.getClass().getSimpleName());
    }
    
    /**
     * Navigate to a specific URL
     */
    protected void navigateTo(String url) {
        logger.info("Navigating to: {}", url);
        driver.get(url);
    }
    
    /**
     * Get the current page title
     */
    public String getPageTitle() {
        return driver.getTitle();
    }
    
    /**
     * Get the current page URL
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
    
    /**
     * Check if the page is loaded
     */
    public boolean isPageLoaded() {
        return driver.getCurrentUrl() != null && !driver.getCurrentUrl().isEmpty();
    }
    
    /**
     * Refresh the current page
     */
    public void refreshPage() {
        logger.info("Refreshing page");
        driver.navigate().refresh();
    }
    
    /**
     * Go back to previous page
     */
    public void goBack() {
        logger.info("Going back to previous page");
        driver.navigate().back();
    }
    
    /**
     * Go forward to next page
     */
    public void goForward() {
        logger.info("Going forward to next page");
        driver.navigate().forward();
    }
    
    /**
     * Click on a WebElement with wait
     */
    protected void clickElement(WebElement element, String elementName) {
        waitUtils.waitForElementToBeClickable(element);
        element.click();
        logger.info("Clicked on element: {}", elementName);
    }
    
    /**
     * Send text to a WebElement with wait
     */
    protected void sendText(WebElement element, String text, String elementName) {
        waitUtils.waitForElementToBeVisible(element);
        element.clear();
        element.sendKeys(text);
        logger.info("Entered text '{}' in element: {}", text, elementName);
    }
    
    /**
     * Get text from a WebElement with wait
     */
    protected String getElementText(WebElement element, String elementName) {
        waitUtils.waitForElementToBeVisible(element);
        String text = element.getText();
        logger.debug("Retrieved text '{}' from element: {}", text, elementName);
        return text;
    }
    
    /**
     * Check if element is displayed
     */
    protected boolean isElementDisplayed(WebElement element, String elementName) {
        try {
            boolean displayed = element.isDisplayed();
            logger.debug("Element '{}' is displayed: {}", elementName, displayed);
            return displayed;
        } catch (Exception e) {
            logger.debug("Element '{}' is not displayed", elementName);
            return false;
        }
    }
    
    /**
     * Check if element is enabled
     */
    protected boolean isElementEnabled(WebElement element, String elementName) {
        try {
            boolean enabled = element.isEnabled();
            logger.debug("Element '{}' is enabled: {}", elementName, enabled);
            return enabled;
        } catch (Exception e) {
            logger.debug("Element '{}' is not enabled", elementName);
            return false;
        }
    }
}
