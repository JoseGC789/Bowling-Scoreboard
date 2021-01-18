Feature: Create a scoreboard
  As a ten-pin bowling player
  I want a scoreboard of my games
  So I can tell who won

  Background:
    Given the App is running

  Scenario: Show average scoreboard
    Given I have an average game
    When I input my throws
    Then I'm shown my scoreboard

  Scenario: Show perfect scoreboard
    Given I have a perfect game
    When I input my throws
    Then I'm shown my scoreboard

  Scenario: Show worst scoreboard
    Given I have an awful game
    When I input my throws
    Then I'm shown my scoreboard