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

public class PlayerStepDef {
    Player[] ps;
    @Given("players are initialized")
    public void playersAreInitialized() {
        ps = new Player[3];
        for(int i=0;i<ps.length;i++){
            ps[i] = new Player(i+1,0);
        }
    }
    public void reroll_logic(List<Map<String, String>> maps, List<Die> list, Set<Integer> set, String arg0){
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
    @Given("Three players are initialized")
    public void threePlayersAreInitialized() {
        ps = new Player[3];
        for(int i=0;i<ps.length;i++){
            ps[i] = new Player(i+1,0);
        }
    }

    @When("player {int} has fortunate card {string}")
    public void playerHasFortunateCard(int arg0, String arg1) {
        ps[arg0].setCard(arg1);
        if(arg1.contains("sword")) {
            ps[arg0].activateSeaBattles(Integer.parseInt(arg1.split("-")[0]));
        }else if(arg1.contains("skull")) {
            ps[arg0].setSkulls(arg1.split("-"));
        }
    }

    @And("player {int} rolls")
    public void playerRolls(int arg0, DataTable dataTable) {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        List<Die> dies = new ArrayList<>();
        for(Map<String, String> m : maps){
            int occ = Integer.parseInt(m.get("values"));
            while(occ >0){
                dies.add(new Die(m.get("die")));
                occ--;
            }
        }
        ps[arg0].setDice(dies);
    }

    @And("player {int} rerolls {string} to get")
    public void playerRerollsToGet(int arg0, String arg1, DataTable dataTable) {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        String[] ind = new String[maps.size()];
        List<Die> list = ps[arg0].getDice();
        Set<Integer> set = new HashSet<>();
        reroll_logic(maps, list, set, arg1);
        ps[arg0].setDice(list);
        System.out.println(ps[arg0].getDice());
    }

    @Then("player {int} does skull check")
    public void playerDoesSkullCheck(int arg0) {
        ps[arg0].addSkulls(ps[arg0].getDice());
        ps[arg0].skullCheck(false);
    }
    @And("player {int} gets {int} scores")
    public void playerGetsScores(int arg0, int scores) {
        if(!(ps[arg0].getSkullSize() >=3)){
            System.out.println(ps[arg0].getCard());
            ps[arg0].calculateScore(ps[arg0].getDice());
            assertEquals(scores, ps[arg0].getInfo()[0]);
        }else{
            System.out.println("ELSE");
            ps[arg0].calculateScore(ps[arg0].getDice());
            System.out.println(ps[arg0].getInfo()[0] + " "+ps[arg0].getInfo()[1]);
        }
    }

    @And("player {int} dies")
    public void playerDies(int arg0) {
        assertTrue(ps[arg0].getSkullSize() >=3);
        assertTrue(ps[arg0].isEnd());
    }

    @And("dice are initialized")
    public void diceAreInitialized() {
        for(Player p: ps){
            p.initializeDice();
            p.getDice().forEach(d->d.roll());
        }
    }

    @And("player {int} deducts {int} points")
    public void playerDeductsPoints(int arg0, int deduction) {
        ps[arg0].calculateScore(ps[arg0].getDice());
        assertEquals(deduction, ps[arg0].getInfo()[1]);
    }

    @And("player {int} enters islandofskulls")
    public void playerEntersIslandofskulls(int arg0) {
        System.out.println(ps[arg0].getDice());
        assertTrue(ps[arg0].getSkullSize() >3);
    }

    @And("player {int} puts dice in chest")
    public void playerPutsDiceInChest(int arg0,Map<String, String> map) {
        int total = map.values().stream().mapToInt(Integer::parseInt).sum();
        List<Die> l = ps[arg0].getDice();
        String[] inds = buildIndArray(total, map,l);
        ps[arg0].addToTreasures(inds);
    }

    @And("player {int} takes out dice from chest")
    public void playerTakesOutDiceFromChest(int arg0, Map<String, String> map) {
        int total = map.values().stream().mapToInt(Integer::parseInt).sum();
        List<Die> l = ps[arg0].getDice();
        String[] inds = buildIndArray(total, map,l);
        ps[arg0].removeFromTreasures(inds);
    }

    @And("player {int} makes {int} deduction for other player")
    public void playerMakesDeductionForOtherPlayer(int arg0, int arg1) {
        ps[arg0].deductPoints(ps[arg0].getSkullSize());
        assertEquals((arg1 *= -1), ps[arg0].getInfo()[1]);
    }

    @And("player {int} round ends")
    public void playerRoundEnds(int arg0) {
        assertTrue(ps[arg0].isEnd());
    }
}
