@test @Browser
  Feature: Searching a term on google

    Background: Check for internet connectivity
      Given User has internet connectivity


    Scenario: Navigating to google.com
      Given I open a browser
      Then The page is loaded

      @excelData @ignore
    Scenario: Navigating to google.com
      Given I open a browser
      Then The page is loaded

