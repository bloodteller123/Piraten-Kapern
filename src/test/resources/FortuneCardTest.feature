Feature: Handle cases with different fortune cards
  Background:
    Given Player is initialized
    And dice is initialized

  Scenario: row 69 roll 2 diamonds, 1 monkey 1 coin, 1 swords 3 parrots, reroll 3 parrots,
  get 1 skulls 2 monkey, reroll skull get monkey get 500 points
    When fortunate card is "sorceress"
    And player rolls
      | die     | values |
      | diamond | 2      |
      | skull   | 0      |
      | saber   | 1      |
      | parrot  | 3      |
      | coin    | 1      |
      | monkey  | 1      |
    And player rerolls "parrot" to get
      | die     | values |
      | monkey  | 2      |
      | skull   | 1      |
    And player rerolls "skull" to get
      | die     | values |
      | monkey  | 1      |
    Then player gets 500 scores

  Scenario: row 70 roll 3 skulls  2 swords 3 parrots, reroll 1 skull,
  get 1 parrot, reroll 2 swords get parrots get 1000 points
    When fortunate card is "sorceress"
    And player rolls
      | die     | values |
      | diamond | 0      |
      | skull   | 3      |
      | saber   | 2      |
      | parrot  | 3      |
      | coin    | 0      |
      | monkey  | 0      |
    And player rerolls "skull" to get
      | die     | values |
      | parrot  | 1      |
    And player rerolls "saber" to get
      | die     | values |
      | parrot  | 2      |
    Then player gets 1000 scores

  Scenario: row 71 roll 1 skulls 3 monkeys 4 parrots, reroll 3 monkeys,
  get 1 skull 2 parrot, reroll skull get parrots get 2000 points
    When fortunate card is "sorceress"
    And player rolls
      | die     | values |
      | diamond | 0      |
      | skull   | 1      |
      | saber   | 0      |
      | parrot  | 4      |
      | coin    | 0      |
      | monkey  | 3      |
    And player rerolls "monkey" to get
      | die     | values |
      | parrot  | 2      |
      | skull   | 1      |
    And player rerolls "skull" to get
      | die     | values |
      | parrot  | 1      |
    Then player gets 2000 scores