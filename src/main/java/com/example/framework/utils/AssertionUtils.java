package com.example.framework.utils;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Utility class for common assertions
 * Note: This class provides basic assertion methods.
 * For more advanced assertions, use AssertJ in test classes.
 */
public class AssertionUtils {
    private static final Logger logger = LoggerFactory.getLogger(AssertionUtils.class);

    /**
     * Assert that element is displayed
     */
    public static void assertElementDisplayed(WebElement element, String elementName) {
        logger.debug("Asserting element is displayed: {}", elementName);
        if (!element.isDisplayed()) {
            throw new AssertionError(elementName + " should be displayed");
        }
    }

    /**
     * Assert that element is not displayed
     */
    public static void assertElementNotDisplayed(WebElement element, String elementName) {
        logger.debug("Asserting element is not displayed: {}", elementName);
        if (element.isDisplayed()) {
            throw new AssertionError(elementName + " should not be displayed");
        }
    }

    /**
     * Assert that element is enabled
     */
    public static void assertElementEnabled(WebElement element, String elementName) {
        logger.debug("Asserting element is enabled: {}", elementName);
        if (!element.isEnabled()) {
            throw new AssertionError(elementName + " should be enabled");
        }
    }

    /**
     * Assert that element is disabled
     */
    public static void assertElementDisabled(WebElement element, String elementName) {
        logger.debug("Asserting element is disabled: {}", elementName);
        if (element.isEnabled()) {
            throw new AssertionError(elementName + " should be disabled");
        }
    }

    /**
     * Assert that element is selected
     */
    public static void assertElementSelected(WebElement element, String elementName) {
        logger.debug("Asserting element is selected: {}", elementName);
        if (!element.isSelected()) {
            throw new AssertionError(elementName + " should be selected");
        }
    }

    /**
     * Assert that element is not selected
     */
    public static void assertElementNotSelected(WebElement element, String elementName) {
        logger.debug("Asserting element is not selected: {}", elementName);
        if (element.isSelected()) {
            throw new AssertionError(elementName + " should not be selected");
        }
    }

    /**
     * Assert that element text equals expected text
     */
    public static void assertElementTextEquals(WebElement element, String expectedText, String elementName) {
        logger.debug("Asserting element text equals: {} for {}", expectedText, elementName);
        String actualText = element.getText();
        if (!actualText.equals(expectedText)) {
            throw new AssertionError(elementName + " text should equal '" + expectedText + "' but was '" + actualText + "'");
        }
    }

    /**
     * Assert that element text contains expected text
     */
    public static void assertElementTextContains(WebElement element, String expectedText, String elementName) {
        logger.debug("Asserting element text contains: {} for {}", expectedText, elementName);
        String actualText = element.getText();
        if (!actualText.contains(expectedText)) {
            throw new AssertionError(elementName + " text should contain '" + expectedText + "' but was '" + actualText + "'");
        }
    }

    /**
     * Assert that element attribute equals expected value
     */
    public static void assertElementAttributeEquals(WebElement element, String attributeName, String expectedValue, String elementName) {
        logger.debug("Asserting element attribute {} equals: {} for {}", attributeName, expectedValue, elementName);
        String actualValue = element.getAttribute(attributeName);
        if (!expectedValue.equals(actualValue)) {
            throw new AssertionError(elementName + " attribute '" + attributeName + "' should equal '" + expectedValue + "' but was '" + actualValue + "'");
        }
    }

    /**
     * Assert that element attribute contains expected value
     */
    public static void assertElementAttributeContains(WebElement element, String attributeName, String expectedValue, String elementName) {
        logger.debug("Asserting element attribute {} contains: {} for {}", attributeName, expectedValue, elementName);
        String actualValue = element.getAttribute(attributeName);
        if (actualValue == null || !actualValue.contains(expectedValue)) {
            throw new AssertionError(elementName + " attribute '" + attributeName + "' should contain '" + expectedValue + "' but was '" + actualValue + "'");
        }
    }

    /**
     * Assert that list is not empty
     */
    public static void assertListNotEmpty(List<?> list, String listName) {
        logger.debug("Asserting list is not empty: {}", listName);
        if (list == null || list.isEmpty()) {
            throw new AssertionError(listName + " should not be empty");
        }
    }

    /**
     * Assert that list has expected size
     */
    public static void assertListSize(List<?> list, int expectedSize, String listName) {
        logger.debug("Asserting list size equals: {} for {}", expectedSize, listName);
        if (list == null || list.size() != expectedSize) {
            throw new AssertionError(listName + " should have size " + expectedSize + " but was " + (list == null ? "null" : list.size()));
        }
    }

    /**
     * Assert that list size is greater than expected
     */
    public static void assertListSizeGreaterThan(List<?> list, int expectedSize, String listName) {
        logger.debug("Asserting list size is greater than: {} for {}", expectedSize, listName);
        if (list == null || list.size() <= expectedSize) {
            throw new AssertionError(listName + " should have size greater than " + expectedSize + " but was " + (list == null ? "null" : list.size()));
        }
    }

    /**
     * Assert that string is not empty
     */
    public static void assertStringNotEmpty(String text, String fieldName) {
        logger.debug("Asserting string is not empty: {}", fieldName);
        if (text == null || text.isEmpty()) {
            throw new AssertionError(fieldName + " should not be empty");
        }
    }

    /**
     * Assert that string is empty
     */
    public static void assertStringEmpty(String text, String fieldName) {
        logger.debug("Asserting string is empty: {}", fieldName);
        if (text != null && !text.isEmpty()) {
            throw new AssertionError(fieldName + " should be empty but was '" + text + "'");
        }
    }

    /**
     * Assert that string contains expected text
     */
    public static void assertStringContains(String actualText, String expectedText, String fieldName) {
        logger.debug("Asserting string contains: {} for {}", expectedText, fieldName);
        if (actualText == null || !actualText.contains(expectedText)) {
            throw new AssertionError(fieldName + " should contain '" + expectedText + "' but was '" + actualText + "'");
        }
    }

    /**
     * Assert that string does not contain expected text
     */
    public static void assertStringDoesNotContain(String actualText, String expectedText, String fieldName) {
        logger.debug("Asserting string does not contain: {} for {}", expectedText, fieldName);
        if (actualText != null && actualText.contains(expectedText)) {
            throw new AssertionError(fieldName + " should not contain '" + expectedText + "' but was '" + actualText + "'");
        }
    }
}