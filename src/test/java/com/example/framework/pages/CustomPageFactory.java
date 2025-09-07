package com.example.framework.pages;

import com.example.framework.managers.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Custom Page Factory for creating page objects with enhanced functionality
 */
public class CustomPageFactory {
    
    private static final Logger logger = LoggerFactory.getLogger(CustomPageFactory.class);
    
    /**
     * Create a new instance of a page object
     */
    public static <T extends BasePage> T createPage(Class<T> pageClass) {
        try {
            WebDriver driver = WebDriverManager.getInstance().getDriver();
            return createPage(pageClass, driver);
        } catch (Exception e) {
            logger.error("Failed to create page instance for: {}", pageClass.getSimpleName(), e);
            throw new RuntimeException("Failed to create page instance", e);
        }
    }
    
    /**
     * Create a new instance of a page object with specific WebDriver
     */
    public static <T extends BasePage> T createPage(Class<T> pageClass, WebDriver driver) {
        try {
            Constructor<T> constructor = pageClass.getDeclaredConstructor(WebDriver.class);
            T page = constructor.newInstance(driver);
            logger.debug("Created page instance: {}", pageClass.getSimpleName());
            return page;
        } catch (NoSuchMethodException e) {
            // Try default constructor
            try {
                Constructor<T> constructor = pageClass.getDeclaredConstructor();
                T page = constructor.newInstance();
                logger.debug("Created page instance with default constructor: {}", pageClass.getSimpleName());
                return page;
            } catch (Exception ex) {
                logger.error("Failed to create page instance for: {}", pageClass.getSimpleName(), ex);
                throw new RuntimeException("Failed to create page instance", ex);
            }
        } catch (Exception e) {
            logger.error("Failed to create page instance for: {}", pageClass.getSimpleName(), e);
            throw new RuntimeException("Failed to create page instance", e);
        }
    }
    
    /**
     * Initialize page elements using Selenium PageFactory
     */
    public static <T> T initElements(WebDriver driver, Class<T> pageClass) {
        try {
            T page = pageClass.getDeclaredConstructor().newInstance();
            PageFactory.initElements(driver, page);
            logger.debug("Initialized page elements for: {}", pageClass.getSimpleName());
            return page;
        } catch (Exception e) {
            logger.error("Failed to initialize page elements for: {}", pageClass.getSimpleName(), e);
            throw new RuntimeException("Failed to initialize page elements", e);
        }
    }
    
    /**
     * Initialize page elements for an existing page object
     */
    public static void initElements(WebDriver driver, Object page) {
        PageFactory.initElements(driver, page);
        logger.debug("Initialized page elements for: {}", page.getClass().getSimpleName());
    }
    
    /**
     * Create and initialize a page object in one step
     */
    public static <T extends BasePage> T createAndInitPage(Class<T> pageClass) {
        WebDriver driver = WebDriverManager.getInstance().getDriver();
        T page = createPage(pageClass, driver);
        PageFactory.initElements(driver, page);
        logger.debug("Created and initialized page: {}", pageClass.getSimpleName());
        return page;
    }
}
