Feature: Handle multiple single player scoring scenarios
  Background:
    Given Player is initialized
    And dice is initialized

  Scenario: row 37 die with 3 skulls 5 swords on first roll and player gets a score of 0
    When fortunate card is "coin"
    And player rolls
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
    When fortunate card is "coin"
    And player rolls
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
    When fortunate card is "coin"
    And player rolls
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
    When fortunate card is "coin"
    And player rolls
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

  Scenario: row 42 roll 1 skull, 2 parrots, 3 swords, 2 coins, reroll 2 parrots, get 2 coins, reroll
  3 swords, get 3 coins and get 4800 points
    When fortunate card is "coin"
    And player rolls
      | die     | values |
      | diamond | 0      |
      | skull   | 1      |
      | saber   | 3      |
      | parrot  | 2      |
      | coin    | 2      |
      | monkey  | 0      |
    And player rerolls "parrot" to get
      | die     | values |
      | coin    | 2      |
    And player rerolls "saber" to get
      | die     | values |
      | coin    | 3      |
    Then player gets 4800 scores

  Scenario: row 44 roll 2 parrots, 2 diamonds, 2 coins, 2 monkeys and get 800 points
    When fortunate card is "captain"
    And player rolls
      | die     | values |
      | diamond | 2      |
      | skull   | 0      |
      | saber   | 0      |
      | parrot  | 2      |
      | coin    | 2      |
      | monkey  | 2      |
    Then player gets 800 scores

  Scenario: row 45 roll 2 parrots, 2 skulls, 2 sabers, 2 monkeys, reroll parrots, get 1 saber and
  1 monkey and get 300 points
    When fortunate card is "coin"
    And player rolls
      | die     | values |
      | diamond | 0      |
      | skull   | 2      |
      | saber   | 2      |
      | parrot  | 2      |
      | coin    | 0      |
      | monkey  | 2      |
    And player rerolls "parrot" to get
      | die     | values |
      | saber   | 1      |
      | monkey  | 1      |
    Then player gets 300 scores

  Scenario: row 46 roll 3 monkeys 3 swords 2 skulls on first roll and player gets a score of 300
    When fortunate card is "coin"
    And player rolls
      | die     | values |
      | diamond | 0      |
      | skull   | 2      |
      | saber   | 3      |
      | parrot  | 0      |
      | coin    | 0      |
      | monkey  | 3      |
    Then player gets 300 scores

  Scenario: row 47 roll 3 diamonds 1 swords 1 monkey 1 parrot 2 skulls on first roll and player gets a score of 500
    When fortunate card is "coin"
    And player rolls
      | die     | values |
      | diamond | 3      |
      | skull   | 2      |
      | saber   | 1      |
      | parrot  | 1      |
      | coin    | 0      |
      | monkey  | 1      |
    Then player gets 500 scores

  Scenario: row 48 roll 4 coins 2 swords 2 skulls on first roll and player gets a score of 700
    When fortunate card is "diamond"
    And player rolls
      | die     | values |
      | diamond | 0      |
      | skull   | 2      |
      | saber   | 2      |
      | parrot  | 0      |
      | coin    | 4      |
      | monkey  | 0      |
    Then player gets 700 scores

  Scenario: row 49 roll 4 parrots 3 swords 1 skulls on first roll and player gets a score of 400
    When fortunate card is "diamond"
    And player rolls
      | die     | values |
      | diamond | 0      |
      | skull   | 1      |
      | saber   | 3      |
      | parrot  | 4      |
      | coin    | 0      |
      | monkey  | 0      |
    Then player gets 400 scores

  Scenario: row 50 roll 2 parrots, 2 coins 1 skulls, 3 sabers, reroll parrots, get 1 saber and
  1 coin and get 800 points
    When fortunate card is "coin"
    And player rolls
      | die     | values |
      | diamond | 0      |
      | skull   | 1      |
      | saber   | 3      |
      | parrot  | 2      |
      | coin    | 2      |
      | monkey  | 0      |
    And player rerolls "parrot" to get
      | die     | values |
      | saber   | 1      |
      | coin    | 1      |
    Then player gets 800 scores

  Scenario: row 51 roll 2 parrots, 2 coins 1 skulls, 3 sabers, reroll parrots, get 1 saber and
  1 coin and get 1200 points
    When fortunate card is "captain"
    And player rolls
      | die     | values |
      | diamond | 0      |
      | skull   | 1      |
      | saber   | 3      |
      | parrot  | 2      |
      | coin    | 2      |
      | monkey  | 0      |
    And player rerolls "parrot" to get
      | die     | values |
      | saber   | 1      |
      | coin    | 1      |
    Then player gets 1200 scores

  Scenario: row 52 roll 1 skull, 2 parrots, 2 monkeys 3 swords, reroll 2 monkeys, get 1 skull 1 sword, reroll
  2 parrots, get 1 sowrd 1 monkey and get 600 points
    When fortunate card is "coin"
    And player rolls
      | die     | values |
      | diamond | 0      |
      | skull   | 1      |
      | saber   | 3      |
      | parrot  | 2      |
      | coin    | 0      |
      | monkey  | 2      |
    And player rerolls "monkey" to get
      | die     | values |
      | skull   | 1      |
      | saber   | 1      |
    And player rerolls "parrot" to get
      | die     | values |
      | saber   | 1      |
      | monkey  | 1      |
    Then player gets 600 scores

  Scenario: row 54 roll 6 monkeys 2 skulls on first roll and player gets a score of 1100
    When fortunate card is "coin"
    And player rolls
      | die     | values |
      | diamond | 0      |
      | skull   | 2      |
      | saber   | 0      |
      | parrot  | 0      |
      | coin    | 0      |
      | monkey  | 6      |
    Then player gets 1100 scores

  Scenario: row 55 roll 7 parrots 1 skulls on first roll and player gets a score of 2100
    When fortunate card is "coin"
    And player rolls
      | die     | values |
      | diamond | 0      |
      | skull   | 1      |
      | saber   | 0      |
      | parrot  | 7      |
      | coin    | 0      |
      | monkey  | 0      |
    Then player gets 2100 scores

  Scenario: row 56 roll 8 coins first roll and player gets a score of 5400
    When fortunate card is "coin"
    And player rolls
      | die     | values |
      | diamond | 0      |
      | skull   | 0      |
      | saber   | 0      |
      | parrot  | 0      |
      | coin    | 8      |
      | monkey  | 0      |
    Then player gets 5400 scores

  Scenario: row 57 roll 8 coins first roll and player gets a score of 5400
    When fortunate card is "diamond"
    And player rolls
      | die     | values |
      | diamond | 0      |
      | skull   | 0      |
      | saber   | 0      |
      | parrot  | 0      |
      | coin    | 8      |
      | monkey  | 0      |
    Then player gets 5400 scores