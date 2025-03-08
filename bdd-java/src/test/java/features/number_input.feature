Feature: Number Input Field Testing

  Scenario: Verify number input accepts valid numbers
    Given I navigate to the Number Input page
    When I enter "42" in the number field
    Then The number field should contain "42"
