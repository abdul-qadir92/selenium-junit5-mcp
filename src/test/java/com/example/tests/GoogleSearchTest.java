package com.example.tests;

import com.example.framework.base.BaseTest;
import com.example.pageobjects.GoogleHomePage;
import com.example.pageobjects.GoogleSearchResultsPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for Google search functionality
 */
@Epic("Google Search")
@Feature("Search Functionality")
public class GoogleSearchTest extends BaseTest {

    private GoogleHomePage googleHomePage;
    private GoogleSearchResultsPage searchResultsPage;

    @BeforeEach
    @Step("Initialize page objects")
    public void setUpPages() {
        googleHomePage = new GoogleHomePage(driver);
        searchResultsPage = new GoogleSearchResultsPage(driver);
    }

    @Test
    @DisplayName("Verify Google homepage loads correctly")
    @Description("Test that Google homepage loads with all essential elements visible")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Homepage Loading")
    public void testGoogleHomepageLoads() {
        // Verify essential elements are displayed
        assertThat(googleHomePage.isGoogleLogoDisplayed())
            .as("Google logo should be displayed")
            .isTrue();
        
        assertThat(googleHomePage.isSearchBoxDisplayed())
            .as("Search box should be displayed")
            .isTrue();
        
        assertThat(googleHomePage.isSearchButtonDisplayed())
            .as("Search button should be displayed")
            .isTrue();
        
        // Verify page title
        String pageTitle = getPageTitle();
        assertThat(pageTitle)
            .as("Page title should contain 'Google'")
            .contains("Google");
    }

    @Test
    @DisplayName("Perform basic search functionality")
    @Description("Test basic search functionality with a simple search term")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Basic Search")
    public void testBasicSearch() {
        String searchTerm = "Selenium WebDriver";
        
        // Perform search
        googleHomePage.searchFor(searchTerm);
        
        // Wait for search results to load
        searchResultsPage.waitForSearchResults();
        
        // Verify search results are displayed
        assertThat(searchResultsPage.areSearchResultsDisplayed())
            .as("Search results should be displayed")
            .isTrue();
        
        // Verify we have search results
        int resultCount = searchResultsPage.getSearchResultCount();
        assertThat(resultCount)
            .as("Should have at least one search result")
            .isGreaterThan(0);
        
        // Verify search term appears in results
        String firstResultTitle = searchResultsPage.getFirstSearchResultTitle();
        assertThat(firstResultTitle)
            .as("First search result should not be empty")
            .isNotEmpty();
    }

    @ParameterizedTest
    @ValueSource(strings = {"JUnit 5", "Selenium", "Test Automation", "Maven"})
    @DisplayName("Search with different terms")
    @Description("Test search functionality with various search terms")
    @Severity(SeverityLevel.NORMAL)
    @Story("Parameterized Search")
    public void testSearchWithDifferentTerms(String searchTerm) {
        // Perform search
        googleHomePage.searchFor(searchTerm);
        
        // Wait for search results to load
        searchResultsPage.waitForSearchResults();
        
        // Verify search results are displayed
        assertThat(searchResultsPage.areSearchResultsDisplayed())
            .as("Search results should be displayed for term: " + searchTerm)
            .isTrue();
        
        // Verify we have search results
        int resultCount = searchResultsPage.getSearchResultCount();
        assertThat(resultCount)
            .as("Should have search results for term: " + searchTerm)
            .isGreaterThan(0);
    }

    @Test
    @DisplayName("Verify search box placeholder text")
    @Description("Test that search box has correct placeholder text")
    @Severity(SeverityLevel.MINOR)
    @Story("UI Elements")
    public void testSearchBoxPlaceholder() {
        String placeholder = googleHomePage.getSearchBoxPlaceholder();
        assertThat(placeholder)
            .as("Search box should have placeholder text")
            .isNotEmpty();
    }

    @Test
    @DisplayName("Test search with empty string")
    @Description("Test behavior when searching with empty string")
    @Severity(SeverityLevel.NORMAL)
    @Story("Edge Cases")
    public void testSearchWithEmptyString() {
        // Enter empty string and search
        googleHomePage.enterSearchTerm("");
        googleHomePage.clickSearchButton();
        
        // Should still be on Google homepage
        String currentUrl = getCurrentUrl();
        assertThat(currentUrl)
            .as("Should remain on Google homepage")
            .contains("google.com");
    }

    @Test
    @DisplayName("Test search with special characters")
    @Description("Test search functionality with special characters")
    @Severity(SeverityLevel.NORMAL)
    @Story("Edge Cases")
    public void testSearchWithSpecialCharacters() {
        String searchTerm = "!@#$%^&*()";
        
        // Perform search
        googleHomePage.searchFor(searchTerm);
        
        // Wait for search results to load
        searchResultsPage.waitForSearchResults();
        
        // Verify search results are displayed
        assertThat(searchResultsPage.areSearchResultsDisplayed())
            .as("Search results should be displayed even with special characters")
            .isTrue();
    }

    @Test
    @DisplayName("Test search result navigation")
    @Description("Test clicking on search results")
    @Severity(SeverityLevel.NORMAL)
    @Story("Search Navigation")
    public void testSearchResultNavigation() {
        String searchTerm = "Selenium documentation";
        
        // Perform search
        googleHomePage.searchFor(searchTerm);
        
        // Wait for search results to load
        searchResultsPage.waitForSearchResults();
        
        // Verify search results are displayed
        assertThat(searchResultsPage.areSearchResultsDisplayed())
            .as("Search results should be displayed")
            .isTrue();
        
        // Get first result title before clicking
        String firstResultTitle = searchResultsPage.getFirstSearchResultTitle();
        assertThat(firstResultTitle)
            .as("First search result should have a title")
            .isNotEmpty();
        
        // Click on first result
        searchResultsPage.clickFirstSearchResult();
        
        // Verify we navigated away from Google search results
        String currentUrl = getCurrentUrl();
        assertThat(currentUrl)
            .as("Should navigate to a different page")
            .doesNotContain("google.com/search");
    }
}
