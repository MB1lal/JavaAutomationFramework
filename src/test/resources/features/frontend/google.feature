@test @Browser
  Feature: Searching a term on google

    Scenario: Navigating to google.com
      Given I open a browser
      Then The page is loaded

