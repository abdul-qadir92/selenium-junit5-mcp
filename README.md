# Selenium JUnit 5 Framework

A comprehensive Selenium testing framework built with JUnit 5, featuring Page Object Model, Allure reporting, and extensive utility classes.

## 🚀 Features

- **JUnit 5** - Latest JUnit testing framework with enhanced features
- **Selenium WebDriver 4.15.0** - Latest Selenium WebDriver with improved performance
- **Page Object Model** - Clean, maintainable test structure
- **WebDriverManager** - Automatic driver management
- **Allure Reporting** - Beautiful test reports with screenshots
- **Multiple Browser Support** - Chrome, Firefox, Edge
- **Configuration Management** - YAML-based configuration
- **Comprehensive Logging** - Logback with file and console logging
- **Utility Classes** - Reusable helper methods
- **Test Data Management** - JSON-based test data
- **Screenshot Support** - Automatic screenshots on failure
- **Wait Utilities** - Explicit and fluent wait implementations

## 📁 Project Structure

```
selenium-junit5-mcp/
├── src/
│   ├── main/java/com/example/framework/
│   │   ├── base/
│   │   │   ├── BaseTest.java          # Base test class
│   │   │   └── BasePage.java          # Base page object class
│   │   ├── config/
│   │   │   └── TestConfig.java        # Configuration management
│   │   ├── utils/
│   │   │   ├── WebDriverManager.java  # WebDriver management
│   │   │   ├── WaitUtils.java         # Wait utilities
│   │   │   ├── ScreenshotUtils.java   # Screenshot utilities
│   │   │   ├── AssertionUtils.java    # Custom assertions
│   │   │   ├── TestDataUtils.java     # Test data management
│   │   │   └── DateUtils.java         # Date/time utilities
│   │   └── pages/                     # Page object classes
│   └── test/
│       ├── java/com/example/
│       │   ├── pageobjects/           # Page object implementations
│       │   │   ├── GoogleHomePage.java
│       │   │   └── GoogleSearchResultsPage.java
│       │   └── tests/                 # Test classes
│       │       ├── GoogleHomepageTest.java
│       │       ├── GoogleSearchTest.java
│       │       └── TestSuite.java
│       └── resources/
│           ├── config/
│           │   └── test-config.yml    # Test configuration
│           ├── data/                  # Test data files
│           │   ├── test-cases.json
│           │   └── users.json
│           ├── allure.properties      # Allure configuration
│           └── logback-test.xml       # Logging configuration
├── target/
│   ├── allure-results/               # Allure test results
│   ├── screenshots/                  # Screenshots
│   └── logs/                         # Log files
├── pom.xml                           # Maven configuration
├── allure.properties                 # Allure properties
└── README.md                         # This file
```

## 🛠️ Prerequisites

- **Java 11 or higher**
- **Maven 3.6 or higher**
- **Chrome, Firefox, or Edge browser** (for running tests)

## 📦 Installation

1. **Clone the repository:**
   ```bash
   git clone <repository-url>
   cd selenium-junit5-mcp
   ```

2. **Verify Java and Maven installation:**
   ```bash
   java -version
   mvn -version
   ```

3. **Install dependencies:**
   ```bash
   mvn clean install
   ```

## 🚀 Running Tests

### Run All Tests
```bash
mvn test
```

### Run Specific Test Class
```bash
mvn test -Dtest=GoogleHomepageTest
```

### Run Tests with Specific Browser
```bash
# Chrome (default)
mvn test -Pchrome

# Firefox
mvn test -Pfirefox

# Edge
mvn test -Pedge
```

### Run Tests in Headless Mode
Update `src/test/resources/config/test-config.yml`:
```yaml
headless: true
```

### Run Tests with Allure Reporting
```bash
# Run tests and generate Allure report
mvn clean test
mvn allure:report

# Serve Allure report
mvn allure:serve
```

## ⚙️ Configuration

### Test Configuration (`src/test/resources/config/test-config.yml`)

```yaml
browser: chrome                    # Browser to use (chrome, firefox, edge)
headless: false                   # Run in headless mode
implicitWait: 10                  # Implicit wait timeout in seconds
explicitWait: 20                  # Explicit wait timeout in seconds
pageLoadTimeout: 30               # Page load timeout in seconds
baseUrl: "https://www.google.com" # Base URL for tests
screenshotOnFailure: true         # Take screenshots on test failure
videoRecording: false             # Record video (not implemented yet)
```

### Browser-Specific Settings

```yaml
chrome:
  options:
    - "--no-sandbox"
    - "--disable-dev-shm-usage"
    - "--disable-gpu"
    - "--window-size=1920,1080"

firefox:
  options:
    - "--width=1920"
    - "--height=1080"
```

## 📊 Test Reports

### Allure Reports
After running tests, generate and view Allure reports:

```bash
# Generate report
mvn allure:report

# Serve report (opens in browser)
mvn allure:serve
```

### Screenshots
Screenshots are automatically saved to `target/screenshots/` on test failures.

### Logs
Test execution logs are saved to `target/logs/test-execution.log`.

## 🧪 Writing Tests

### 1. Create a Page Object

```java
public class LoginPage extends BasePage {
    private static final By USERNAME_LOCATOR = By.id("username");
    private static final By PASSWORD_LOCATOR = By.id("password");
    private static final By LOGIN_BUTTON_LOCATOR = By.id("login-button");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String username) {
        sendKeys(USERNAME_LOCATOR, username);
    }

    public void enterPassword(String password) {
        sendKeys(PASSWORD_LOCATOR, password);
    }

    public void clickLoginButton() {
        click(LOGIN_BUTTON_LOCATOR);
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }
}
```

### 2. Create a Test Class

```java
@Epic("User Authentication")
@Feature("Login Functionality")
public class LoginTest extends BaseTest {
    
    private LoginPage loginPage;

    @BeforeEach
    public void setUpPages() {
        loginPage = new LoginPage(driver);
    }

    @Test
    @DisplayName("Successful login with valid credentials")
    @Description("Test that user can login with valid username and password")
    @Severity(SeverityLevel.CRITICAL)
    public void testSuccessfulLogin() {
        // Test implementation
        loginPage.login("validuser", "validpassword");
        
        // Assertions
        assertThat(getCurrentUrl()).contains("dashboard");
    }
}
```

## 🔧 Utility Classes

### WaitUtils
```java
// Wait for element to be visible
WebElement element = WaitUtils.waitForElementVisible(driver, locator);

// Wait for element to be clickable
WebElement element = WaitUtils.waitForElementClickable(driver, locator);

// Wait for text to be present
boolean textPresent = WaitUtils.waitForTextToBePresentInElement(driver, locator, "Expected Text");
```

### ScreenshotUtils
```java
// Take screenshot and save to file
String screenshotPath = ScreenshotUtils.takeScreenshotAndSave(driver, "test-name");

// Take screenshot as byte array
byte[] screenshot = ScreenshotUtils.takeScreenshot(driver);
```

### TestDataUtils
```java
// Load test data from JSON
List<SearchTestCase> testCases = TestDataUtils.getSearchTestCases();
List<TestUser> users = TestDataUtils.getTestUsers();
```

## 📝 Best Practices

1. **Use Page Object Model** - Keep page elements and actions in separate classes
2. **Use Explicit Waits** - Always wait for elements before interacting
3. **Use Descriptive Test Names** - Make test names clear and descriptive
4. **Use Allure Annotations** - Add @Step, @Description, @Severity annotations
5. **Use Configuration Files** - Keep test data and configuration external
6. **Use Logging** - Add appropriate logging for debugging
7. **Use Assertions** - Use AssertJ for fluent assertions
8. **Clean Up** - Always clean up resources in @AfterEach methods

## 🐛 Troubleshooting

### Common Issues

1. **WebDriver not found:**
   - Ensure WebDriverManager is properly configured
   - Check if browser is installed

2. **Tests failing with timeout:**
   - Increase wait timeouts in configuration
   - Check if elements are properly located

3. **Allure report not generating:**
   - Ensure `target/allure-results` directory exists
   - Check Allure configuration

4. **Screenshots not saving:**
   - Check if `target/screenshots` directory exists
   - Verify screenshot configuration

### Debug Mode

Enable debug logging by updating `logback-test.xml`:
```xml
<logger name="com.example.framework" level="DEBUG" additivity="false">
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Ensure all tests pass
6. Submit a pull request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 📞 Support

For support and questions:
- Create an issue in the repository
- Check the documentation
- Review existing test examples

---

**Happy Testing! 🎉**
