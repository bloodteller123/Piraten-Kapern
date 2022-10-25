Feature: Handle cases with different players
  Background:
    Given players are initialized
    And dice are initialized
  Scenario: player1 rolls 7 swords + 1 skull with FC captain (gets 4000 points - could win)
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