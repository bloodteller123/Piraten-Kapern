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
    Player p;
    Player[] ps;
    @Given("Player is initialized")
    public void playerIsInitialized() {
        p = new Player(0,0);
    }
    @Given("players are initialized")
    public void playersAreInitialized() {
        ps = new Player[3];
        for(int i=0;i<ps.length;i++){
            ps[i] = new Player(i+1,0);
        }
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
//    @When("player rolls")
//    public void playerRolls(DataTable dataTable) {
//        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
//        List<Die> dies = new ArrayList<>();
//        for(Map<String, String> m : maps){
//            int occ = Integer.valueOf(m.get("values"));
//            while(occ >0){
//                dies.add(new Die(m.get("die")));
//                occ--;
//            }
//        }
//        p.setDice(dies);
//    }
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
//    @And("player rerolls {string} to get")
//    public void playerRerollsToGet(String arg0, DataTable dataTable) {
//        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
//        String[] ind = new String[maps.size()];
//        List<Die> list = p.getDice();
//        Set<Integer> set = new HashSet<>();
//        reroll_logic(maps, list, set, arg0);
//        p.setDice(list);
//        System.out.println(p.getDice());
//    }
//    @And("player dies")
//    public void playerDies() {
//        assertTrue(p.getSkullSize() >=3);
//        assertTrue(p.isEnd());
//    }
//    @Then("player gets {int} scores")
//    public void playerGetsScores(int scores) {
//        if(!(p.getSkullSize() >=3)){
//            System.out.println(p.getCard());
//            p.calculateScore(p.getDice());
//            assertEquals(scores, p.getInfo()[0]);
//        }else{
//            System.out.println("ELSE");
//            p.calculateScore(p.getDice());
//            System.out.println(p.getInfo()[0] + " "+p.getInfo()[1]);
//        }
//    }
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

//    @Then("player does skull check")
//    public void playerDoesSkullCheck() {
//        p.addSkulls(p.getDice());
//        p.skullCheck(false);
//    }

    @And("game ends")
    public void gameEnds() {
        assertTrue(p.isEnd());
    }

    @And("player deducts {int} points")
    public void playerDeductsPoints(int deduction) {
        p.calculateScore(p.getDice());
        assertEquals(deduction, p.getInfo()[1]);
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
}
