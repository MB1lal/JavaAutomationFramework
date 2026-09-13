@test @frontend @herokuapp @download
Feature: Secure File Download Tests

  Scenario: Download a File
    Given I am on the file download page
    When I download the first listed file
    Then the file should be downloaded successfully
