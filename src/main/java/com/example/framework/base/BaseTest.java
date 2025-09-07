package com.example.framework.base;

import com.example.framework.config.TestConfig;
import com.example.framework.utils.ScreenshotUtils;
import com.example.framework.utils.WebDriverManagerUtil;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;

/**
 * Base test class that provides common setup and teardown functionality
 */
public abstract class BaseTest {
    protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    protected WebDriver driver;
    protected TestConfig config;

    public void setUp() {
        logger.info("Setting up test: {}", this.getClass().getSimpleName());
        config = TestConfig.getInstance();
        driver = WebDriverManagerUtil.getDriver();
        
        // Navigate to base URL
        String baseUrl = config.getBaseUrl();
        logger.info("Navigating to: {}", baseUrl);
        driver.get(baseUrl);
    }

    public void tearDown() {
        logger.info("Tearing down test: {}", this.getClass().getSimpleName());
        
        // Take screenshot on failure if enabled
        if (config.isScreenshotOnFailure()) {
            try {
                byte[] screenshot = ScreenshotUtils.takeScreenshot(driver);
                if (screenshot != null) {
                    // Screenshot taken and saved
                    logger.debug("Screenshot taken successfully");
                }
            } catch (Exception e) {
                logger.warn("Failed to take screenshot: {}", e.getMessage());
            }
        }
        
        // Clean up WebDriver
        WebDriverManagerUtil.quitDriver();
    }

    /**
     * Navigate to a specific URL
     */
    protected void navigateToUrl(String url) {
        logger.info("Navigating to URL: {}", url);
        driver.get(url);
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
     * Refresh the current page
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
}
