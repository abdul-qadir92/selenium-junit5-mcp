package com.example.framework.base;

import com.example.framework.hooks.TestHooks;
import com.example.framework.managers.WebDriverManager;
import com.example.framework.utils.ConfigManager;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base test class providing common functionality for all test classes
 */
@ExtendWith(TestHooks.class)
public abstract class BaseTest {
    
    protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    protected WebDriver driver;
    protected WebDriverManager webDriverManager;
    
    /**
     * Initialize test setup
     */
    protected void setUp() {
        logger.info("Setting up test: {}", this.getClass().getSimpleName());
        webDriverManager = WebDriverManager.getInstance();
        driver = webDriverManager.getDriver();
    }
    
    /**
     * Clean up after test
     */
    protected void tearDown() {
        logger.info("Tearing down test: {}", this.getClass().getSimpleName());
        if (driver != null && ConfigManager.getProperty("browser.closeAfterTest", "true").equals("true")) {
            webDriverManager.quitDriver();
        }
    }
    
    /**
     * Navigate to base URL
     */
    protected void navigateToBaseUrl() {
        String baseUrl = ConfigManager.getBaseUrl();
        logger.info("Navigating to base URL: {}", baseUrl);
        driver.get(baseUrl);
    }
    
    /**
     * Navigate to specific URL
     */
    protected void navigateToUrl(String url) {
        logger.info("Navigating to URL: {}", url);
        driver.get(url);
    }
    
    /**
     * Get current page title
     */
    protected String getPageTitle() {
        return driver.getTitle();
    }
    
    /**
     * Get current page URL
     */
    protected String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
    
    /**
     * Refresh current page
     */
    protected void refreshPage() {
        logger.info("Refreshing page");
        driver.navigate().refresh();
    }
    
    /**
     * Go back to previous page
     */
    protected void goBack() {
        logger.info("Going back to previous page");
        driver.navigate().back();
    }
    
    /**
     * Go forward to next page
     */
    protected void goForward() {
        logger.info("Going forward to next page");
        driver.navigate().forward();
    }
    
    /**
     * Get WebDriver instance
     */
    protected WebDriver getDriver() {
        return driver;
    }
    
    /**
     * Get WebDriverManager instance
     */
    protected WebDriverManager getWebDriverManager() {
        return webDriverManager;
    }
}
