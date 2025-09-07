package com.example.tests;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

/**
 * Test suite that runs all test classes
 */
@Suite
@SuiteDisplayName("Selenium JUnit 5 Test Suite")
@SelectClasses({
    GoogleHomepageTest.class,
    GoogleSearchTest.class
})
public class TestSuite {
    // This class serves as a test suite container
    // All test classes are selected via @SelectClasses annotation
}
