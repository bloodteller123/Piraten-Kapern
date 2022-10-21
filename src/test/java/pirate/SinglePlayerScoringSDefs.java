package pirate;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.*;
import java.util.stream.IntStream;

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
        p.getDice().forEach(d -> d.roll());
    }
    @When("fortunate card is {string}")
    public void fortunateCardIs(String arg0) {
        p.setCard(arg0);
    }
    @When("player rolls")
    public void playerRolls(DataTable dataTable) {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
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
    @And("player rerolls {string} to get")
    public void playerRerollsToGet(String arg0, DataTable dataTable) {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        String[] ind = new String[maps.size()];
        List<Die> list = p.getDice();
        Set<Integer> set = new HashSet<>();
        for(int i=0;i<maps.size();i++){
            int occ = Integer.valueOf(maps.get(i).get("values"));
            String die_val = maps.get(i).get("die");
            while(occ >0){
                int index = IntStream.range(0, list.size())
                        .filter(j -> list.get(j).getFace().trim().equals(arg0) && !set.contains(j))
                        .findFirst()
                        .orElse(-1);
                if(index!=-1) {
                    set.add(index);
                    list.set(index, new Die(die_val));
                }
                occ--;
            }
        }
        p.setDice(list);
    }
    @And("player dies")
    public void playerDies() {
        assertTrue(p.getSkulls().size() >=3);
        assertTrue(p.isEnd());
    }
    @Then("player gets {int} scores")
    public void playerGetsScores(int scores) {
        p.addSkulls(p.getDice());
        p.skullCheck();
        if(!(p.getSkulls().size() >=3)){
            System.out.println(p.getCard());
            p.calculateScore(p.getDice());
            System.out.println(p.getDice());
            assertEquals(scores, p.getInfo()[0]);
        }
    }
}
