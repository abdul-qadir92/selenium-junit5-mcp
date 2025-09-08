package com.example.framework.examples;

import com.example.framework.base.BaseTest;
import com.example.framework.pages.BasePage;
import com.example.framework.utils.ConfigManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Example test class demonstrating framework usage
 * This is just an example - actual test classes should be created as needed
 */
@DisplayName("Framework Example Test")
public class FrameworkExampleTest extends BaseTest {

    @Disabled
    @Test
    @DisplayName("Example test demonstrating framework capabilities")
    public void exampleTest() {
        // This is just an example - replace with actual test logic
        setUp();
        
        // Navigate to base URL
        navigateToBaseUrl();
        
        // Example of using the framework utilities
        String pageTitle = getPageTitle();
        String currentUrl = getCurrentUrl();
        
        // Example of using configuration
        String baseUrl = ConfigManager.getBaseUrl();
        
        // Example of creating a simple page object
        ExamplePage page = new ExamplePage();
        
        // Example test logic would go here
        // page.performSomeAction();
        
        tearDown();
    }
    
    /**
     * Example page class demonstrating page object pattern
     */
    private static class ExamplePage extends BasePage {
        
        // Example locators
        private final By searchBox = By.name("q");
        private final By searchButton = By.name("btnK");
        
        public void performSearch(String query) {
            // Example of using framework utilities
            WebElement searchElement = waitUtils.waitForElementToBeVisible(searchBox);
            sendText(searchElement, query, "Search Box");
            
            WebElement buttonElement = waitUtils.waitForElementToBeClickable(searchButton);
            clickElement(buttonElement, "Search Button");
        }
    }
}
