package pirate;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SinglePlayerScoringSDefs {
    Player p;

    @Given("Player is initialized")
    public void playerIsInitialized() {
        p = new Player(0,0);
    }
    @And("dice is initialized")
    public void diceIsInitialized() {
        p.initializeDice();
    }
    @When("player rolls {string} {string} and {string} {string}")
    public void playerRollsAnd(String arg0, String arg1, String arg2, String arg3) {
        p.getDice().forEach(d -> d.roll());
        // set to 3 skulls
        p.setDice(new ArrayList<>(Arrays.asList(new Die("skull  "), new Die("skull  "),
                new Die("skull  "), new Die("diamond"), new Die("diamond"), new Die("diamond"),
                new Die("diamond"), new Die("diamond"))));
    }

    @Then("player gets {int} scores")
    public void playerGetsScores(int arg0) {
        p.addSkulls(p.getDice());
        p.skullCheck();
        assertEquals(3, p.getSkulls().size());
        assertTrue(p.isEnd());
        assertEquals(0, p.getScore());
    }
}
