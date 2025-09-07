package com.example.framework.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Configuration management class for test settings
 */
public class TestConfig {
    private static final Logger logger = LoggerFactory.getLogger(TestConfig.class);
    private static TestConfig instance;
    private Map<String, Object> config;

    private TestConfig() {
        loadConfig();
    }

    public static TestConfig getInstance() {
        if (instance == null) {
            instance = new TestConfig();
        }
        return instance;
    }

    private void loadConfig() {
        try {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            File configFile = new File("src/test/resources/config/test-config.yml");
            
            if (configFile.exists()) {
                config = mapper.readValue(configFile, Map.class);
                logger.info("Configuration loaded from: {}", configFile.getAbsolutePath());
            } else {
                // Load default configuration
                loadDefaultConfig();
                logger.info("Using default configuration");
            }
        } catch (IOException e) {
            logger.error("Failed to load configuration", e);
            loadDefaultConfig();
        }
    }

    private void loadDefaultConfig() {
        config = Map.of(
            "browser", "chrome",
            "headless", false,
            "implicitWait", 10,
            "explicitWait", 20,
            "pageLoadTimeout", 30,
            "baseUrl", "https://www.google.com",
            "screenshotOnFailure", true,
            "videoRecording", false
        );
    }

    public String getBrowser() {
        return getProperty("browser", "chrome");
    }

    public boolean isHeadless() {
        return getProperty("headless", false);
    }

    public int getImplicitWait() {
        return getProperty("implicitWait", 10);
    }

    public int getExplicitWait() {
        return getProperty("explicitWait", 20);
    }

    public int getPageLoadTimeout() {
        return getProperty("pageLoadTimeout", 30);
    }

    public String getBaseUrl() {
        return getProperty("baseUrl", "https://www.google.com");
    }

    public boolean isScreenshotOnFailure() {
        return getProperty("screenshotOnFailure", true);
    }

    public boolean isVideoRecording() {
        return getProperty("videoRecording", false);
    }

    @SuppressWarnings("unchecked")
    private <T> T getProperty(String key, T defaultValue) {
        try {
            return (T) config.getOrDefault(key, defaultValue);
        } catch (ClassCastException e) {
            logger.warn("Invalid type for property: {}", key);
            return defaultValue;
        }
    }
}
