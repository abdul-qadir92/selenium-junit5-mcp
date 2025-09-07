package com.example.tests;

import com.example.framework.base.BaseTest;
import com.example.framework.utils.TestDataUtils;
import com.example.pageobjects.GoogleHomePage;
import com.example.pageobjects.GoogleSearchResultsPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Data-driven test class for Google search functionality
 */
@Epic("Google Search")
@Feature("Data-Driven Search")
public class DataDrivenSearchTest extends BaseTest {

    private GoogleHomePage googleHomePage;
    private GoogleSearchResultsPage searchResultsPage;

    @BeforeEach
    @Step("Initialize page objects")
    public void setUpPages() {
        googleHomePage = new GoogleHomePage(driver);
        searchResultsPage = new GoogleSearchResultsPage(driver);
    }

    /**
     * Data source for search test cases from JSON
     */
    static Stream<TestDataUtils.SearchTestCase> searchTestCasesProvider() {
        return TestDataUtils.getSearchTestCases().stream();
    }

    @ParameterizedTest
    @MethodSource("searchTestCasesProvider")
    @DisplayName("Data-driven search test")
    @Description("Test search functionality with data from JSON file")
    @Severity(SeverityLevel.NORMAL)
    @Story("Data-Driven Testing")
    public void testSearchWithData(TestDataUtils.SearchTestCase testCase) {
        // Log test case information
        logger.info("Running test case: {} - {}", testCase.getTestName(), testCase.getDescription());
        
        // Perform search
        googleHomePage.searchFor(testCase.getSearchTerm());
        
        if (testCase.isExpectedResults()) {
            // Wait for search results to load
            searchResultsPage.waitForSearchResults();
            
            // Verify search results are displayed
            assertThat(searchResultsPage.areSearchResultsDisplayed())
                .as("Search results should be displayed for: " + testCase.getSearchTerm())
                .isTrue();
            
            // Verify we have search results
            int resultCount = searchResultsPage.getSearchResultCount();
            assertThat(resultCount)
                .as("Should have search results for: " + testCase.getSearchTerm())
                .isGreaterThan(0);
        } else {
            // For cases where no results are expected (like empty search)
            // Verify we remain on Google homepage
            String currentUrl = getCurrentUrl();
            assertThat(currentUrl)
                .as("Should remain on Google homepage for: " + testCase.getSearchTerm())
                .contains("google.com");
        }
    }

    @Test
    @DisplayName("Test with multiple search terms")
    @Description("Test search functionality with multiple search terms")
    @Severity(SeverityLevel.NORMAL)
    @Story("Multiple Search Terms")
    public void testMultipleSearchTerms() {
        String[] searchTerms = {
            "Selenium WebDriver",
            "JUnit 5",
            "Test Automation",
            "Maven",
            "Allure Reporting"
        };

        for (String searchTerm : searchTerms) {
            logger.info("Testing search term: {}", searchTerm);
            
            // Navigate back to Google homepage
            navigateToUrl("https://www.google.com");
            
            // Perform search
            googleHomePage.searchFor(searchTerm);
            
            // Wait for search results
            searchResultsPage.waitForSearchResults();
            
            // Verify results
            assertThat(searchResultsPage.areSearchResultsDisplayed())
                .as("Search results should be displayed for: " + searchTerm)
                .isTrue();
            
            int resultCount = searchResultsPage.getSearchResultCount();
            assertThat(resultCount)
                .as("Should have search results for: " + searchTerm)
                .isGreaterThan(0);
        }
    }

    @Test
    @DisplayName("Test search result titles")
    @Description("Verify that search result titles are not empty")
    @Severity(SeverityLevel.MINOR)
    @Story("Search Result Validation")
    public void testSearchResultTitles() {
        String searchTerm = "Selenium WebDriver";
        
        // Perform search
        googleHomePage.searchFor(searchTerm);
        
        // Wait for search results
        searchResultsPage.waitForSearchResults();
        
        // Get all search result titles
        List<String> titles = searchResultsPage.getAllSearchResultTitles();
        
        // Verify we have titles
        assertThat(titles)
            .as("Should have search result titles")
            .isNotEmpty();
        
        // Verify titles are not empty
        for (String title : titles) {
            assertThat(title)
                .as("Search result title should not be empty")
                .isNotEmpty();
        }
        
        logger.info("Found {} search result titles", titles.size());
    }

    @Test
    @DisplayName("Test search with special characters")
    @Description("Test search functionality with various special characters")
    @Severity(SeverityLevel.NORMAL)
    @Story("Special Characters")
    public void testSearchWithSpecialCharacters() {
        String[] specialSearchTerms = {
            "test@example.com",
            "file://path/to/file",
            "https://www.example.com",
            "user+name@domain.com",
            "test & development",
            "price $100",
            "50% discount"
        };

        for (String searchTerm : specialSearchTerms) {
            logger.info("Testing special character search: {}", searchTerm);
            
            // Navigate back to Google homepage
            navigateToUrl("https://www.google.com");
            
            // Perform search
            googleHomePage.searchFor(searchTerm);
            
            // Wait for search results
            searchResultsPage.waitForSearchResults();
            
            // Verify results are displayed
            assertThat(searchResultsPage.areSearchResultsDisplayed())
                .as("Search results should be displayed for special characters: " + searchTerm)
                .isTrue();
        }
    }
}
