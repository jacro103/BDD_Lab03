Feature: User Registration
  As a new user
  I want to register an account
  So that I can access the application

  Scenario: Successful user registration
    Given I am on the registration page
    When I enter valid user details
    And I submit the registration form
    Then I should see a confirmation message
