package com.example.framework.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Utility class for common WebElement operations
 */
public class ElementUtils {
    
    private static final Logger logger = LoggerFactory.getLogger(ElementUtils.class);
    private final WebDriver driver;
    private final JavascriptExecutor jsExecutor;
    private final Actions actions;
    
    public ElementUtils(WebDriver driver) {
        this.driver = driver;
        this.jsExecutor = (JavascriptExecutor) driver;
        this.actions = new Actions(driver);
    }
    
    /**
     * Click element using JavaScript
     */
    public void clickElementWithJS(WebElement element) {
        logger.debug("Clicking element using JavaScript");
        jsExecutor.executeScript("arguments[0].click();", element);
    }
    
    /**
     * Scroll element into view
     */
    public void scrollToElement(WebElement element) {
        logger.debug("Scrolling to element");
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }
    
    /**
     * Scroll to top of page
     */
    public void scrollToTop() {
        logger.debug("Scrolling to top of page");
        jsExecutor.executeScript("window.scrollTo(0, 0);");
    }
    
    /**
     * Scroll to bottom of page
     */
    public void scrollToBottom() {
        logger.debug("Scrolling to bottom of page");
        jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }
    
    /**
     * Scroll by specific amount
     */
    public void scrollBy(int x, int y) {
        logger.debug("Scrolling by x: {}, y: {}", x, y);
        jsExecutor.executeScript("window.scrollBy(arguments[0], arguments[1]);", x, y);
    }
    
    /**
     * Highlight element with border
     */
    public void highlightElement(WebElement element) {
        logger.debug("Highlighting element");
        jsExecutor.executeScript("arguments[0].style.border='3px solid red'", element);
    }
    
    /**
     * Remove highlight from element
     */
    public void removeHighlight(WebElement element) {
        logger.debug("Removing highlight from element");
        jsExecutor.executeScript("arguments[0].style.border=''", element);
    }
    
    /**
     * Get element text using JavaScript
     */
    public String getElementTextWithJS(WebElement element) {
        logger.debug("Getting element text using JavaScript");
        return (String) jsExecutor.executeScript("return arguments[0].textContent;", element);
    }
    
    /**
     * Set element value using JavaScript
     */
    public void setElementValueWithJS(WebElement element, String value) {
        logger.debug("Setting element value using JavaScript: {}", value);
        jsExecutor.executeScript("arguments[0].value = arguments[1];", element, value);
    }
    
    /**
     * Get element attribute using JavaScript
     */
    public String getElementAttributeWithJS(WebElement element, String attribute) {
        logger.debug("Getting element attribute using JavaScript: {}", attribute);
        return (String) jsExecutor.executeScript("return arguments[0].getAttribute(arguments[1]);", element, attribute);
    }
    
    /**
     * Check if element is displayed using JavaScript
     */
    public boolean isElementDisplayedWithJS(WebElement element) {
        logger.debug("Checking if element is displayed using JavaScript");
        return (Boolean) jsExecutor.executeScript("return arguments[0].offsetParent !== null;", element);
    }
    
    /**
     * Double click element
     */
    public void doubleClickElement(WebElement element) {
        logger.debug("Double clicking element");
        actions.doubleClick(element).perform();
    }
    
    /**
     * Right click element
     */
    public void rightClickElement(WebElement element) {
        logger.debug("Right clicking element");
        actions.contextClick(element).perform();
    }
    
    /**
     * Hover over element
     */
    public void hoverOverElement(WebElement element) {
        logger.debug("Hovering over element");
        actions.moveToElement(element).perform();
    }
    
    /**
     * Drag and drop element
     */
    public void dragAndDrop(WebElement source, WebElement target) {
        logger.debug("Dragging and dropping element");
        actions.dragAndDrop(source, target).perform();
    }
    
    /**
     * Drag and drop by offset
     */
    public void dragAndDropByOffset(WebElement source, int xOffset, int yOffset) {
        logger.debug("Dragging and dropping element by offset: x={}, y={}", xOffset, yOffset);
        actions.dragAndDropBy(source, xOffset, yOffset).perform();
    }
    
    /**
     * Select option from dropdown by visible text
     */
    public void selectByVisibleText(WebElement dropdown, String visibleText) {
        logger.debug("Selecting option by visible text: {}", visibleText);
        Select select = new Select(dropdown);
        select.selectByVisibleText(visibleText);
    }
    
    /**
     * Select option from dropdown by value
     */
    public void selectByValue(WebElement dropdown, String value) {
        logger.debug("Selecting option by value: {}", value);
        Select select = new Select(dropdown);
        select.selectByValue(value);
    }
    
    /**
     * Select option from dropdown by index
     */
    public void selectByIndex(WebElement dropdown, int index) {
        logger.debug("Selecting option by index: {}", index);
        Select select = new Select(dropdown);
        select.selectByIndex(index);
    }
    
    /**
     * Get selected option text from dropdown
     */
    public String getSelectedOptionText(WebElement dropdown) {
        logger.debug("Getting selected option text from dropdown");
        Select select = new Select(dropdown);
        return select.getFirstSelectedOption().getText();
    }
    
    /**
     * Get all options from dropdown
     */
    public List<WebElement> getAllOptions(WebElement dropdown) {
        logger.debug("Getting all options from dropdown");
        Select select = new Select(dropdown);
        return select.getOptions();
    }
    
    /**
     * Check if dropdown is multiple select
     */
    public boolean isMultipleSelect(WebElement dropdown) {
        logger.debug("Checking if dropdown is multiple select");
        Select select = new Select(dropdown);
        return select.isMultiple();
    }
    
    /**
     * Deselect all options from dropdown
     */
    public void deselectAll(WebElement dropdown) {
        logger.debug("Deselecting all options from dropdown");
        Select select = new Select(dropdown);
        select.deselectAll();
    }
    
    /**
     * Deselect option by visible text
     */
    public void deselectByVisibleText(WebElement dropdown, String visibleText) {
        logger.debug("Deselecting option by visible text: {}", visibleText);
        Select select = new Select(dropdown);
        select.deselectByVisibleText(visibleText);
    }
    
    /**
     * Deselect option by value
     */
    public void deselectByValue(WebElement dropdown, String value) {
        logger.debug("Deselecting option by value: {}", value);
        Select select = new Select(dropdown);
        select.deselectByValue(value);
    }
    
    /**
     * Deselect option by index
     */
    public void deselectByIndex(WebElement dropdown, int index) {
        logger.debug("Deselecting option by index: {}", index);
        Select select = new Select(dropdown);
        select.deselectByIndex(index);
    }
    
    /**
     * Get all selected options from dropdown
     */
    public List<WebElement> getAllSelectedOptions(WebElement dropdown) {
        logger.debug("Getting all selected options from dropdown");
        Select select = new Select(dropdown);
        return select.getAllSelectedOptions();
    }
    
    /**
     * Switch to frame by index
     */
    public void switchToFrame(int frameIndex) {
        logger.debug("Switching to frame by index: {}", frameIndex);
        driver.switchTo().frame(frameIndex);
    }
    
    /**
     * Switch to frame by name or ID
     */
    public void switchToFrame(String frameNameOrId) {
        logger.debug("Switching to frame by name or ID: {}", frameNameOrId);
        driver.switchTo().frame(frameNameOrId);
    }
    
    /**
     * Switch to frame by WebElement
     */
    public void switchToFrame(WebElement frameElement) {
        logger.debug("Switching to frame by WebElement");
        driver.switchTo().frame(frameElement);
    }
    
    /**
     * Switch to default content
     */
    public void switchToDefaultContent() {
        logger.debug("Switching to default content");
        driver.switchTo().defaultContent();
    }
    
    /**
     * Switch to parent frame
     */
    public void switchToParentFrame() {
        logger.debug("Switching to parent frame");
        driver.switchTo().parentFrame();
    }
    
    /**
     * Switch to alert and accept
     */
    public void acceptAlert() {
        logger.debug("Accepting alert");
        driver.switchTo().alert().accept();
    }
    
    /**
     * Switch to alert and dismiss
     */
    public void dismissAlert() {
        logger.debug("Dismissing alert");
        driver.switchTo().alert().dismiss();
    }
    
    /**
     * Get alert text
     */
    public String getAlertText() {
        logger.debug("Getting alert text");
        return driver.switchTo().alert().getText();
    }
    
    /**
     * Send text to alert
     */
    public void sendTextToAlert(String text) {
        logger.debug("Sending text to alert: {}", text);
        driver.switchTo().alert().sendKeys(text);
    }
    
    /**
     * Execute JavaScript
     */
    public Object executeJavaScript(String script, Object... args) {
        logger.debug("Executing JavaScript: {}", script);
        return jsExecutor.executeScript(script, args);
    }
    
    /**
     * Execute JavaScript asynchronously
     */
    public Object executeAsyncJavaScript(String script, Object... args) {
        logger.debug("Executing asynchronous JavaScript: {}", script);
        return jsExecutor.executeAsyncScript(script, args);
    }
}
