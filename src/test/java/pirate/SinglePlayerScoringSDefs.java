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
    @When("player rolls {int} {string} , {int} {string}")
    public void playerRolls(int arg0, String arg1, int arg2, String arg3) {
        p.getDice().forEach(d -> d.roll());
        // set to 3 skulls
        p.setDice(new ArrayList<>(Arrays.asList(new Die(arg1), new Die(arg1),
                new Die(arg1), new Die(arg3), new Die(arg3), new Die(arg3),
                new Die(arg3), new Die(arg3))));
    }
    @Then("player gets {int} scores")
    public void playerGetsScores(int scores) {
        p.addSkulls(p.getDice());
        p.skullCheck();
        assertEquals(3, p.getSkulls().size());

        assertEquals(scores, p.getScore());
    }
    @And("player dies")
    public void playerDies() {
        assertTrue(p.isEnd());
    }
    @When("player rolls {int} {string} , {int} {string}, {int} {string}")
    public void playerRolls(int arg0, String arg1, int arg2, String arg3, int arg4, String arg5) {
        p.getDice().forEach(d -> d.roll());
        //1 skull, 4 parrots, 3 swords
        p.setDice(new ArrayList<>(Arrays.asList(new Die(arg1), new Die(arg3),
                new Die(arg3), new Die(arg3), new Die(arg3), new Die(arg5),
                new Die(arg5), new Die(arg5))));
    }
    @And("player rerolls {int} {string} to get {int} {string}, {int} {string}")
    public void playerRerollsToGet(int arg0, String arg1, int arg2, String arg3, int arg4, String arg5) {
        p.rerollSome(new String[]{"5", "6", "7"});
        p.setDice(new ArrayList<>(Arrays.asList(new Die("skull"), new Die("parrot "),
                new Die("parrot "), new Die("parrot "), new Die("parrot "), new Die(arg3),
                new Die(arg3), new Die(arg5))));
    }
}
