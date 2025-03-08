Feature: Drag and Drop Interaction

  Scenario: Drag box A to box B
    Given I am on the drag and drop page
    When I drag box A to box B
    Then Box A should be in the position of box B
