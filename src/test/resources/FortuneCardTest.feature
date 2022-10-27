Feature: Handle cases with different fortune cards

  Scenario: row 69 roll 2 diamonds, 1 monkey 1 coin, 1 swords 3 parrots, reroll 3 parrots,
  get 1 skulls 2 monkey, reroll skull get monkey get 500 points
    Given players are initialized
    And dice are initialized
    When player 1 has fortunate card "sorceress"
    And player 1 rolls "diamond diamond saber parrot parrot parrot coin monkey"
    And player 1 rerolls "parrot" to get "monkey monkey skull"
    And player 1 rerolls "skull" to get "monkey"
    Then player 1 does skull check
    And player 1 gets 500 scores

  Scenario: row 70 roll 3 skulls  2 swords 3 parrots, reroll 1 skull,
  get 1 parrot, reroll 2 swords get parrots get 1000 points
    Given players are initialized
    And dice are initialized
    When player 1 has fortunate card "sorceress"
    And player 1 rolls "skull skull skull saber saber parrot parrot parrot"
    And player 1 rerolls "skull" to get "parrot"
    And player 1 rerolls "saber" to get "parrot parrot"
    Then player 1 does skull check
    And player 1 gets 1000 scores
#
  Scenario: row 71 roll 1 skulls 3 monkeys 4 parrots, reroll 3 monkeys,
  get 1 skull 2 parrot, reroll skull get parrots get 2000 points
    Given players are initialized
    And dice are initialized
    When player 1 has fortunate card "sorceress"
    And player 1 rolls "skull parrot parrot parrot parrot monkey monkey monkey"
    And player 1 rerolls "monkey" to get "parrot parrot skull skull"
    And player 1 rerolls "skull" to get "parrot"
    Then player 1 does skull check
    And player 1 gets 2000 scores
#
  Scenario: row 74 roll 3 monkeys 3 parrots 1 skull 1 coin,  get 1100 points
    Given players are initialized
    And dice are initialized
    When player 1 has fortunate card "MP"
    And player 1 rolls "skull parrot parrot parrot coin monkey monkey monkey"
    Then player 1 does skull check
    And player 1 gets 1100 scores
#
  Scenario: row 75 roll 2 coins 2 swords 2 monkeys 2 parrots, reroll 2 swords,
  get 1 monkey 1 parrot get 1700 points
    Given players are initialized
    And dice are initialized
    When player 1 has fortunate card "MP"
    And player 1 rolls "saber saber parrot parrot coin coin monkey monkey"
    And player 1 rerolls "saber" to get "parrot monkey"
    Then player 1 does skull check
    And player 1 gets 1700 scores
#
  Scenario: row 76 roll 3 monkeys 2 parrots 3 skull,  player 1 dies and gets 0 points
    Given players are initialized
    And dice are initialized
    When player 1 has fortunate card "MP"
    And player 1 rolls "skull skull skull parrot parrot monkey monkey monkey"
    Then player 1 does skull check
    And player 1 gets 0 scores
    And player 1 dies
#
  Scenario: row 79 roll 2 swords 2 diamonds 1 coin 3 parrots, put diamonds and coin in chest,
    rerolls 2 swords get 2 parrots, put 5 parrots in chest, take out 2 diamonds and 1 coin,
    rerolls diamonds, coin get 1 skull 1 coin and 1 parrot, get 1100 points
    Given players are initialized
    And dice are initialized
    When player 1 has fortunate card "chest"
    And player 1 rolls "diamond diamond saber saber parrot parrot parrot coin"
    And player 1 puts dice in chest "diamond diamond coin"
    And player 1 rerolls "saber" to get "parrot parrot"
    And player 1 puts dice in chest "parrot parrot parrot parrot parrot"
    And player 1 takes out dice from chest "diamond diamond coin"
    And player 1 rerolls "coin" to get "parrot"
    And player 1 rerolls "diamond" to get "coin skull"
    Then player 1 does skull check
    And player 1 gets 1100 scores
#
  Scenario: Row 84 roll 2 skulls, 3 parrots, 3 coins,put 3 coins in chest
     rerolls 3 parrots and get 2 diamonds 1 coin, put coin in chest (now 4)
     reroll 2 diamonds and get 1 skull 1 coin, score for chest only = 400 + 200 = 600 AND report death
    Given players are initialized
    And dice are initialized
    When player 1 has fortunate card "chest"
    And player 1 rolls "skull skull parrot parrot parrot coin coin coin"
    And player 1 puts dice in chest "coin coin coin"
    And player 1 rerolls "parrot" to get "diamond diamond coin"
    And player 1 puts dice in chest "coin"
    And player 1 rerolls "diamond" to get "skull coin"
    Then player 1 does skull check
    And player 1 gets 600 scores
    And player 1 dies
#
  Scenario: row 89 roll 3 monkeys 3 swords 1 diamond 1 parrot,  get 400 points
    Given players are initialized
    And dice are initialized
    When player 1 has fortunate card "coin"
    And player 1 rolls "diamond saber saber saber parrot monkey monkey monkey"
    Then player 1 does skull check
    And player 1 gets 400 scores
#
  Scenario: row 90 roll 3 monkeys 3 swords 2 coins,  get 1800 points
    Given players are initialized
    And dice are initialized
    When player 1 has fortunate card "captain"
    And player 1 rolls "saber saber saber coin coin monkey monkey monkey"
    Then player 1 does skull check
    And player 1 gets 1800 scores
#
  Scenario: row 91 roll 3 monkeys 4 swords 1 diamond,  get 1000 points
    Given players are initialized
    And dice are initialized
    When player 1 has fortunate card "coin"
    And player 1 rolls "diamond saber saber saber saber monkey monkey monkey"
    Then player 1 does skull check
    And player 1 gets 1000 scores
#
  Scenario: row 92 roll 1 coins 1 swords 4 monkeys 2 parrots, reroll 2 parrots,
  get 1 coin 1 sword get 1200 points
    Given players are initialized
    And dice are initialized
    When player 1 has fortunate card "2-sword"
    And player 1 rolls "saber parrot parrot coin monkey monkey monkey monkey"
    And player 1 rerolls "parrot" to get "saber coin"
    Then player 1 does skull check
    And player 1 gets 1200 scores
#
  Scenario: row 95 roll 2 monkeys 1 parrot 2 coins 3 diamond,  get 1200 points
    Given players are initialized
    And dice are initialized
    When player 1 has fortunate card "MP"
    And player 1 rolls "diamond diamond diamond parrot coin coin monkey monkey"
    Then player 1 does skull check
    And player 1 gets 1200 scores
#
  Scenario: row 98 roll 1 skull 7 swords,  get 0 scores and die
    Given players are initialized
    And dice are initialized
    When player 1 has fortunate card "2-skull"
    And player 1 rolls "skull saber saber saber saber saber saber saber"
    Then player 1 does skull check
    And player 1 gets 0 scores
    And player 1 dies
#
  Scenario: row 99 roll 2 skull 6 swords,  get 0 scores and die
    Given players are initialized
    And dice are initialized
    When player 1 has fortunate card "1-skull"
    And player 1 rolls "skull skull saber saber saber saber saber saber"
    Then player 1 does skull check
    And player 1 gets 0 scores
    And player 1 dies
#
  Scenario: row 100 roll 2 skulls 3 monkeys 3 parrots, reroll 3 parrots,
    get 2 skulls 1 sword, reroll swords, monkeys get 3 skulls, 1 swords, get 0 points, deduction for
    other player 1s is 900 points
    Given players are initialized
    And dice are initialized
    When player 1 has fortunate card "2-skull"
    And player 1 rolls "skull skull parrot parrot parrot monkey monkey monkey"
    And player 1 does skull check
    And player 1 enters islandofskulls
    And player 1 rerolls "parrot" to get "skull skull saber"
    And player 1 rerolls "saber" to get "saber"
    And player 1 rerolls "monkey" to get "skull skull skull"
    Then player 1 does skull check
    And player 1 makes 900 deduction for other player
    And player 1 gets 0 scores
#
#
  Scenario: row 102 roll 5 skulls 3 monkeys, reroll 3 monkeys,
  get 1 coin 2 skulls get 0 points, deduction for other player 1s is 1400 points
    Given players are initialized
    And dice are initialized
    When player 1 has fortunate card "captain"
    And player 1 rolls "skull skull skull skull skull monkey monkey monkey"
    And player 1 rerolls "monkey" to get "skull skull coin"
    Then player 1 does skull check
    And player 1 makes 1400 deduction for other player
    And player 1 gets 0 scores
#
  Scenario: row 103 roll 3 skulls 5 swords, reroll 5 swords,
  get 5 coin get 0 points, deduction for other player 1s is 500 points
    Given players are initialized
    And dice are initialized
    When player 1 has fortunate card "2-skull"
    And player 1 rolls "skull skull skull saber saber saber saber saber"
    And player 1 rerolls "saber" to get "coin coin coin coin coin"
    Then player 1 does skull check
    And player 1 round ends
    And player 1 makes 500 deduction for other player
    And player 1 gets 0 scores
#
  Scenario: row 106 roll 3 skull 4 monkeys 1 sword,  lose 300 points and die
    Given players are initialized
    And dice are initialized
    When player 1 has fortunate card "2-sword"
    And player 1 rolls "skull skull skull saber monkey monkey monkey monkey"
    Then player 1 does skull check
    And player 1 deducts -300 points
    And player 1 dies
#
  Scenario: row 107 roll 2 skulls 2 swords 4 parrots, reroll 4 parrots,
  get 4 skulls, lose 500 points and die
    Given players are initialized
    And dice are initialized
    When player 1 has fortunate card "3-sword"
    And player 1 rolls "skull skull saber saber parrot parrot parrot parrot"
    And player 1 rerolls "parrot" to get "skull skull skull skull"
    Then player 1 does skull check
    And player 1 round ends
    And player 1 deducts -500 points
    And player 1 dies
#
#  Scenario: row 108 roll 3 skull 2 monkeys 3 sword,  lose 1000 points and die
#    Given players are initialized
#    And dice are initialized
#    When player 1 has fortunate card "4-sword"
#    And player 1 rolls
#      | die     | values |
#      | diamond | 0      |
#      | skull   | 3      |
#      | saber   | 3      |
#      | parrot  | 0      |
#      | coin    | 0      |
#      | monkey  | 2      |
#    Then player 1 does skull check
#    And player 1 deducts -1000 points
#    And player 1 dies
#
#  Scenario: row 109 roll 1 coin 2 parrots 3 monkeys 2 sword,  get 500 points
#    Given players are initialized
#    And dice are initialized
#    When player 1 has fortunate card "2-sword"
#    And player 1 rolls
#      | die     | values |
#      | diamond | 0      |
#      | skull   | 0      |
#      | saber   | 2      |
#      | parrot  | 2      |
#      | coin    | 1      |
#      | monkey  | 3      |
#    Then player 1 does skull check
#    And player 1 gets 500 scores
#
#  Scenario: row 110 roll 4 monkeys 1 sword 1 skull 2 parrots, reroll 2 parrots,
#  get 1 skull 1 sword, get 500 points
#    Given players are initialized
#    And dice are initialized
#    When player 1 has fortunate card "2-sword"
#    And player 1 rolls
#      | die     | values |
#      | diamond | 0      |
#      | skull   | 1      |
#      | saber   | 1      |
#      | parrot  | 2      |
#      | coin    | 0      |
#      | monkey  | 4      |
#    And player 1 rerolls "parrot" to get
#      | die     | values |
#      | skull   | 1      |
#      | saber   | 1      |
#    Then player 1 does skull check
#    And player 1 gets 500 scores
#
#  Scenario: row 112 roll 1 skull 3 monkeys 4 sword,  get 800 points
#    Given players are initialized
#    And dice are initialized
#    When player 1 has fortunate card "3-sword"
#    And player 1 rolls
#      | die     | values |
#      | diamond | 0      |
#      | skull   | 1      |
#      | saber   | 4      |
#      | parrot  | 0      |
#      | coin    | 0      |
#      | monkey  | 3      |
#    Then player 1 does skull check
#    And player 1 gets 800 scores
#
#  Scenario: row 113 roll 4 monkeys 2 sword 2 skull, reroll 4 monkeys,
#  get 2 skull 2 sword, lose 500 points and die
#    Given players are initialized
#    And dice are initialized
#    When player 1 has fortunate card "3-sword"
#    And player 1 rolls
#      | die     | values |
#      | diamond | 0      |
#      | skull   | 2      |
#      | saber   | 2      |
#      | parrot  | 0      |
#      | coin    | 0      |
#      | monkey  | 4      |
#    And player 1 rerolls "monkey" to get
#      | die     | values |
#      | skull   | 2      |
#      | saber   | 2      |
#    Then player 1 does skull check
#    And player 1 deducts -500 points
#    And player 1 dies
#
#  Scenario: row 115 roll 1 skull 3 monkeys 4 sword,  get 1300 points
#    Given players are initialized
#    And dice are initialized
#    When player 1 has fortunate card "4-sword"
#    And player 1 rolls
#      | die     | values |
#      | diamond | 0      |
#      | skull   | 1      |
#      | saber   | 4      |
#      | parrot  | 0      |
#      | coin    | 0      |
#      | monkey  | 3      |
#    Then player 1 does skull check
#    And player 1 gets 1300 scores
#
#  Scenario: row 116 roll 3 monkeys 1 sword 1 skull 1 diamond 2 parrots reroll 2 parrots,
#  get 2 swords, reroll 3 monkeys get 1 sword and 2 parrots, get 1300 points
#    Given players are initialized
#    And dice are initialized
#    When player 1 has fortunate card "4-sword"
#    And player 1 rolls
#      | die     | values |
#      | diamond | 1      |
#      | skull   | 1      |
#      | saber   | 1      |
#      | parrot  | 2      |
#      | coin    | 0      |
#      | monkey  | 3      |
#    And player 1 rerolls "parrot" to get
#      | die     | values |
#      | saber   | 2      |
#    And player 1 rerolls "monkey" to get
#      | die     | values |
#      | saber   | 1      |
#      | parrot  | 2      |
#    Then player 1 does skull check
#    And player 1 gets 1300 scores