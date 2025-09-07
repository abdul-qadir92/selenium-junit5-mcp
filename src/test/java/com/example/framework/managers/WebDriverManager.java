package com.example.framework.managers;

import com.example.framework.utils.ConfigManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * Singleton class for managing WebDriver instances
 */
public class WebDriverManager {
    
    private static final Logger logger = LoggerFactory.getLogger(WebDriverManager.class);
    private static WebDriverManager instance;
    private WebDriver driver;
    private ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    
    private WebDriverManager() {
        // Private constructor for singleton
    }
    
    public static synchronized WebDriverManager getInstance() {
        if (instance == null) {
            instance = new WebDriverManager();
        }
        return instance;
    }
    
    public WebDriver getDriver() {
        if (driver == null) {
            driver = createDriver();
        }
        return driver;
    }
    
    public WebDriver createDriver() {
        String browser = ConfigManager.getProperty("browser.name", "chrome").toLowerCase();
        boolean headless = Boolean.parseBoolean(ConfigManager.getProperty("browser.headless", "false"));
        boolean remote = Boolean.parseBoolean(ConfigManager.getProperty("browser.remote", "false"));
        String remoteUrl = ConfigManager.getProperty("browser.remote.url", "http://localhost:4444/wd/hub");
        
        logger.info("Creating WebDriver for browser: {} (headless: {}, remote: {})", browser, headless, remote);
        
        try {
            if (remote) {
                driver = createRemoteDriver(browser, headless, remoteUrl);
            } else {
                driver = createLocalDriver(browser, headless);
            }
            
            configureDriver(driver);
            driverThreadLocal.set(driver);
            return driver;
        } catch (Exception e) {
            logger.error("Failed to create WebDriver", e);
            throw new RuntimeException("Failed to create WebDriver", e);
        }
    }
    
    private WebDriver createLocalDriver(String browser, boolean headless) {
        switch (browser) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                if (headless) {
                    chromeOptions.addArguments("--headless");
                }
                chromeOptions.addArguments("--no-sandbox", "--disable-dev-shm-usage");
                return new ChromeDriver(chromeOptions);
                
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (headless) {
                    firefoxOptions.addArguments("--headless");
                }
                return new FirefoxDriver(firefoxOptions);
                
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                if (headless) {
                    edgeOptions.addArguments("--headless");
                }
                return new EdgeDriver(edgeOptions);
                
            case "safari":
                SafariOptions safariOptions = new SafariOptions();
                return new SafariDriver(safariOptions);
                
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }
    
    private WebDriver createRemoteDriver(String browser, boolean headless, String remoteUrl) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        
        switch (browser) {
            case "chrome":
                capabilities.setBrowserName("chrome");
                ChromeOptions chromeOptions = new ChromeOptions();
                if (headless) {
                    chromeOptions.addArguments("--headless");
                }
                capabilities.merge(chromeOptions);
                break;
                
            case "firefox":
                capabilities.setBrowserName("firefox");
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (headless) {
                    firefoxOptions.addArguments("--headless");
                }
                capabilities.merge(firefoxOptions);
                break;
                
            case "edge":
                capabilities.setBrowserName("MicrosoftEdge");
                EdgeOptions edgeOptions = new EdgeOptions();
                if (headless) {
                    edgeOptions.addArguments("--headless");
                }
                capabilities.merge(edgeOptions);
                break;
                
            default:
                throw new IllegalArgumentException("Unsupported remote browser: " + browser);
        }
        
        return new RemoteWebDriver(new URL(remoteUrl), capabilities);
    }
    
    private void configureDriver(WebDriver driver) {
        int implicitWait = Integer.parseInt(ConfigManager.getProperty("browser.implicitWait", "10"));
        int pageLoadTimeout = Integer.parseInt(ConfigManager.getProperty("browser.pageLoadTimeout", "30"));
        
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTimeout));
        
        // Set window size
        String windowSize = ConfigManager.getProperty("browser.windowSize", "1920x1080");
        if (!windowSize.equals("maximize")) {
            String[] dimensions = windowSize.split("x");
            if (dimensions.length == 2) {
                driver.manage().window().setSize(new org.openqa.selenium.Dimension(
                    Integer.parseInt(dimensions[0]), 
                    Integer.parseInt(dimensions[1])
                ));
            }
        } else {
            driver.manage().window().maximize();
        }
    }
    
    public void quitDriver() {
        if (driver != null) {
            try {
                driver.quit();
                logger.info("WebDriver quit successfully");
            } catch (Exception e) {
                logger.error("Error while quitting WebDriver", e);
            } finally {
                driver = null;
                driverThreadLocal.remove();
            }
        }
    }
    
    public void closeDriver() {
        if (driver != null) {
            try {
                driver.close();
                logger.info("WebDriver closed successfully");
            } catch (Exception e) {
                logger.error("Error while closing WebDriver", e);
            }
        }
    }
}
