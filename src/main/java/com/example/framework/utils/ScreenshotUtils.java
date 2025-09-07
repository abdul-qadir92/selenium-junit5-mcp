package com.example.framework.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for taking screenshots
 */
public class ScreenshotUtils {
    private static final Logger logger = LoggerFactory.getLogger(ScreenshotUtils.class);
    private static final String SCREENSHOT_DIR = "target/screenshots";

    /**
     * Take a screenshot and return as byte array
     */
    public static byte[] takeScreenshot(WebDriver driver) {
        try {
            if (driver instanceof TakesScreenshot) {
                TakesScreenshot screenshotDriver = (TakesScreenshot) driver;
                byte[] screenshot = screenshotDriver.getScreenshotAs(OutputType.BYTES);
                logger.debug("Screenshot taken successfully");
                return screenshot;
            } else {
                logger.warn("WebDriver does not support taking screenshots");
                return null;
            }
        } catch (Exception e) {
            logger.error("Failed to take screenshot", e);
            return null;
        }
    }

    /**
     * Take a screenshot and save to file
     */
    public static String takeScreenshotAndSave(WebDriver driver, String testName) {
        try {
            if (driver instanceof TakesScreenshot) {
                TakesScreenshot screenshotDriver = (TakesScreenshot) driver;
                byte[] screenshot = screenshotDriver.getScreenshotAs(OutputType.BYTES);
                
                // Create screenshot directory if it doesn't exist
                createScreenshotDirectory();
                
                // Generate filename with timestamp
                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
                String filename = String.format("%s_%s.png", testName, timestamp);
                Path filePath = Paths.get(SCREENSHOT_DIR, filename);
                
                // Save screenshot
                Files.write(filePath, screenshot);
                String absolutePath = filePath.toAbsolutePath().toString();
                logger.info("Screenshot saved: {}", absolutePath);
                return absolutePath;
            } else {
                logger.warn("WebDriver does not support taking screenshots");
                return null;
            }
        } catch (IOException e) {
            logger.error("Failed to save screenshot", e);
            return null;
        }
    }

    /**
     * Take a screenshot and save with custom filename
     */
    public static String takeScreenshotAndSave(WebDriver driver, String testName, String customSuffix) {
        try {
            if (driver instanceof TakesScreenshot) {
                TakesScreenshot screenshotDriver = (TakesScreenshot) driver;
                byte[] screenshot = screenshotDriver.getScreenshotAs(OutputType.BYTES);
                
                // Create screenshot directory if it doesn't exist
                createScreenshotDirectory();
                
                // Generate filename with timestamp and custom suffix
                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
                String filename = String.format("%s_%s_%s.png", testName, customSuffix, timestamp);
                Path filePath = Paths.get(SCREENSHOT_DIR, filename);
                
                // Save screenshot
                Files.write(filePath, screenshot);
                String absolutePath = filePath.toAbsolutePath().toString();
                logger.info("Screenshot saved: {}", absolutePath);
                return absolutePath;
            } else {
                logger.warn("WebDriver does not support taking screenshots");
                return null;
            }
        } catch (IOException e) {
            logger.error("Failed to save screenshot", e);
            return null;
        }
    }

    private static void createScreenshotDirectory() {
        try {
            Path screenshotPath = Paths.get(SCREENSHOT_DIR);
            if (!Files.exists(screenshotPath)) {
                Files.createDirectories(screenshotPath);
                logger.debug("Created screenshot directory: {}", SCREENSHOT_DIR);
            }
        } catch (IOException e) {
            logger.error("Failed to create screenshot directory", e);
        }
    }

    /**
     * Clean up old screenshots (older than specified days)
     */
    public static void cleanupOldScreenshots(int daysToKeep) {
        try {
            Path screenshotPath = Paths.get(SCREENSHOT_DIR);
            if (Files.exists(screenshotPath)) {
                LocalDateTime cutoffDate = LocalDateTime.now().minusDays(daysToKeep);
                
                Files.walk(screenshotPath)
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".png"))
                    .filter(path -> {
                        try {
                            return Files.getLastModifiedTime(path).toInstant()
                                .atZone(java.time.ZoneId.systemDefault())
                                .toLocalDateTime()
                                .isBefore(cutoffDate);
                        } catch (IOException e) {
                            return false;
                        }
                    })
                    .forEach(path -> {
                        try {
                            Files.delete(path);
                            logger.debug("Deleted old screenshot: {}", path);
                        } catch (IOException e) {
                            logger.warn("Failed to delete old screenshot: {}", path, e);
                        }
                    });
                
                logger.info("Cleanup completed for screenshots older than {} days", daysToKeep);
            }
        } catch (IOException e) {
            logger.error("Failed to cleanup old screenshots", e);
        }
    }
}
