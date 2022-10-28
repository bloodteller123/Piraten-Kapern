Feature: Handle cases with different players

  Scenario: row 122 player1 rolls 7 swords + 1 skull with FC captain (gets 4000 points - could win)
  so player2 rolls 7 swords 1 skull  with FC 1 skull (2000)
  so player3 scores 0 (3 skulls, 5 monkeys, FC coin) => game stops and declares player 1 wins
    Given players are initialized
    And dice are initialized
    When player 1 has fortunate card "captain"
    And player 1 rolls "skull saber saber saber saber saber saber saber"
    And player 1 does skull check
    And player 1 gets 4000 scores
    And player 2 has fortunate card "1-skull"
    And player 2 rolls "skull saber saber saber saber saber saber saber"
    And player 2 does skull check
    And player 2 gets 2000 scores
    And player 3 has fortunate card "coin"
    And player 3 rolls "skull skull skull monkey monkey monkey monkey monkey"
    And player 3 does skull check
    And player 3 gets 0 scores
    And player 3 dies
    Then player 1 wins

  Scenario: row 134 player 1 scores 0 with (3 skulls, 5 monkeys, FC Captain)
  player2 rolls 7 swords + 1 skull with FC captain (gets 4000 points - could win)
  so player3 scores 0 with FC 2 skulls and a roll of 1 skull & 7 swords
  so player 1 has FC Captain rolls 8 swords and thus gets 9000 points     => player 1 WINS
    Given players are initialized
    And dice are initialized
    When player 1 has fortunate card "captain"
    And player 1 rolls "skull skull skull monkey monkey monkey monkey monkey"
    And player 1 does skull check
    And player 1 gets 0 scores
    And player 1 dies
    And player 2 has fortunate card "captain"
    And player 2 rolls "skull saber saber saber saber saber saber saber"
    And player 2 does skull check
    And player 2 gets 4000 scores
    And player 3 has fortunate card "2-skull"
    And player 3 rolls "skull saber saber saber saber saber saber saber"
    And player 3 does skull check
    And player 3 gets 0 scores
    And player 3 dies
    And player 1 has restarted the rounds
    And player 1 has fortunate card "captain"
    And player 1 rolls "saber saber saber saber saber saber saber saber"
    And player 1 does skull check
    And player 1 gets 9000 scores
    Then player 1 wins
#
  Scenario: row 139 Player 1 rolls 6 swords, 2 skulls and FC coin, scores 1100 points
  Player 2 has FC Sorceress and rolls 7 skulls and a coin, uses sorceress to reroll a skull into a parrot
  selects the coin and the parrot and gets 2 skulls: now has 8 skulls
  meaning-800 for player1 (now at 300) and still 0 for players 2 and 3
    Given players are initialized
    And dice are initialized
    When player 1 has fortunate card "coin"
    And player 1 rolls "skull skull saber saber saber saber saber saber"
    And player 1 does skull check
    And player 1 gets 1100 scores
    And player 2 has fortunate card "sorceress"
    And player 2 rolls "skull skull skull skull skull skull skull coin"
    And player 2 does skull check
    And player 2 enters islandofskulls
    And player 2 rerolls "skull" to get "parrot"
    And player 2 rerolls "coin" to get "skull"
    And player 2 rerolls "parrot" to get "skull"
    Then player 2 does skull check
    And player 2 makes 800 deduction for other player
    And player 2 gets 0 scores
