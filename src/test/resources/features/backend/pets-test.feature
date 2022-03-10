@Test @backend

  Feature: Testing the pets api

    Scenario: Adding a new pet
      Given I add the pet
      Then Verify status code is 200
