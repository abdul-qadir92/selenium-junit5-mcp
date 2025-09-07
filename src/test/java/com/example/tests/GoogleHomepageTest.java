package com.example.tests;

import com.example.framework.base.BaseTest;
import com.example.pageobjects.GoogleHomePage;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for Google homepage functionality
 */
@Epic("Google Homepage")
@Feature("Homepage Elements")
public class GoogleHomepageTest extends BaseTest {

    private GoogleHomePage googleHomePage;

    @BeforeEach
    @Step("Initialize page objects")
    public void setUpPages() {
        googleHomePage = new GoogleHomePage(driver);
    }

    @Test
    @DisplayName("Verify Google logo is displayed")
    @Description("Test that Google logo is visible on the homepage")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Logo Display")
    public void testGoogleLogoDisplayed() {
        assertThat(googleHomePage.isGoogleLogoDisplayed())
            .as("Google logo should be displayed")
            .isTrue();
    }

    @Test
    @DisplayName("Verify search box is displayed and functional")
    @Description("Test that search box is visible and can accept input")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Search Box")
    public void testSearchBoxDisplayed() {
        assertThat(googleHomePage.isSearchBoxDisplayed())
            .as("Search box should be displayed")
            .isTrue();
        
        // Test that search box can accept input
        String testText = "Test input";
        googleHomePage.enterSearchTerm(testText);
        
        String actualText = googleHomePage.getSearchBoxText();
        assertThat(actualText)
            .as("Search box should contain the entered text")
            .isEqualTo(testText);
    }

    @Test
    @DisplayName("Verify search button is displayed")
    @Description("Test that search button is visible on the homepage")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Search Button")
    public void testSearchButtonDisplayed() {
        assertThat(googleHomePage.isSearchButtonDisplayed())
            .as("Search button should be displayed")
            .isTrue();
    }

    @Test
    @DisplayName("Verify page title contains Google")
    @Description("Test that page title contains the word 'Google'")
    @Severity(SeverityLevel.NORMAL)
    @Story("Page Title")
    public void testPageTitle() {
        String pageTitle = getPageTitle();
        assertThat(pageTitle)
            .as("Page title should contain 'Google'")
            .contains("Google");
    }

    @Test
    @DisplayName("Verify search box placeholder text")
    @Description("Test that search box has appropriate placeholder text")
    @Severity(SeverityLevel.MINOR)
    @Story("Search Box Placeholder")
    public void testSearchBoxPlaceholder() {
        String placeholder = googleHomePage.getSearchBoxPlaceholder();
        assertThat(placeholder)
            .as("Search box should have placeholder text")
            .isNotEmpty();
    }

    @Test
    @DisplayName("Test search box clear functionality")
    @Description("Test that search box can be cleared")
    @Severity(SeverityLevel.MINOR)
    @Story("Search Box Functionality")
    public void testSearchBoxClear() {
        // Enter some text
        String testText = "Test text to clear";
        googleHomePage.enterSearchTerm(testText);
        
        // Verify text was entered
        String actualText = googleHomePage.getSearchBoxText();
        assertThat(actualText)
            .as("Search box should contain the entered text")
            .isEqualTo(testText);
        
        // Clear the search box
        googleHomePage.clearSearchBox();
        
        // Verify search box is cleared
        String clearedText = googleHomePage.getSearchBoxText();
        assertThat(clearedText)
            .as("Search box should be empty after clearing")
            .isEmpty();
    }

    @Test
    @DisplayName("Verify page loads completely")
    @Description("Test that all essential elements are loaded on the page")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Page Loading")
    public void testPageLoadsCompletely() {
        // Wait for page to load completely
        googleHomePage.waitForPageToLoad();
        
        // Verify all essential elements are present
        assertThat(googleHomePage.isGoogleLogoDisplayed())
            .as("Google logo should be displayed")
            .isTrue();
        
        assertThat(googleHomePage.isSearchBoxDisplayed())
            .as("Search box should be displayed")
            .isTrue();
        
        assertThat(googleHomePage.isSearchButtonDisplayed())
            .as("Search button should be displayed")
            .isTrue();
    }

    @Test
    @DisplayName("Test multiple text entries in search box")
    @Description("Test that search box can handle multiple text entries")
    @Severity(SeverityLevel.MINOR)
    @Story("Search Box Functionality")
    public void testMultipleTextEntries() {
        String[] testTexts = {"First text", "Second text", "Third text"};
        
        for (String text : testTexts) {
            googleHomePage.enterSearchTerm(text);
            String actualText = googleHomePage.getSearchBoxText();
            assertThat(actualText)
                .as("Search box should contain: " + text)
                .isEqualTo(text);
        }
    }
}
