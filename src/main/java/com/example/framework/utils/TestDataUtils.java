package com.example.framework.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for loading test data from JSON files
 */
public class TestDataUtils {
    private static final Logger logger = LoggerFactory.getLogger(TestDataUtils.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Load test data from JSON file
     */
    public static JsonNode loadTestData(String fileName) {
        try {
            String filePath = "src/test/resources/data/" + fileName;
            File file = new File(filePath);
            
            if (!file.exists()) {
                logger.warn("Test data file not found: {}", filePath);
                return objectMapper.createObjectNode();
            }
            
            JsonNode data = objectMapper.readTree(file);
            logger.info("Loaded test data from: {}", filePath);
            return data;
        } catch (IOException e) {
            logger.error("Failed to load test data from file: {}", fileName, e);
            return objectMapper.createObjectNode();
        }
    }

    /**
     * Get search test cases from JSON
     */
    public static List<SearchTestCase> getSearchTestCases() {
        List<SearchTestCase> testCases = new ArrayList<>();
        try {
            JsonNode data = loadTestData("test-cases.json");
            JsonNode searchTestCases = data.get("searchTestCases");
            
            if (searchTestCases != null && searchTestCases.isArray()) {
                for (JsonNode testCase : searchTestCases) {
                    SearchTestCase searchTestCase = new SearchTestCase();
                    searchTestCase.setTestName(testCase.get("testName").asText());
                    searchTestCase.setSearchTerm(testCase.get("searchTerm").asText());
                    searchTestCase.setExpectedResults(testCase.get("expectedResults").asBoolean());
                    searchTestCase.setDescription(testCase.get("description").asText());
                    testCases.add(searchTestCase);
                }
            }
        } catch (Exception e) {
            logger.error("Failed to parse search test cases", e);
        }
        return testCases;
    }

    /**
     * Get homepage test cases from JSON
     */
    public static List<HomepageTestCase> getHomepageTestCases() {
        List<HomepageTestCase> testCases = new ArrayList<>();
        try {
            JsonNode data = loadTestData("test-cases.json");
            JsonNode homepageTestCases = data.get("homepageTestCases");
            
            if (homepageTestCases != null && homepageTestCases.isArray()) {
                for (JsonNode testCase : homepageTestCases) {
                    HomepageTestCase homepageTestCase = new HomepageTestCase();
                    homepageTestCase.setTestName(testCase.get("testName").asText());
                    homepageTestCase.setElement(testCase.get("element").asText());
                    homepageTestCase.setShouldBeVisible(testCase.get("shouldBeVisible").asBoolean());
                    homepageTestCase.setDescription(testCase.get("description").asText());
                    testCases.add(homepageTestCase);
                }
            }
        } catch (Exception e) {
            logger.error("Failed to parse homepage test cases", e);
        }
        return testCases;
    }

    /**
     * Get browser test cases from JSON
     */
    public static List<BrowserTestCase> getBrowserTestCases() {
        List<BrowserTestCase> testCases = new ArrayList<>();
        try {
            JsonNode data = loadTestData("test-cases.json");
            JsonNode browserTestCases = data.get("browserTestCases");
            
            if (browserTestCases != null && browserTestCases.isArray()) {
                for (JsonNode testCase : browserTestCases) {
                    BrowserTestCase browserTestCase = new BrowserTestCase();
                    browserTestCase.setBrowser(testCase.get("browser").asText());
                    browserTestCase.setHeadless(testCase.get("headless").asBoolean());
                    browserTestCase.setDescription(testCase.get("description").asText());
                    testCases.add(browserTestCase);
                }
            }
        } catch (Exception e) {
            logger.error("Failed to parse browser test cases", e);
        }
        return testCases;
    }

    /**
     * Get test users from JSON
     */
    public static List<TestUser> getTestUsers() {
        List<TestUser> users = new ArrayList<>();
        try {
            JsonNode data = loadTestData("users.json");
            JsonNode testUsers = data.get("testUsers");
            
            if (testUsers != null && testUsers.isArray()) {
                for (JsonNode user : testUsers) {
                    TestUser testUser = new TestUser();
                    testUser.setUsername(user.get("username").asText());
                    testUser.setEmail(user.get("email").asText());
                    testUser.setFirstName(user.get("firstName").asText());
                    testUser.setLastName(user.get("lastName").asText());
                    testUser.setPassword(user.get("password").asText());
                    testUser.setRole(user.get("role").asText());
                    users.add(testUser);
                }
            }
        } catch (Exception e) {
            logger.error("Failed to parse test users", e);
        }
        return users;
    }

    // Inner classes for test data models
    public static class SearchTestCase {
        private String testName;
        private String searchTerm;
        private boolean expectedResults;
        private String description;

        // Getters and setters
        public String getTestName() { return testName; }
        public void setTestName(String testName) { this.testName = testName; }
        public String getSearchTerm() { return searchTerm; }
        public void setSearchTerm(String searchTerm) { this.searchTerm = searchTerm; }
        public boolean isExpectedResults() { return expectedResults; }
        public void setExpectedResults(boolean expectedResults) { this.expectedResults = expectedResults; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    }

    public static class HomepageTestCase {
        private String testName;
        private String element;
        private boolean shouldBeVisible;
        private String description;

        // Getters and setters
        public String getTestName() { return testName; }
        public void setTestName(String testName) { this.testName = testName; }
        public String getElement() { return element; }
        public void setElement(String element) { this.element = element; }
        public boolean isShouldBeVisible() { return shouldBeVisible; }
        public void setShouldBeVisible(boolean shouldBeVisible) { this.shouldBeVisible = shouldBeVisible; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    }

    public static class BrowserTestCase {
        private String browser;
        private boolean headless;
        private String description;

        // Getters and setters
        public String getBrowser() { return browser; }
        public void setBrowser(String browser) { this.browser = browser; }
        public boolean isHeadless() { return headless; }
        public void setHeadless(boolean headless) { this.headless = headless; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    }

    public static class TestUser {
        private String username;
        private String email;
        private String firstName;
        private String lastName;
        private String password;
        private String role;

        // Getters and setters
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getFirstName() { return firstName; }
        public void setFirstName(String firstName) { this.firstName = firstName; }
        public String getLastName() { return lastName; }
        public void setLastName(String lastName) { this.lastName = lastName; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
        public String getRole() { return role; }
        public void setRole(String role) { this.role = role; }
    }
}
