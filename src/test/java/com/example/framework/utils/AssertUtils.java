package com.example.framework.utils;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Utility class for common assertion operations
 */
public class AssertUtils {
    
    private static final Logger logger = LoggerFactory.getLogger(AssertUtils.class);
    
    /**
     * Assert element is displayed
     */
    public static void assertElementDisplayed(WebElement element, String elementName) {
        logger.debug("Asserting element is displayed: {}", elementName);
        if (!element.isDisplayed()) {
            throw new AssertionError("Element '" + elementName + "' is not displayed");
        }
    }
    
    /**
     * Assert element is not displayed
     */
    public static void assertElementNotDisplayed(WebElement element, String elementName) {
        logger.debug("Asserting element is not displayed: {}", elementName);
        if (element.isDisplayed()) {
            throw new AssertionError("Element '" + elementName + "' is displayed but should not be");
        }
    }
    
    /**
     * Assert element is enabled
     */
    public static void assertElementEnabled(WebElement element, String elementName) {
        logger.debug("Asserting element is enabled: {}", elementName);
        if (!element.isEnabled()) {
            throw new AssertionError("Element '" + elementName + "' is not enabled");
        }
    }
    
    /**
     * Assert element is disabled
     */
    public static void assertElementDisabled(WebElement element, String elementName) {
        logger.debug("Asserting element is disabled: {}", elementName);
        if (element.isEnabled()) {
            throw new AssertionError("Element '" + elementName + "' is enabled but should be disabled");
        }
    }
    
    /**
     * Assert element is selected
     */
    public static void assertElementSelected(WebElement element, String elementName) {
        logger.debug("Asserting element is selected: {}", elementName);
        if (!element.isSelected()) {
            throw new AssertionError("Element '" + elementName + "' is not selected");
        }
    }
    
    /**
     * Assert element is not selected
     */
    public static void assertElementNotSelected(WebElement element, String elementName) {
        logger.debug("Asserting element is not selected: {}", elementName);
        if (element.isSelected()) {
            throw new AssertionError("Element '" + elementName + "' is selected but should not be");
        }
    }
    
    /**
     * Assert element text equals expected text
     */
    public static void assertElementTextEquals(WebElement element, String expectedText, String elementName) {
        logger.debug("Asserting element text equals: {} = {}", elementName, expectedText);
        String actualText = element.getText();
        if (!actualText.equals(expectedText)) {
            throw new AssertionError("Element '" + elementName + "' text mismatch. Expected: '" + 
                expectedText + "', Actual: '" + actualText + "'");
        }
    }
    
    /**
     * Assert element text contains expected text
     */
    public static void assertElementTextContains(WebElement element, String expectedText, String elementName) {
        logger.debug("Asserting element text contains: {} contains {}", elementName, expectedText);
        String actualText = element.getText();
        if (!actualText.contains(expectedText)) {
            throw new AssertionError("Element '" + elementName + "' text does not contain expected text. " +
                "Expected to contain: '" + expectedText + "', Actual: '" + actualText + "'");
        }
    }
    
    /**
     * Assert element attribute equals expected value
     */
    public static void assertElementAttributeEquals(WebElement element, String attribute, String expectedValue, String elementName) {
        logger.debug("Asserting element attribute equals: {}[{}] = {}", elementName, attribute, expectedValue);
        String actualValue = element.getAttribute(attribute);
        if (!expectedValue.equals(actualValue)) {
            throw new AssertionError("Element '" + elementName + "' attribute '" + attribute + "' mismatch. " +
                "Expected: '" + expectedValue + "', Actual: '" + actualValue + "'");
        }
    }
    
    /**
     * Assert element attribute contains expected value
     */
    public static void assertElementAttributeContains(WebElement element, String attribute, String expectedValue, String elementName) {
        logger.debug("Asserting element attribute contains: {}[{}] contains {}", elementName, attribute, expectedValue);
        String actualValue = element.getAttribute(attribute);
        if (actualValue == null || !actualValue.contains(expectedValue)) {
            throw new AssertionError("Element '" + elementName + "' attribute '" + attribute + "' does not contain expected value. " +
                "Expected to contain: '" + expectedValue + "', Actual: '" + actualValue + "'");
        }
    }
    
    /**
     * Assert element CSS property equals expected value
     */
    public static void assertElementCssPropertyEquals(WebElement element, String property, String expectedValue, String elementName) {
        logger.debug("Asserting element CSS property equals: {}[{}] = {}", elementName, property, expectedValue);
        String actualValue = element.getCssValue(property);
        if (!expectedValue.equals(actualValue)) {
            throw new AssertionError("Element '" + elementName + "' CSS property '" + property + "' mismatch. " +
                "Expected: '" + expectedValue + "', Actual: '" + actualValue + "'");
        }
    }
    
    /**
     * Assert element CSS property contains expected value
     */
    public static void assertElementCssPropertyContains(WebElement element, String property, String expectedValue, String elementName) {
        logger.debug("Asserting element CSS property contains: {}[{}] contains {}", elementName, property, expectedValue);
        String actualValue = element.getCssValue(property);
        if (actualValue == null || !actualValue.contains(expectedValue)) {
            throw new AssertionError("Element '" + elementName + "' CSS property '" + property + "' does not contain expected value. " +
                "Expected to contain: '" + expectedValue + "', Actual: '" + actualValue + "'");
        }
    }
    
    /**
     * Assert list size equals expected size
     */
    public static void assertListSizeEquals(List<WebElement> elements, int expectedSize, String listName) {
        logger.debug("Asserting list size equals: {} = {}", listName, expectedSize);
        int actualSize = elements.size();
        if (actualSize != expectedSize) {
            throw new AssertionError("List '" + listName + "' size mismatch. Expected: " + 
                expectedSize + ", Actual: " + actualSize);
        }
    }
    
    /**
     * Assert list size is greater than expected size
     */
    public static void assertListSizeGreaterThan(List<WebElement> elements, int expectedSize, String listName) {
        logger.debug("Asserting list size greater than: {} > {}", listName, expectedSize);
        int actualSize = elements.size();
        if (actualSize <= expectedSize) {
            throw new AssertionError("List '" + listName + "' size is not greater than expected. " +
                "Expected: > " + expectedSize + ", Actual: " + actualSize);
        }
    }
    
    /**
     * Assert list size is less than expected size
     */
    public static void assertListSizeLessThan(List<WebElement> elements, int expectedSize, String listName) {
        logger.debug("Asserting list size less than: {} < {}", listName, expectedSize);
        int actualSize = elements.size();
        if (actualSize >= expectedSize) {
            throw new AssertionError("List '" + listName + "' size is not less than expected. " +
                "Expected: < " + expectedSize + ", Actual: " + actualSize);
        }
    }
    
    /**
     * Assert list is not empty
     */
    public static void assertListNotEmpty(List<WebElement> elements, String listName) {
        logger.debug("Asserting list is not empty: {}", listName);
        if (elements.isEmpty()) {
            throw new AssertionError("List '" + listName + "' is empty but should not be");
        }
    }
    
    /**
     * Assert list is empty
     */
    public static void assertListEmpty(List<WebElement> elements, String listName) {
        logger.debug("Asserting list is empty: {}", listName);
        if (!elements.isEmpty()) {
            throw new AssertionError("List '" + listName + "' is not empty but should be. Size: " + elements.size());
        }
    }
    
    /**
     * Assert string equals
     */
    public static void assertStringEquals(String actual, String expected, String message) {
        logger.debug("Asserting string equals: {} = {}", message, expected);
        if (!expected.equals(actual)) {
            throw new AssertionError(message + " - String mismatch. Expected: '" + expected + "', Actual: '" + actual + "'");
        }
    }
    
    /**
     * Assert string contains
     */
    public static void assertStringContains(String actual, String expected, String message) {
        logger.debug("Asserting string contains: {} contains {}", message, expected);
        if (actual == null || !actual.contains(expected)) {
            throw new AssertionError(message + " - String does not contain expected text. Expected to contain: '" + 
                expected + "', Actual: '" + actual + "'");
        }
    }
    
    /**
     * Assert boolean is true
     */
    public static void assertTrue(boolean condition, String message) {
        logger.debug("Asserting true: {}", message);
        if (!condition) {
            throw new AssertionError(message + " - Condition is false but expected to be true");
        }
    }
    
    /**
     * Assert boolean is false
     */
    public static void assertFalse(boolean condition, String message) {
        logger.debug("Asserting false: {}", message);
        if (condition) {
            throw new AssertionError(message + " - Condition is true but expected to be false");
        }
    }
    
    /**
     * Assert integer equals
     */
    public static void assertIntEquals(int actual, int expected, String message) {
        logger.debug("Asserting int equals: {} = {}", message, expected);
        if (actual != expected) {
            throw new AssertionError(message + " - Integer mismatch. Expected: " + expected + ", Actual: " + actual);
        }
    }
    
    /**
     * Assert integer is greater than
     */
    public static void assertIntGreaterThan(int actual, int expected, String message) {
        logger.debug("Asserting int greater than: {} > {}", message, expected);
        if (actual <= expected) {
            throw new AssertionError(message + " - Integer is not greater than expected. Expected: > " + expected + ", Actual: " + actual);
        }
    }
    
    /**
     * Assert integer is less than
     */
    public static void assertIntLessThan(int actual, int expected, String message) {
        logger.debug("Asserting int less than: {} < {}", message, expected);
        if (actual >= expected) {
            throw new AssertionError(message + " - Integer is not less than expected. Expected: < " + expected + ", Actual: " + actual);
        }
    }
}
