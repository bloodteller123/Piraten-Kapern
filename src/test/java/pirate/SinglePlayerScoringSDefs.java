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
        if(arg0.contains("sword")) {
            p.activateSeaBattles(Integer.parseInt(arg0.split("-")[0]));
        }else if(arg0.contains("skull")) {
            p.setSkulls(arg0.split("-"));
        }
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
            int occ = Integer.parseInt(maps.get(i).get("values"));
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
        assertTrue(p.getSkullSize() >=3);
        assertTrue(p.isEnd());
    }
    @Then("player gets {int} scores")
    public void playerGetsScores(int scores) {
        if(!(p.getSkullSize() >=3)){
            System.out.println(p.getCard());
            p.calculateScore(p.getDice());
            System.out.println(p.getDice());
            assertEquals(scores, p.getInfo()[0]);
        }else{
            System.out.println("ELSE");
            p.calculateScore(p.getDice());
            System.out.println(p.getInfo()[0] + " "+p.getInfo()[1]);
        }
    }
    @And("player puts dice in chest")
    public void playerPutsDiceInChest(Map<String, String> map) {
        int total = map.values().stream().mapToInt(Integer::parseInt).sum();
        List<Die> l = p.getDice();
        String[] inds = buildIndArray(total, map,l);
        p.addToTreasures(inds);
    }
    @And("player takes out dice from chest")
    public void playerTakesOutDiceFromChest(Map<String, String> map) {
        int total = map.values().stream().mapToInt(Integer::parseInt).sum();
        List<Die> l = p.getDice();
        String[] inds = buildIndArray(total, map,l);
        p.removeFromTreasures(inds);
    }
    public String[] buildIndArray(int total, Map<String, String> map, List<Die> l){
        String[] inds = new String[total];
        int ind = 0;
        System.out.println(map);
        for(Map.Entry<String,String> m : map.entrySet()){
            for (int i = 0; i < l.size(); i++) {
                if (ind<inds.length && m.getKey().equals(l.get(i).toString())) {
                    inds[ind] = Integer.toString(i);
                    ind++;
                }
            }
        }
        return inds;
    }

    @And("player enters islandofskulls")
    public void playerEntersIslandofskulls() {
        System.out.println(p.getDice());
        assertTrue(p.getSkullSize() >3);
    }

    @And("deduction for other player is {int} points")
    public void deductionForOtherPlayerIsPoints(int arg0) {
        p.deductPoints(p.getSkullSize());
        assertEquals((arg0 *= -1), p.getInfo()[1]);
    }

    @Then("player does skull check")
    public void playerDoesSkullCheck() {
        p.addSkulls(p.getDice());
        p.skullCheck(false);
    }
}
