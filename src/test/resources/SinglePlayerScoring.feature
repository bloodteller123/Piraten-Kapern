Feature: Handle multiple single player scoring scenarios
  Background:
    Given Player is initialized
    And dice is initialized

  Scenario: row 37 die with 3 skulls 5 swords on first roll and player gets a score of 0
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

  Scenario: row 38 roll 1 skull, 4 parrots, 3 swords, reroll 3 swords, get 2 skulls 1 sword die
    When player rolls
      | die     | values |
      | diamond | 0      |
      | skull   | 1      |
      | saber   | 3      |
      | parrot  | 4      |
      | coin    | 0      |
      | monkey  | 0      |
    And player rerolls "saber" to get
      | die     | values |
      | saber   | 1      |
      | skull   | 2      |
    Then player gets 0 scores
    And player dies

  Scenario: row 39 roll 2 skull, 4 parrots, 2 swords, reroll 2 swords, get 1 skulls 1 sword die
    When player rolls
      | die     | values |
      | diamond | 0      |
      | skull   | 2      |
      | saber   | 2      |
      | parrot  | 4      |
      | coin    | 0      |
      | monkey  | 0      |
    And player rerolls "saber" to get
      | die     | values |
      | saber   | 1      |
      | skull   | 1      |
    Then player gets 0 scores
    And player dies

  Scenario: row 40 roll 1 skull, 4 parrots, 3 swords, reroll 3 swords, get 1 skull 2 monkeys, reroll
  2 monkeys, get 1 skull 1 monkey die
    When player rolls
      | die     | values |
      | diamond | 0      |
      | skull   | 1      |
      | saber   | 3      |
      | parrot  | 4      |
      | coin    | 0      |
      | monkey  | 0      |
    And player rerolls "saber" to get
      | die     | values |
      | monkey  | 2      |
      | skull   | 1      |
    And player rerolls "monkey" to get
      | die     | values |
      | monkey  | 1      |
      | skull   | 1      |
    Then player gets 0 scores
    And player dies