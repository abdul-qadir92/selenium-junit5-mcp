package com.example.framework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for date and time operations
 */
public class DateUtils {
    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);

    /**
     * Get current timestamp in default format
     */
    public static String getCurrentTimestamp() {
        return getCurrentTimestamp("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * Get current timestamp in specified format
     */
    public static String getCurrentTimestamp(String pattern) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        String timestamp = now.format(formatter);
        logger.debug("Generated timestamp: {}", timestamp);
        return timestamp;
    }

    /**
     * Get current date in yyyy-MM-dd format
     */
    public static String getCurrentDate() {
        return getCurrentTimestamp("yyyy-MM-dd");
    }

    /**
     * Get current time in HH:mm:ss format
     */
    public static String getCurrentTime() {
        return getCurrentTimestamp("HH:mm:ss");
    }

    /**
     * Get timestamp for file naming (no spaces or special characters)
     */
    public static String getTimestampForFile() {
        return getCurrentTimestamp("yyyyMMdd_HHmmss");
    }

    /**
     * Get timestamp for logging
     */
    public static String getTimestampForLog() {
        return getCurrentTimestamp("yyyy-MM-dd HH:mm:ss.SSS");
    }

    /**
     * Get current year
     */
    public static int getCurrentYear() {
        return LocalDateTime.now().getYear();
    }

    /**
     * Get current month (1-12)
     */
    public static int getCurrentMonth() {
        return LocalDateTime.now().getMonthValue();
    }

    /**
     * Get current day of month
     */
    public static int getCurrentDay() {
        return LocalDateTime.now().getDayOfMonth();
    }

    /**
     * Get current hour (0-23)
     */
    public static int getCurrentHour() {
        return LocalDateTime.now().getHour();
    }

    /**
     * Get current minute (0-59)
     */
    public static int getCurrentMinute() {
        return LocalDateTime.now().getMinute();
    }

    /**
     * Get current second (0-59)
     */
    public static int getCurrentSecond() {
        return LocalDateTime.now().getSecond();
    }
}
