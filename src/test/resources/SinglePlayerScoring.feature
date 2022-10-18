Feature: Handle multiple single player scoring scenarios
  Background:
    Given Player is initialized
    And dice is initialized

  Scenario: die with 3 skulls 5 swords on first roll and player gets a score of 0
    When player rolls "3" "skulls" and "5" "sabers"
    Then player gets 0 scores