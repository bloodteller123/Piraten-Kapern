Feature: Handle cases with different players
  Background:
    Given players are initialized
    And dice are initialized
  Scenario: row 122 player1 rolls 7 swords + 1 skull with FC captain (gets 4000 points - could win)
  then player2 rolls 7 swords 1 skull  with FC 1 skull (2000)
  then player3 scores 0 (3 skulls, 5 monkeys, FC coin) => game stops and declares player 1 wins
    When player 1 has fortunate card "captain"
    And player 1 rolls
      | die     | values |
      | diamond | 0      |
      | skull   | 1      |
      | saber   | 7      |
      | parrot  | 0      |
      | coin    | 0      |
      | monkey  | 0      |
    And player 1 does skull check
    And player 1 gets 4000 scores
    And player 2 has fortunate card "1-skull"
    And player 2 rolls
      | die     | values |
      | diamond | 0      |
      | skull   | 1      |
      | saber   | 7      |
      | parrot  | 0      |
      | coin    | 0      |
      | monkey  | 0      |
    And player 2 does skull check
    And player 2 gets 2000 scores
    And player 3 has fortunate card "coin"
    And player 3 rolls
      | die     | values |
      | diamond | 0      |
      | skull   | 3      |
      | saber   | 0      |
      | parrot  | 0      |
      | coin    | 0      |
      | monkey  | 5      |
    And player 3 does skull check
    And player 3 gets 0 scores
    And player 3 dies
    Then player 1 wins


  Scenario: row 134 player 1 scores 0 with (3 skulls, 5 monkeys, FC Captain)
  player2 rolls 7 swords + 1 skull with FC captain (gets 4000 points - could win)
  then player3 scores 0 with FC 2 skulls and a roll of 1 skull & 7 swords
  then player 1 has FC Captain rolls 8 swords and thus gets 9000 points     => player 1 WINS
    When player 1 has fortunate card "captain"
    And player 1 rolls
      | die     | values |
      | diamond | 0      |
      | skull   | 3      |
      | saber   | 0      |
      | parrot  | 0      |
      | coin    | 0      |
      | monkey  | 5      |
    And player 1 does skull check
    And player 1 gets 0 scores
    And player 1 dies
    And player 2 has fortunate card "captain"
    And player 2 rolls
      | die     | values |
      | diamond | 0      |
      | skull   | 1      |
      | saber   | 7      |
      | parrot  | 0      |
      | coin    | 0      |
      | monkey  | 0      |
    And player 2 does skull check
    And player 2 gets 4000 scores
    And player 3 has fortunate card "2-skull"
    And player 3 rolls
      | die     | values |
      | diamond | 0      |
      | skull   | 1      |
      | saber   | 7      |
      | parrot  | 0      |
      | coin    | 0      |
      | monkey  | 0      |
    And player 3 does skull check
    And player 3 gets 0 scores
    And player 3 dies
    And player 1 has fortunate card "captain"
    And player 1 rolls
      | die     | values |
      | diamond | 0      |
      | skull   | 0      |
      | saber   | 8      |
      | parrot  | 0      |
      | coin    | 0      |
      | monkey  | 0      |
    And player 1 does skull check
    And player 1 gets 9000 scores
    Then player 1 wins

  Scenario: row 139 Player 1 rolls 6 swords, 2 skulls and FC coin, scores 1100 points
  Player 2 has FC Sorceress and rolls 7 skulls and a coin, uses sorceress to reroll a skull into a parrot
  then selects the coin and the parrot and gets 2 skulls: now has 8 skulls
  => -800 for player1 (now at 300) and still 0 for players 2 and 3
    When player 1 has fortunate card "coin"
    And player 1 rolls
      | die     | values |
      | diamond | 0      |
      | skull   | 2      |
      | saber   | 6      |
      | parrot  | 0      |
      | coin    | 0      |
      | monkey  | 0      |
    And player 1 does skull check
    And player 1 gets 1100 scores
    And player 2 has fortunate card "sorceress"
    And player 2 rolls
      | die     | values |
      | diamond | 0      |
      | skull   | 7      |
      | saber   | 0      |
      | parrot  | 0      |
      | coin    | 1      |
      | monkey  | 0      |
    And player 2 does skull check
    And player 2 enters islandofskulls
    And player 2 rerolls "skull" to get
      | die     | values |
      | parrot  | 1      |
    And player 2 rerolls "coin" to get
      | die     | values |
      | skull   | 1      |
    And player 2 rerolls "parrot" to get
      | die     | values |
      | skull   | 1      |
    And player 2 does skull check
    Then player 2 makes 800 deduction for other player
    And player 2 gets 0 scores
