@test @imdb @ignore
  Feature: Searching a term on google

    Background: Check for internet connectivity
      Given User has internet connectivity


    Scenario: Navigating to Google
      Given User navigates to Google
      Then The page is loaded

    @excelData
    Scenario: Saving cast of a series from imdb by using google
      Given User navigates to Google
      And The page is loaded
      And User searches for value in "B2" of provided sheet
      And User finds the link present in "B3" of provided sheet
      And User opens the link in a new tab
      And User scrolls down to find the "All cast & crew" text
      When User clicks on the "All cast & crew"
      Then User picks the following details:
      | Name        |
      | Screen Name |
      | Appearances |
      And User exports all the data into "Series Cast" sheet


