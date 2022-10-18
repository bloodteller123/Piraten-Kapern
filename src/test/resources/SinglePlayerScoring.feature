Feature: Handle multiple single player scoring scenarios
  Background:
    Given Player is initialized
    And dice is initialized

  Scenario: die with 3 skulls 5 swords on first roll and player gets a score of 0
    When player rolls 3 "skull" , 5 "saber"
    Then player gets 0 scores
    And player dies

  Scenario: roll 1 skull, 4 parrots, 3 swords, reroll 3 swords, get 2 skulls 1 sword die
    When player rolls 1 "skull" , 4 "parrot", 3 "saber"
    And player rerolls 3 "saber" to get 2 "skull", 1 "saber"
    Then player gets 0 scores
    And player dies