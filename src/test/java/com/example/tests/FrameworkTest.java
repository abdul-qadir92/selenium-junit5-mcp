package com.example.tests;

import com.example.framework.base.BaseTest;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Basic framework test to verify the setup is working
 */
@Epic("Framework")
@Feature("Basic Setup")
public class FrameworkTest extends BaseTest {

    @BeforeEach
    public void setUp() {
        super.setUp();
    }

    @AfterEach
    public void tearDown() {
        super.tearDown();
    }

    @Test
    @DisplayName("Verify framework setup")
    @Description("Test that the framework is properly configured and can load a webpage")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Framework Setup")
    public void testFrameworkSetup() {
        // Verify driver is initialized
        assertThat(driver).isNotNull();
        
        // Verify we can get page title
        String pageTitle = getPageTitle();
        assertThat(pageTitle).isNotEmpty();
        
        // Verify we can get current URL
        String currentUrl = getCurrentUrl();
        assertThat(currentUrl).isNotEmpty();
        assertThat(currentUrl).contains("google.com");
        
        // Verify we can navigate to a different URL
        navigateToUrl("https://www.example.com");
        String newUrl = getCurrentUrl();
        assertThat(newUrl).contains("example.com");
    }

    @Test
    @DisplayName("Verify configuration loading")
    @Description("Test that configuration is loaded correctly")
    @Severity(SeverityLevel.NORMAL)
    @Story("Configuration")
    public void testConfigurationLoading() {
        // Verify configuration is loaded
        assertThat(config).isNotNull();
        
        // Verify basic configuration values
        assertThat(config.getBrowser()).isNotEmpty();
        assertThat(config.getBaseUrl()).isNotEmpty();
        assertThat(config.getImplicitWait()).isGreaterThan(0);
        assertThat(config.getExplicitWait()).isGreaterThan(0);
    }
}
