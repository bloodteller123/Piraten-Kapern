package pirate;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
    @When("player rolls")
    public void playerRolls(DataTable dataTable) {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        System.out.println(maps);
        List<Die> dies = new ArrayList<>();
        for(Map<String, String> m : maps){
            int occ = Integer.valueOf(m.get("values"));
            while(occ >0){
                dies.add(new Die(m.get("die")));
                occ--;
            }
        }
        p.setDice(dies);
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
    @And("player rerolls {int} {string} to get {int} {string}, {int} {string}")
    public void playerRerollsToGet(int arg0, String arg1, int arg2, String arg3, int arg4, String arg5) {
        p.rerollSome(new String[]{"5", "6", "7"});
        p.setDice(new ArrayList<>(Arrays.asList(new Die("skull"), new Die("parrot "),
                new Die("parrot "), new Die("parrot "), new Die("parrot "), new Die(arg3),
                new Die(arg3), new Die(arg5))));
    }

}
