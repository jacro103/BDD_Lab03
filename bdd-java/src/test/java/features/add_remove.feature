Feature: Add and Remove Elements

  Scenario: Add and delete an element
    Given I am on the Add/Remove Elements page
    When I click the "Add Element" button
    Then A new element should be added
    When I click the "Delete" button
    Then The element should be removed
