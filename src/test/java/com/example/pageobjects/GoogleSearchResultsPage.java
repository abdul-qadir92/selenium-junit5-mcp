package com.example.pageobjects;

import com.example.framework.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Page Object for Google Search Results Page
 */
public class GoogleSearchResultsPage extends BasePage {
    
    // Page Elements using @FindBy annotations
    @FindBy(id = "search")
    private WebElement searchResultsContainer;
    
    @FindBy(css = "h3")
    private List<WebElement> searchResultTitles;
    
    @FindBy(css = ".g")
    private List<WebElement> searchResultItems;
    
    @FindBy(id = "result-stats")
    private WebElement resultStats;
    
    @FindBy(css = "a[aria-label='Next page']")
    private WebElement nextPageButton;
    
    // Alternative locators using By
    private static final By SEARCH_RESULTS_CONTAINER_LOCATOR = By.id("search");
    private static final By SEARCH_RESULT_TITLES_LOCATOR = By.cssSelector("h3");
    private static final By SEARCH_RESULT_ITEMS_LOCATOR = By.cssSelector(".g");
    private static final By RESULT_STATS_LOCATOR = By.id("result-stats");
    private static final By NEXT_PAGE_BUTTON_LOCATOR = By.cssSelector("a[aria-label='Next page']");

    public GoogleSearchResultsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Wait for search results to load
     */
    public void waitForSearchResults() {
        logger.info("Waiting for search results to load");
        waitForElementVisible(SEARCH_RESULTS_CONTAINER_LOCATOR);
    }

    /**
     * Get the number of search results displayed
     */
    public int getSearchResultCount() {
        List<WebElement> results = findElements(SEARCH_RESULT_ITEMS_LOCATOR);
        int count = results.size();
        logger.info("Found {} search results", count);
        return count;
    }

    /**
     * Get all search result titles
     */
    public List<String> getAllSearchResultTitles() {
        List<WebElement> titleElements = findElements(SEARCH_RESULT_TITLES_LOCATOR);
        List<String> titles = titleElements.stream()
                .map(WebElement::getText)
                .filter(text -> !text.isEmpty())
                .collect(java.util.stream.Collectors.toList());
        logger.info("Retrieved {} search result titles", titles.size());
        return titles;
    }

    /**
     * Get the first search result title
     */
    public String getFirstSearchResultTitle() {
        List<String> titles = getAllSearchResultTitles();
        if (!titles.isEmpty()) {
            String firstTitle = titles.get(0);
            logger.info("First search result title: {}", firstTitle);
            return firstTitle;
        }
        return "";
    }

    /**
     * Click on the first search result
     */
    public void clickFirstSearchResult() {
        logger.info("Clicking on first search result");
        List<WebElement> resultItems = findElements(SEARCH_RESULT_ITEMS_LOCATOR);
        if (!resultItems.isEmpty()) {
            WebElement firstResult = resultItems.get(0);
            WebElement link = firstResult.findElement(By.cssSelector("a"));
            link.click();
        }
    }

    /**
     * Click on a specific search result by index
     */
    public void clickSearchResultByIndex(int index) {
        logger.info("Clicking on search result at index: {}", index);
        List<WebElement> resultItems = findElements(SEARCH_RESULT_ITEMS_LOCATOR);
        if (index < resultItems.size()) {
            WebElement resultItem = resultItems.get(index);
            WebElement link = resultItem.findElement(By.cssSelector("a"));
            link.click();
        } else {
            logger.warn("Search result index {} is out of bounds", index);
        }
    }

    /**
     * Get the result statistics text
     */
    public String getResultStats() {
        try {
            String stats = getText(RESULT_STATS_LOCATOR);
            logger.info("Result stats: {}", stats);
            return stats;
        } catch (Exception e) {
            logger.warn("Could not retrieve result stats", e);
            return "";
        }
    }

    /**
     * Check if next page button is available
     */
    public boolean isNextPageAvailable() {
        return isDisplayed(NEXT_PAGE_BUTTON_LOCATOR);
    }

    /**
     * Click on next page button
     */
    public void clickNextPage() {
        if (isNextPageAvailable()) {
            logger.info("Clicking next page button");
            click(NEXT_PAGE_BUTTON_LOCATOR);
        } else {
            logger.warn("Next page button is not available");
        }
    }

    /**
     * Check if search results are displayed
     */
    public boolean areSearchResultsDisplayed() {
        return isDisplayed(SEARCH_RESULTS_CONTAINER_LOCATOR);
    }

    /**
     * Get search result by title text (case-insensitive)
     */
    public WebElement getSearchResultByTitle(String titleText) {
        List<WebElement> resultItems = findElements(SEARCH_RESULT_ITEMS_LOCATOR);
        for (WebElement resultItem : resultItems) {
            try {
                WebElement titleElement = resultItem.findElement(By.cssSelector("h3"));
                if (titleElement.getText().toLowerCase().contains(titleText.toLowerCase())) {
                    logger.info("Found search result with title containing: {}", titleText);
                    return resultItem;
                }
            } catch (Exception e) {
                // Continue searching
            }
        }
        logger.warn("No search result found with title containing: {}", titleText);
        return null;
    }

    /**
     * Click on search result by title text
     */
    public void clickSearchResultByTitle(String titleText) {
        WebElement resultItem = getSearchResultByTitle(titleText);
        if (resultItem != null) {
            WebElement link = resultItem.findElement(By.cssSelector("a"));
            link.click();
            logger.info("Clicked on search result with title containing: {}", titleText);
        } else {
            logger.warn("Could not click on search result with title containing: {}", titleText);
        }
    }
}
