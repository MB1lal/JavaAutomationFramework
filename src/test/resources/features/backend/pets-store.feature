@test @backend @pet

Feature: Testing the pets store


 Scenario: Order is successfully placed on pet store
   Given I place an order on pet store with id = 400
   When I fetch the order using id = 400
   Then The order is successfully placed