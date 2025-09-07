# Selenium JUnit5 MCP Framework

A comprehensive Selenium testing framework built with JUnit5, featuring Page Object Model, WebDriver management, and extensive utility classes for robust test automation.

## Framework Structure

```
src/test/java/com/example/framework/
├── base/
│   └── BaseTest.java                 # Base test class with common functionality
├── hooks/
│   ├── TestHooks.java               # JUnit5 lifecycle hooks and test management
│   └── WebDriverHooks.java          # WebDriver-specific lifecycle management
├── managers/
│   └── WebDriverManager.java        # Singleton WebDriver management
├── pages/
│   ├── BasePage.java                # Base page class with common page operations
│   └── PageFactory.java             # Custom Page Factory for page object creation
└── utils/
    ├── AssertUtils.java             # Custom assertion utilities
    ├── ConfigManager.java           # Configuration management
    ├── ElementUtils.java            # WebElement utility operations
    └── WaitUtils.java               # Wait and synchronization utilities
```

## Features

### 🚀 Core Features
- **JUnit5 Integration**: Full JUnit5 support with extensions and lifecycle hooks
- **Page Object Model**: Clean separation of test logic and page interactions
- **WebDriver Management**: Singleton pattern for efficient WebDriver lifecycle management
- **Multi-Browser Support**: Chrome, Firefox, Edge, Safari, and Remote WebDriver support
- **Configuration Management**: Centralized configuration with properties file support
- **Comprehensive Utilities**: Extensive utility classes for common test operations

### 🛠️ Utility Classes

#### WaitUtils
- Element visibility and clickability waits
- Custom condition waits
- Alert and frame handling waits
- URL and title change waits

#### ElementUtils
- JavaScript execution utilities
- Advanced interactions (hover, drag-drop, double-click)
- Dropdown handling
- Frame and alert management

#### AssertUtils
- Custom assertion methods for WebElements
- List and collection assertions
- String and numeric assertions
- Element state validations

#### ConfigManager
- Properties file management
- Environment-specific configurations
- Default value handling
- Type-safe property access

### 🎯 Hooks and Lifecycle Management

#### TestHooks
- Automatic WebDriver initialization and cleanup
- Screenshot capture on test failure
- Page source attachment for debugging
- Allure integration for reporting

#### WebDriverHooks
- WebDriver-specific lifecycle management
- Thread-safe WebDriver handling
- Automatic cleanup after each test

## Configuration

### Browser Configuration
```properties
# Browser settings
browser.name=chrome
browser.headless=false
browser.remote=false
browser.remote.url=http://localhost:4444/wd/hub
browser.implicitWait=10
browser.pageLoadTimeout=30
browser.windowSize=1920x1080
browser.closeAfterTest=true
```

### Test Configuration
```properties
# Test settings
test.baseUrl=https://www.google.com
test.timeout=30
test.retryCount=0
screenshot.onFailure=true
screenshot.onSuccess=false
```

## Usage Examples

### Creating a Test Class
```java
@ExtendWith(TestHooks.class)
public class MyTest extends BaseTest {
    
    @Test
    public void testExample() {
        setUp();
        navigateToBaseUrl();
        // Your test logic here
        tearDown();
    }
}
```

### Creating a Page Object
```java
public class HomePage extends BasePage {
    
    @FindBy(id = "search-box")
    private WebElement searchBox;
    
    @FindBy(css = "button[type='submit']")
    private WebElement searchButton;
    
    public void searchFor(String query) {
        sendText(searchBox, query, "Search Box");
        clickElement(searchButton, "Search Button");
    }
}
```

### Using Utilities
```java
// Wait for element
waitUtils.waitForElementToBeVisible(searchBox);

// Assert element state
AssertUtils.assertElementDisplayed(searchBox, "Search Box");

// JavaScript operations
elementUtils.clickElementWithJS(searchButton);

// Configuration access
String baseUrl = ConfigManager.getBaseUrl();
```

## Maven Profiles

### Browser Profiles
```bash
# Run with Chrome (default)
mvn test

# Run with Firefox
mvn test -Pfirefox

# Run with Edge
mvn test -Pedge

# Run in headless mode
mvn test -Pheadless
```

### Parallel Execution
```bash
# Run tests in parallel
mvn test -Pparallel
```

## Dependencies

- **Selenium WebDriver**: 4.15.0
- **JUnit5**: 5.10.0
- **SLF4J**: 2.0.9
- **Logback**: 1.4.11
- **Allure**: 2.24.0
- **WebDriverManager**: 5.6.2

## Getting Started

1. **Clone the repository**
2. **Install dependencies**: `mvn clean install`
3. **Configure test properties** in `src/test/resources/test.properties`
4. **Create your test classes** extending `BaseTest`
5. **Create page objects** extending `BasePage`
6. **Run tests**: `mvn test`

## Best Practices

1. **Use Page Object Model**: Create separate page classes for each page
2. **Leverage Utilities**: Use the provided utility classes for common operations
3. **Configure Properly**: Set up test properties for your environment
4. **Use Hooks**: Let the framework handle WebDriver lifecycle
5. **Add Assertions**: Use AssertUtils for comprehensive validations
6. **Handle Waits**: Use WaitUtils for reliable element interactions

## Reporting

The framework integrates with Allure for comprehensive test reporting:
- Automatic screenshot capture on failures
- Page source attachment for debugging
- Detailed test execution information
- Beautiful HTML reports

Generate reports with:
```bash
mvn allure:report
```

## Contributing

1. Follow the existing code structure
2. Add comprehensive JavaDoc comments
3. Include unit tests for new utilities
4. Update documentation as needed

## License

This project is licensed under the MIT License.