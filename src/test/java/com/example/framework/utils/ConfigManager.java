package com.example.framework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Configuration manager for handling test properties and configuration
 */
public class ConfigManager {
    
    private static final Logger logger = LoggerFactory.getLogger(ConfigManager.class);
    private static Properties properties;
    private static final String DEFAULT_CONFIG_FILE = "test.properties";
    
    /**
     * Initialize configuration manager
     */
    public static void initialize() {
        initialize(DEFAULT_CONFIG_FILE);
    }
    
    /**
     * Initialize configuration manager with specific config file
     */
    public static void initialize(String configFile) {
        properties = new Properties();
        loadProperties(configFile);
        logger.info("Configuration manager initialized with file: {}", configFile);
    }
    
    /**
     * Load properties from file
     */
    private static void loadProperties(String configFile) {
        try (InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream(configFile)) {
            if (input == null) {
                logger.warn("Configuration file '{}' not found, using default values", configFile);
                loadDefaultProperties();
                return;
            }
            
            properties.load(input);
            logger.info("Loaded configuration from: {}", configFile);
        } catch (IOException e) {
            logger.error("Error loading configuration file: {}", configFile, e);
            loadDefaultProperties();
        }
    }
    
    /**
     * Load default properties when config file is not found
     */
    private static void loadDefaultProperties() {
        properties.setProperty("browser.name", "chrome");
        properties.setProperty("browser.headless", "false");
        properties.setProperty("browser.remote", "false");
        properties.setProperty("browser.remote.url", "http://localhost:4444/wd/hub");
        properties.setProperty("browser.implicitWait", "10");
        properties.setProperty("browser.pageLoadTimeout", "30");
        properties.setProperty("browser.windowSize", "1920x1080");
        properties.setProperty("browser.closeAfterTest", "true");
        properties.setProperty("test.baseUrl", "https://www.google.com");
        properties.setProperty("test.timeout", "30");
        properties.setProperty("test.retryCount", "0");
        properties.setProperty("allure.results.directory", "target/allure-results");
        properties.setProperty("screenshot.onFailure", "true");
        properties.setProperty("screenshot.onSuccess", "false");
        logger.info("Loaded default configuration properties");
    }
    
    /**
     * Get property value with default
     */
    public static String getProperty(String key, String defaultValue) {
        if (properties == null) {
            initialize();
        }
        return properties.getProperty(key, defaultValue);
    }
    
    /**
     * Get property value
     */
    public static String getProperty(String key) {
        if (properties == null) {
            initialize();
        }
        return properties.getProperty(key);
    }
    
    /**
     * Get boolean property value
     */
    public static boolean getBooleanProperty(String key, boolean defaultValue) {
        String value = getProperty(key, String.valueOf(defaultValue));
        return Boolean.parseBoolean(value);
    }
    
    /**
     * Get integer property value
     */
    public static int getIntProperty(String key, int defaultValue) {
        String value = getProperty(key, String.valueOf(defaultValue));
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            logger.warn("Invalid integer value for property '{}': {}, using default: {}", key, value, defaultValue);
            return defaultValue;
        }
    }
    
    /**
     * Get long property value
     */
    public static long getLongProperty(String key, long defaultValue) {
        String value = getProperty(key, String.valueOf(defaultValue));
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            logger.warn("Invalid long value for property '{}': {}, using default: {}", key, value, defaultValue);
            return defaultValue;
        }
    }
    
    /**
     * Set property value
     */
    public static void setProperty(String key, String value) {
        if (properties == null) {
            initialize();
        }
        properties.setProperty(key, value);
        logger.debug("Set property: {} = {}", key, value);
    }
    
    /**
     * Check if property exists
     */
    public static boolean hasProperty(String key) {
        if (properties == null) {
            initialize();
        }
        return properties.containsKey(key);
    }
    
    /**
     * Get all properties
     */
    public static Properties getAllProperties() {
        if (properties == null) {
            initialize();
        }
        return new Properties(properties);
    }
    
    /**
     * Get browser name
     */
    public static String getBrowserName() {
        return getProperty("browser.name", "chrome");
    }
    
    /**
     * Check if browser should run in headless mode
     */
    public static boolean isHeadless() {
        return getBooleanProperty("browser.headless", false);
    }
    
    /**
     * Check if using remote WebDriver
     */
    public static boolean isRemote() {
        return getBooleanProperty("browser.remote", false);
    }
    
    /**
     * Get remote WebDriver URL
     */
    public static String getRemoteUrl() {
        return getProperty("browser.remote.url", "http://localhost:4444/wd/hub");
    }
    
    /**
     * Get base URL for tests
     */
    public static String getBaseUrl() {
        return getProperty("test.baseUrl", "https://www.google.com");
    }
    
    /**
     * Get test timeout
     */
    public static int getTestTimeout() {
        return getIntProperty("test.timeout", 30);
    }
    
    /**
     * Get retry count for failed tests
     */
    public static int getRetryCount() {
        return getIntProperty("test.retryCount", 0);
    }
    
    /**
     * Check if screenshots should be taken on failure
     */
    public static boolean isScreenshotOnFailure() {
        return getBooleanProperty("screenshot.onFailure", true);
    }
    
    /**
     * Check if screenshots should be taken on success
     */
    public static boolean isScreenshotOnSuccess() {
        return getBooleanProperty("screenshot.onSuccess", false);
    }
}
