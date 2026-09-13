@test @backend @petStore

Feature: Testing the pets store


  Scenario: Order is successfully placed on pet store
    Given I place an order on the pet store
    When I fetch the order
    Then The order is successfully placed

  Scenario: Order can be successfully deleted
    Given I place an order on the pet store
    When I delete the order
    Then The order shouldn't exist
