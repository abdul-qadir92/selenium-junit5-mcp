#!/bin/bash

# Selenium JUnit 5 Framework Test Runner Script
# This script provides convenient commands to run tests with different configurations

set -e

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Function to print colored output
print_info() {
    echo -e "${BLUE}[INFO]${NC} $1"
}

print_success() {
    echo -e "${GREEN}[SUCCESS]${NC} $1"
}

print_warning() {
    echo -e "${YELLOW}[WARNING]${NC} $1"
}

print_error() {
    echo -e "${RED}[ERROR]${NC} $1"
}

# Function to show usage
show_usage() {
    echo "Usage: $0 [COMMAND] [OPTIONS]"
    echo ""
    echo "Commands:"
    echo "  test                    Run all tests"
    echo "  test-class CLASS        Run specific test class"
    echo "  test-method CLASS#METHOD Run specific test method"
    echo "  chrome                  Run tests with Chrome browser"
    echo "  firefox                 Run tests with Firefox browser"
    echo "  edge                    Run tests with Edge browser"
    echo "  headless                Run tests in headless mode"
    echo "  allure                  Run tests and generate Allure report"
    echo "  clean                   Clean target directory"
    echo "  install                 Install dependencies"
    echo "  help                    Show this help message"
    echo ""
    echo "Examples:"
    echo "  $0 test"
    echo "  $0 test-class GoogleHomepageTest"
    echo "  $0 test-method GoogleSearchTest#testBasicSearch"
    echo "  $0 chrome"
    echo "  $0 headless"
    echo "  $0 allure"
}

# Function to run tests
run_tests() {
    local profile=$1
    local test_class=$2
    local test_method=$3
    
    print_info "Starting test execution..."
    
    if [ -n "$test_class" ]; then
        if [ -n "$test_method" ]; then
            print_info "Running test method: $test_class#$test_method"
            mvn test -Dtest="$test_class#$test_method" ${profile:+-P$profile}
        else
            print_info "Running test class: $test_class"
            mvn test -Dtest="$test_class" ${profile:+-P$profile}
        fi
    else
        print_info "Running all tests"
        mvn test ${profile:+-P$profile}
    fi
    
    if [ $? -eq 0 ]; then
        print_success "Tests completed successfully!"
    else
        print_error "Tests failed!"
        exit 1
    fi
}

# Function to run tests with Allure
run_tests_with_allure() {
    print_info "Running tests with Allure reporting..."
    
    # Clean previous results
    rm -rf target/allure-results
    
    # Run tests
    mvn clean test
    
    if [ $? -eq 0 ]; then
        print_success "Tests completed successfully!"
        
        # Generate Allure report
        print_info "Generating Allure report..."
        mvn allure:report
        
        if [ $? -eq 0 ]; then
            print_success "Allure report generated successfully!"
            print_info "To view the report, run: mvn allure:serve"
        else
            print_error "Failed to generate Allure report!"
            exit 1
        fi
    else
        print_error "Tests failed!"
        exit 1
    fi
}

# Function to clean target directory
clean_target() {
    print_info "Cleaning target directory..."
    mvn clean
    print_success "Target directory cleaned!"
}

# Function to install dependencies
install_dependencies() {
    print_info "Installing dependencies..."
    mvn clean install
    print_success "Dependencies installed successfully!"
}

# Main script logic
case "${1:-help}" in
    "test")
        run_tests
        ;;
    "test-class")
        if [ -z "$2" ]; then
            print_error "Test class name is required!"
            show_usage
            exit 1
        fi
        run_tests "" "$2"
        ;;
    "test-method")
        if [ -z "$2" ]; then
            print_error "Test class and method name are required!"
            show_usage
            exit 1
        fi
        run_tests "" "$2"
        ;;
    "chrome")
        print_info "Running tests with Chrome browser..."
        run_tests "chrome"
        ;;
    "firefox")
        print_info "Running tests with Firefox browser..."
        run_tests "firefox"
        ;;
    "edge")
        print_info "Running tests with Edge browser..."
        run_tests "edge"
        ;;
    "headless")
        print_info "Running tests in headless mode..."
        # Update configuration to headless mode
        sed -i.bak 's/headless: false/headless: true/' src/test/resources/config/test-config.yml
        run_tests
        # Restore original configuration
        sed -i.bak 's/headless: true/headless: false/' src/test/resources/config/test-config.yml
        rm src/test/resources/config/test-config.yml.bak
        ;;
    "allure")
        run_tests_with_allure
        ;;
    "clean")
        clean_target
        ;;
    "install")
        install_dependencies
        ;;
    "help"|"-h"|"--help")
        show_usage
        ;;
    *)
        print_error "Unknown command: $1"
        show_usage
        exit 1
        ;;
esac
