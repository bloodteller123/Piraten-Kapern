package pirate;

import scs.comp5903.cucumber.model.annotation.JGivenStep;
import scs.comp5903.cucumber.model.annotation.JThenStep;
import scs.comp5903.cucumber.model.annotation.JWhenStep;
import scs.comp5903.cucumber.model.annotation.JAndStep;

import java.util.*;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EasyCucumberSD {
    Player[] ps;
    @JGivenStep("players are initialized")
    public void playersAreInitialized() {
        ps = new Player[3];
        for(int i=0;i<ps.length;i++){
            ps[i] = new Player(i+1,0);
        }
    }
    public void reroll_logic(String[] list_d, List<Die> list, Set<Integer> set, String s){
        for(int i=0;i<list_d.length;i++){
//            int occ = Integer.parseInt(maps.get(i).get("values"));
//            String die_val = maps.get(i).get("die");
            String die_val = list_d[i];
            int index = IntStream.range(0, list.size())
                    .filter(j -> list.get(j).getFace().trim().equals(s) && !set.contains(j))
                    .findFirst()
                    .orElse(-1);
            if(index!=-1) {
                set.add(index);
                list.set(index, new Die(die_val));
            }
        }
    }
    public String[] buildIndArray(int total, String[] ss, List<Die> l){
        Map<String,Integer> map = new HashMap<>();
        String[] inds = new String[total];
        int ind = 0;
        for(String s : ss){
            for (int i = 0; i < l.size(); i++) {
                if (ind<inds.length && s.equals(l.get(i).toString())) {
                    if(!map.containsKey(s) || map.get(s)!=i){
                        inds[ind] = Integer.toString(i);
                        map.put(s,ind);
//                        System.out.println(ind);
                        ind++;
                    }
                }
            }
        }return inds;
    }
    @JGivenStep("Three players are initialized")
    public void threePlayersAreInitialized() {
        ps = new Player[3];
        for(int i=0;i<ps.length;i++){
            ps[i] = new Player(i+1,0);
        }
    }

    @JWhenStep("player {int} has fortunate card {string}")
    public void playerHasFortunateCard(int arg0, String arg1) {
        ps[arg0-1].setCard(arg1);
        if(arg1.contains("sword")) {
            ps[arg0-1].activateSeaBattles(Integer.parseInt(arg1.split("-")[0]));
        }else if(arg1.contains("skull")) {
            ps[arg0-1].setSkulls(arg1.split("-"));
        }
    }

    @JAndStep("player {int} has fortunate card {string}")
    public void playerHasFortunateCard_and(int arg0, String arg1) {
        ps[arg0-1].setCard(arg1);
        if(arg1.contains("sword")) {
            ps[arg0-1].activateSeaBattles(Integer.parseInt(arg1.split("-")[0]));
        }else if(arg1.contains("skull")) {
            ps[arg0-1].setSkulls(arg1.split("-"));
        }
    }

    @JAndStep("player {int} rolls {string}}")
    public void playerRolls(int arg0, String dice) {
        String[] list_d = dice.split(" ");
        List<Die> dies = new ArrayList<>();
        for(String s : list_d){
            dies.add(new Die(s));
        }
        ps[arg0-1].setDice(dies);
    }

    @JAndStep("player {int} rerolls {string} to get {string}")
    public void playerRerollsToGet(int arg0, String arg1, String dice) {
        String[] list_d = dice.split(" ");
        String[] ind = new String[list_d.length];
        List<Die> list = ps[arg0-1].getDice();
        Set<Integer> set = new HashSet<>();
        reroll_logic(list_d, list, set, arg1);
        ps[arg0-1].setDice(list);
//        System.out.println("playerRerollsToGet "+ps[arg0-1].getDice());
    }

    @JThenStep("player {int} does skull check")
    public void playerDoesSkullCheck(int arg0) {
        ps[arg0-1].addSkulls(ps[arg0-1].getDice());
        ps[arg0-1].skullCheck(false);
//        System.out.println("playerDoesSkullCheck"+ ps[arg0-1].getSkullSize());
    }

    @JAndStep("player {int} does skull check")
    public void playerDoesSkullCheck1(int arg0) {
        ps[arg0-1].addSkulls(ps[arg0-1].getDice());
        ps[arg0-1].skullCheck(false);
//        System.out.println("playerDoesSkullCheck"+ ps[arg0-1].getSkullSize());
    }
    @JAndStep("player {int} gets {int} scores")
    public void playerGetsScores(int arg0, int scores) {
//        System.out.println("player " +arg0);
        if(!(ps[arg0-1].getSkullSize() >=3)){
//            System.out.println("playerGetsScores_<3: "+ps[arg0-1].getCard());
//            System.out.println("playerGetsScores_<3: "+ps[arg0-1].getDice());
            ps[arg0-1].calculateScore(ps[arg0-1].getDice());
            assertEquals(scores, ps[arg0-1].getInfo()[0]);
        }else{
//            System.out.println("skull size > 3");
//            System.out.println(ps[arg0-1].getSkullSize());
//            System.out.println("playerGetsScores: "+ps[arg0-1].getDice());
//            ps[arg0-1].calculateScore(ps[arg0-1].getDice());
//            System.out.println(ps[arg0-1].getInfo()[0] + " "+ps[arg0-1].getInfo()[1]);
            if(ps[arg0-1].getTreasures().size()>0)
                assertEquals(scores,ps[arg0-1].getInfo()[0]);
            else
                assertEquals(0,ps[arg0-1].getInfo()[0]);
        }
    }

    @JAndStep("player {int} dies")
    public void playerDies(int arg0) {
        assertTrue(ps[arg0-1].getSkullSize() >=3);
        assertTrue(ps[arg0-1].isEnd());
    }

    @JAndStep("dice are initialized")
    public void diceAreInitialized() {
        for(Player p: ps){
            p.initializeDice();
            p.getDice().forEach(d->d.roll());
        }
    }

    @JAndStep("player {int} deducts {int} points")
    public void playerDeductsPoints(int arg0, int deduction) {
        ps[arg0-1].calculateScore(ps[arg0-1].getDice());
        assertEquals(deduction, ps[arg0-1].getInfo()[1]);
    }

    @JAndStep("player {int} enters islandofskulls")
    public void playerEntersIslandofskulls(int arg0) {
//        System.out.println("playerEntersIslandofskulls "+ps[arg0-1].getDice());
        assertTrue(ps[arg0-1].getSkullSize() >3);
    }

    @JAndStep("player {int} puts dice in chest {string}")
    public void playerPutsDiceInChest(int arg0,String s) {
        String[] ss = s.split(" ");
        int total = ss.length;
        List<Die> l = ps[arg0-1].getDice();
        String[] inds = buildIndArray(total, ss,l);
        ps[arg0-1].addToTreasures(inds);
    }

    @JAndStep("player {int} takes out dice from chest {string}")
    public void playerTakesOutDiceFromChest(int arg0, String s) {
        String[] ss = s.split(" ");
        int total = ss.length;
        List<Die> l = ps[arg0-1].getDice();
        String[] inds = buildIndArray(total, ss,l);
        ps[arg0-1].removeFromTreasures(inds);
    }

    @JAndStep("player {int} makes {int} deduction for other player")
    public void playerMakesDeductionForOtherPlayer(int arg0, int arg1) {
        ps[arg0-1].deductPoints(ps[arg0-1].getSkullSize());
        assertEquals((arg1 *= -1), ps[arg0-1].getInfo()[1]);
    }

    @JAndStep("player {int} round ends")
    public void playerRoundEnds(int arg0) {
        assertTrue(ps[arg0-1].isEnd());
    }

    @JThenStep("player {int} wins")
    public void playerWins(int arg0) {
        List<Integer> l = Arrays.asList(ps[0].getInfo()[0],ps[1].getInfo()[0],ps[2].getInfo()[0]);
//        System.out.println(l);
        assertEquals(arg0-1, l.indexOf(Collections.max(l)));
    }

    @JAndStep("player {int} has restarted the round")
    public void playerrestarts(int arg0){
        ps[arg0-1].reset();
    }
}
