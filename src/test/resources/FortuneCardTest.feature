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

  Scenario: row 74 roll 3 monkeys 3 parrots 1 skull 1 coin,  get 1100 points
    When fortunate card is "MP"
    And player rolls
      | die     | values |
      | diamond | 0      |
      | skull   | 1      |
      | saber   | 0      |
      | parrot  | 3      |
      | coin    | 1      |
      | monkey  | 3      |
    Then player gets 1100 scores

  Scenario: row 75 roll 2 coins 2 swords 2 monkeys 2 parrots, reroll 2 swords,
  get 1 monkey 1 parrot get 1700 points
    When fortunate card is "MP"
    And player rolls
      | die     | values |
      | diamond | 0      |
      | skull   | 0      |
      | saber   | 2      |
      | parrot  | 2      |
      | coin    | 2      |
      | monkey  | 2      |
    And player rerolls "saber" to get
      | die     | values |
      | parrot  | 1      |
      | monkey  | 1      |
    Then player gets 1700 scores

  Scenario: row 76 roll 3 monkeys 2 parrots 3 skull,  player dies and gets 0 points
    When fortunate card is "MP"
    And player rolls
      | die     | values |
      | diamond | 0      |
      | skull   | 3      |
      | saber   | 0      |
      | parrot  | 2      |
      | coin    | 0      |
      | monkey  | 3      |
    Then player gets 0 scores
    And player dies

  Scenario: row 79 roll 2 swords 2 diamonds 1 coin 3 parrots, put diamonds and coin in chest,
    rerolls 2 swords get 2 parrots, put 5 parrots in chest, take out 2 diamonds and 1 coin,
    rerolls diamonds, coin get 1 skull 1 coin and 1 parrot, get 1100 points
    When fortunate card is "chest"
    And player rolls
      | die     | values |
      | diamond | 2      |
      | skull   | 0      |
      | saber   | 2      |
      | parrot  | 3      |
      | coin    | 1      |
      | monkey  | 0      |
    And player puts dice in chest
      | diamond | 2      |
      | coin    | 1      |
    And player rerolls "saber" to get
      | die     | values |
      | parrot  | 2      |
    And player puts dice in chest
      | parrot  | 5      |
    And player takes out dice from chest
      | diamond | 2      |
      | coin    | 1      |
    And player rerolls "coin" to get
      | die     | values |
      | parrot  | 1      |
    And player rerolls "diamond" to get
      | die     | values |
      | coin    | 1      |
      | skull   | 1      |
    Then player gets 1100 scores

  Scenario: Row 84 roll 2 skulls, 3 parrots, 3 coins,put 3 coins in chest
  then rerolls 3 parrots and get 2 diamonds 1 coin, put coin in chest (now 4)
  then reroll 2 diamonds and get 1 skull 1 coin, score for chest only = 400 + 200 = 600 AND report death
    When fortunate card is "chest"
    And player rolls
      | die     | values |
      | diamond | 0      |
      | skull   | 2      |
      | saber   | 0      |
      | parrot  | 3      |
      | coin    | 3      |
      | monkey  | 0      |
    And player puts dice in chest
      | coin    | 3      |
    And player rerolls "parrot" to get
      | die     | values |
      | diamond | 2      |
      | coin    | 1      |
    And player puts dice in chest
      | coin    | 1      |
    And player rerolls "diamond" to get
      | die     | values |
      | skull   | 1      |
      | coin    | 1      |
    Then player gets 600 scores
    And player dies

  Scenario: row 89 roll 3 monkeys 3 swords 1 diamond 1 parrot,  get 400 points
    When fortunate card is "coin"
    And player rolls
      | die     | values |
      | diamond | 1      |
      | skull   | 0      |
      | saber   | 3      |
      | parrot  | 1      |
      | coin    | 0      |
      | monkey  | 3      |
    Then player gets 400 scores

  Scenario: row 90 roll 3 monkeys 3 swords 2 coins,  get 1800 points
    When fortunate card is "captain"
    And player rolls
      | die     | values |
      | diamond | 0      |
      | skull   | 0      |
      | saber   | 3      |
      | parrot  | 0      |
      | coin    | 2      |
      | monkey  | 3      |
    Then player gets 1800 scores

  Scenario: row 91 roll 3 monkeys 4 swords 1 diamond,  get 1000 points
    When fortunate card is "coin"
    And player rolls
      | die     | values |
      | diamond | 1      |
      | skull   | 0      |
      | saber   | 4      |
      | parrot  | 0      |
      | coin    | 0      |
      | monkey  | 3      |
    Then player gets 1000 scores

  Scenario: row 92 roll 1 coins 1 swords 4 monkeys 2 parrots, reroll 2 parrots,
  get 1 coin 1 sword get 1200 points
    When fortunate card is "2-sword"
    And player rolls
      | die     | values |
      | diamond | 0      |
      | skull   | 0      |
      | saber   | 1      |
      | parrot  | 2      |
      | coin    | 1      |
      | monkey  | 4      |
    And player rerolls "parrot" to get
      | die     | values |
      | saber   | 1      |
      | coin    | 1      |
    Then player gets 1200 scores

  Scenario: row 95 roll 2 monkeys 1 parrot 2 coins 3 diamond,  get 1200 points
    When fortunate card is "MP"
    And player rolls
      | die     | values |
      | diamond | 3      |
      | skull   | 0      |
      | saber   | 0      |
      | parrot  | 1      |
      | coin    | 2      |
      | monkey  | 2      |
    Then player gets 1200 scores