package com.example.framework.utils;

import com.example.framework.config.TestConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;

/**
 * WebDriver management utility class
 */
public class WebDriverManagerUtil {
    private static final Logger logger = LoggerFactory.getLogger(WebDriverManagerUtil.class);
    private static WebDriver driver;
    private static final TestConfig config = TestConfig.getInstance();

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = createDriver();
            configureDriver();
        }
        return driver;
    }

    private static WebDriver createDriver() {
        String browser = config.getBrowser().toLowerCase();
        logger.info("Creating WebDriver for browser: {}", browser);

        switch (browser) {
            case "chrome":
                return createChromeDriver();
            case "firefox":
                return createFirefoxDriver();
            case "edge":
                return createEdgeDriver();
            default:
                logger.warn("Unknown browser: {}. Defaulting to Chrome", browser);
                return createChromeDriver();
        }
    }

    private static WebDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        
        if (config.isHeadless()) {
            options.addArguments("--headless");
        }
        
        // Add common Chrome options
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-popup-blocking");
        
        return new ChromeDriver(options);
    }

    private static WebDriver createFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        
        if (config.isHeadless()) {
            options.addArguments("--headless");
        }
        
        options.addArguments("--width=1920");
        options.addArguments("--height=1080");
        
        return new FirefoxDriver(options);
    }

    private static WebDriver createEdgeDriver() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        
        if (config.isHeadless()) {
            options.addArguments("--headless");
        }
        
        options.addArguments("--window-size=1920,1080");
        
        return new EdgeDriver(options);
    }

    private static void configureDriver() {
        if (driver != null) {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(config.getImplicitWait()));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(config.getPageLoadTimeout()));
            driver.manage().window().maximize();
            logger.info("WebDriver configured successfully");
        }
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            logger.info("WebDriver quit successfully");
        }
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.close();
            logger.info("WebDriver closed successfully");
        }
    }

    public static boolean isDriverActive() {
        try {
            return driver != null && !driver.toString().contains("null");
        } catch (Exception e) {
            return false;
        }
    }
}
