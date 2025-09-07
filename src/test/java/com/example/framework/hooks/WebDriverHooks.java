package com.example.framework.hooks;

import com.example.framework.managers.WebDriverManager;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit5 extension specifically for WebDriver lifecycle management
 */
public class WebDriverHooks implements BeforeEachCallback, AfterEachCallback {
    
    private static final Logger logger = LoggerFactory.getLogger(WebDriverHooks.class);
    private static final String DRIVER_KEY = "webdriver";
    
    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        logger.debug("Initializing WebDriver for test: {}", context.getDisplayName());
        WebDriver driver = WebDriverManager.getInstance().createDriver();
        context.getStore(ExtensionContext.Namespace.GLOBAL).put(DRIVER_KEY, driver);
    }
    
    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        WebDriver driver = context.getStore(ExtensionContext.Namespace.GLOBAL).get(DRIVER_KEY, WebDriver.class);
        
        if (driver != null) {
            logger.debug("Cleaning up WebDriver for test: {}", context.getDisplayName());
            WebDriverManager.getInstance().quitDriver();
        }
    }
}
