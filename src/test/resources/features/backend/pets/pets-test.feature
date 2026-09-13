@test @backend @pet

  Feature: Testing the pets api


    Scenario: Verifying newly added pet through Id
      Given I add a new pet with status = available
      When I fetch the pet by id
      Then the pet exists


    Scenario: Verifying newly added pet through Status
      Given I add a new pet with status = sold
      When I fetch pets by status
      Then The pet has status = sold


    Scenario: Verifying newly added pet can be deleted
      Given I add a new pet with status = available
      And I delete the pet
      When I fetch the pet by id
      Then the pet does not exist


    Scenario: Verifying pet details are correctly updated
      Given I add a new pet with status = available
      And I update the pet name to Unicorn
      And I update the pet status to sold
      When I fetch the pet by id
      Then the pet exists
      And The pet has status = sold
