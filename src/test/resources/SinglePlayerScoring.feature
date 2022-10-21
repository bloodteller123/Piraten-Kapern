Feature: Handle multiple single player scoring scenarios
  Background:
    Given Player is initialized
    And dice is initialized

  Scenario: die with 3 skulls 5 swords on first roll and player gets a score of 0
    When player rolls
      | die     | values |
      | diamond | 0      |
      | skull   | 3      |
      | saber   | 5      |
      | parrot  | 0      |
      | coin    | 0      |
      | monkey  | 0      |
    Then player gets 0 scores
    And player dies

  Scenario: roll 1 skull, 4 parrots, 3 swords, reroll 3 swords, get 2 skulls 1 sword die
    When player rolls
      | die     | values |
      | diamond | 0      |
      | skull   | 1      |
      | saber   | 3      |
      | parrot  | 4      |
      | coin    | 0      |
      | monkey  | 0      |
    And player rerolls 3 "saber" to get 2 "skull", 1 "saber"
    Then player gets 0 scores
    And player dies

  Scenario: roll 1 skull, 4 parrots, 3 swords, reroll 3 swords, get 2 skulls 1 sword die
    When player rolls
      | die     | values |
      | diamond | 0      |
      | skull   | 2      |
      | saber   | 2      |
      | parrot  | 4      |
      | coin    | 0      |
      | monkey  | 0      |
    And player rerolls 2 "saber" to get 1 "skull", 1 "saber"
    Then player gets 0 scores
    And player dies

  Scenario: roll 1 skull, 4 parrots, 3 swords, reroll 3 swords, get 2 skulls 1 sword die
    When player rolls
      | die     | values |
      | diamond | 0      |
      | skull   | 1      |
      | saber   | 3      |
      | parrot  | 4      |
      | coin    | 0      |
      | monkey  | 0      |
    And player rerolls 3 "saber" to get 1 "skull", 2 "monkey"
    And player rerolls 2 "monkey" to get 1 "skull", 1 "monkey"
    Then player gets 0 scores
    And player dies