package com.example.framework.hooks;

import com.example.framework.managers.WebDriverManager;
import com.example.framework.utils.ConfigManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit5 extension for managing test lifecycle and WebDriver operations
 */
public class TestHooks implements BeforeAllCallback, BeforeEachCallback, AfterEachCallback, AfterAllCallback {
    
    private static final Logger logger = LoggerFactory.getLogger(TestHooks.class);
    private static final String DRIVER_KEY = "webdriver";
    
    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        logger.info("Starting test suite: {}", context.getDisplayName());
        ConfigManager.initialize();
    }
    
    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        logger.info("Starting test: {}", context.getDisplayName());
        WebDriver driver = WebDriverManager.getInstance().getDriver();
        context.getStore(ExtensionContext.Namespace.GLOBAL).put(DRIVER_KEY, driver);
    }
    
    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        WebDriver driver = context.getStore(ExtensionContext.Namespace.GLOBAL).get(DRIVER_KEY, WebDriver.class);
        
        if (context.getExecutionException().isPresent()) {
            logger.error("Test failed: {}", context.getDisplayName());
            takeScreenshot(driver, context.getDisplayName());
            attachPageSource(driver, context.getDisplayName());
        } else {
            logger.info("Test passed: {}", context.getDisplayName());
        }
        
        // Clean up WebDriver if needed
        if (driver != null && ConfigManager.getProperty("browser.closeAfterTest", "true").equals("true")) {
            WebDriverManager.getInstance().quitDriver();
        }
    }
    
    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        logger.info("Completed test suite: {}", context.getDisplayName());
        WebDriverManager.getInstance().quitDriver();
    }
    
    @Attachment(value = "Screenshot", type = "image/png")
    private byte[] takeScreenshot(WebDriver driver, String testName) {
        if (driver != null) {
            try {
                return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            } catch (Exception e) {
                logger.error("Failed to take screenshot for test: {}", testName, e);
            }
        }
        return new byte[0];
    }
    
    @Attachment(value = "Page Source", type = "text/html")
    private String attachPageSource(WebDriver driver, String testName) {
        if (driver != null) {
            try {
                return driver.getPageSource();
            } catch (Exception e) {
                logger.error("Failed to get page source for test: {}", testName, e);
            }
        }
        return "";
    }
}
