package com.example.pageobjects;

import com.example.framework.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object for Google Home Page
 */
public class GoogleHomePage extends BasePage {
    
    // Page Elements using @FindBy annotations
    @FindBy(name = "q")
    private WebElement searchBox;
    
    @FindBy(name = "btnK")
    private WebElement searchButton;
    
    @FindBy(name = "btnI")
    private WebElement luckyButton;
    
    @FindBy(id = "logo")
    private WebElement googleLogo;
    
    // Alternative locators using By
    private static final By SEARCH_BOX_LOCATOR = By.name("q");
    private static final By SEARCH_BUTTON_LOCATOR = By.name("btnK");
    private static final By LUCKY_BUTTON_LOCATOR = By.name("btnI");
    private static final By GOOGLE_LOGO_LOCATOR = By.id("logo");

    public GoogleHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Enter search term in the search box
     */
    public void enterSearchTerm(String searchTerm) {
        logger.info("Entering search term: {}", searchTerm);
        sendKeys(SEARCH_BOX_LOCATOR, searchTerm);
    }

    /**
     * Click the search button
     */
    public void clickSearchButton() {
        logger.info("Clicking search button");
        click(SEARCH_BUTTON_LOCATOR);
    }

    /**
     * Click the "I'm Feeling Lucky" button
     */
    public void clickLuckyButton() {
        logger.info("Clicking 'I'm Feeling Lucky' button");
        click(LUCKY_BUTTON_LOCATOR);
    }

    /**
     * Perform a search with the given term
     */
    public void searchFor(String searchTerm) {
        logger.info("Performing search for: {}", searchTerm);
        enterSearchTerm(searchTerm);
        clickSearchButton();
    }

    /**
     * Check if the search box is displayed
     */
    public boolean isSearchBoxDisplayed() {
        return isDisplayed(SEARCH_BOX_LOCATOR);
    }

    /**
     * Check if the search button is displayed
     */
    public boolean isSearchButtonDisplayed() {
        return isDisplayed(SEARCH_BUTTON_LOCATOR);
    }

    /**
     * Check if the Google logo is displayed
     */
    public boolean isGoogleLogoDisplayed() {
        return isDisplayed(GOOGLE_LOGO_LOCATOR);
    }

    /**
     * Get the placeholder text of the search box
     */
    public String getSearchBoxPlaceholder() {
        return getAttribute(SEARCH_BOX_LOCATOR, "placeholder");
    }

    /**
     * Get the text from the search box
     */
    public String getSearchBoxText() {
        return getAttribute(SEARCH_BOX_LOCATOR, "value");
    }

    /**
     * Clear the search box
     */
    public void clearSearchBox() {
        logger.info("Clearing search box");
        WebElement searchBoxElement = findElement(SEARCH_BOX_LOCATOR);
        searchBoxElement.clear();
    }

    /**
     * Wait for the page to load completely
     */
    public void waitForPageToLoad() {
        waitForElementVisible(SEARCH_BOX_LOCATOR);
        waitForElementVisible(SEARCH_BUTTON_LOCATOR);
    }
}
