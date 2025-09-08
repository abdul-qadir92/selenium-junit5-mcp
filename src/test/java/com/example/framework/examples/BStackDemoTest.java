package com.example.framework.examples;

import com.example.framework.base.BaseTest;
import com.example.framework.utils.AssertUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Basic test class for BStackDemo website
 * This test verifies the page title without using page objects
 */
@DisplayName("BStackDemo Basic Test")
public class BStackDemoTest extends BaseTest {

    @Test
    @DisplayName("Verify BStackDemo page title is StackDemo")
    public void testBStackDemoPageTitle() {
        // Setup WebDriver
        setUp();
        
        // Navigate to BStackDemo website
        navigateToUrl("https://bstackdemo.com");
        
        // Get the page title
        String pageTitle = getPageTitle();
        
        // Verify the page title is "StackDemo"
        AssertUtils.assertStringEquals(pageTitle, "StackDemo", "Page title should be StackDemo");
        
        // Additional verification - check if the page loaded properly
        String currentUrl = getCurrentUrl();
        AssertUtils.assertTrue(currentUrl.contains("bstackdemo.com"), "Should be on BStackDemo website");
        
        // Log the verification
        logger.info("Successfully verified BStackDemo page title: {}", pageTitle);
        
        // Cleanup
        tearDown();
    }
    
}
