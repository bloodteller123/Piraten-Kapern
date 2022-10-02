package pirate;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    void InitializeDicetest() {
        Player p = new Player(0,0);
        assertNotNull(p.getDice());
    }

    @Test
    void InitializeSkullsTest(){
        Player p = new Player(0,0);
        assertNotNull(p.getSkulls());
    }
    @Test
    void addSkullsIndicesTest(){
        Player p = new Player(0,0);
        ArrayList<Die> dice = new ArrayList<>(Arrays.asList(new Die("skull  "),new Die("skull  "),
                new Die("skull  "),new Die("skull  "),new Die("skull  "),new Die("skull  "),
                new Die("skull  "), new Die("skull  ")));
        p.addSkulls(dice);
        assertEquals(8, p.getSkulls().size());
    }

    @Test
    void rollSomeTest(){
        Player p = new Player(0,0);
        assertNotNull(p.getDice());
        List<Die> l = p.getDice();

        p.rerollSome(new String[]{"0","1","2","3"});
        assertEquals(l.get(4).getFace(), p.getDice().get(4).getFace());
        assertEquals(l.get(5).getFace(),p.getDice().get(5).getFace());
        assertEquals(l.get(6).getFace(),p.getDice().get(6).getFace());
        assertEquals(l.get(7).getFace(),p.getDice().get(7).getFace());
    }

    @Test
    void rollAllTest(){
        Player p = new Player(0,0);
        assertNotNull(p.getDice());
        ArrayList<Die> dice = new ArrayList<>(Arrays.asList(new Die("coin  "),new Die("coin  "),
                new Die("coin  "),new Die("skull  "),new Die("skull  "),new Die("skull  "),
                new Die("skull  "), new Die("skull  ")));

        p.setDice(dice);
        p.addSkulls(dice);
        p.rerollAll();
        assertTrue(p.getSkulls().size() >= 5);

    }

    @Test
    void skullCheckTest(){
        Player p = new Player(0,0);
        ArrayList<Die> dice = new ArrayList<>(Arrays.asList(new Die("coin   "),new Die("coin   "),
                new Die("coin   "),new Die("skull  "),new Die("skull  "),new Die("skull  "),
                new Die("coin   "), new Die("coin   ")));
        p.setDice(dice);
        p.addSkulls(dice);
        p.skullCheck();
        assertTrue(p.getSkulls().size()==3);
        assertTrue(p.isEnd());
    }
    @Test
    void fullChestTest(){
        Player p = new Player(0,0);
        ArrayList<Die> dice = new ArrayList<>(Arrays.asList(new Die("monkey "),new Die("monkey "),
                new Die("monkey "),new Die("monkey "),new Die("monkey "),new Die("skull  "),
                new Die("monkey "), new Die("monkey ")));
        p.setDice(dice);
        assertFalse(p.checkFullChest());

        dice = new ArrayList<>(Arrays.asList(new Die("monkey "),new Die("monkey "),
                new Die("monkey "),new Die("monkey "),new Die("monkey "),new Die("monkey "),
                new Die("monkey "), new Die("monkey ")));
        p.setDice(dice);
        assertTrue(p.checkFullChest());
    }

    @Test
    void getFCTest(){
        Player p = new Player(0,0);
        FortuneCard fc = new FortuneCard();
        String card = fc.drawCard();
        p.setCard(card);
        assertEquals(card, p.getCard());
    }

    @Test
    void setScoreTest(){
        Player p = new Player(0,0);
        p.setScore(200);
        assertEquals(200, p.getScore());
        p.setScore(300);
        assertEquals(500, p.getScore());
        p.setScore(-500);
        assertEquals(0, p.getScore());
        p.setScore(-100);
        assertEquals(0, p.getScore());
    }

    @Test
    void calculateScoreTest(){
        Player p = new Player(0,0);
        ArrayList<Die> dice = new ArrayList<>(Arrays.asList(new Die("monkey "),new Die("monkey "),
                new Die("monkey "),new Die("monkey "),new Die("monkey "),new Die("monkey "),
                new Die("monkey "), new Die("monkey ")));
        p.setDice(dice);
        p.calculateScore(dice);
        assertEquals(4500, p.getScore());
        p.reset();
        dice = new ArrayList<>(Arrays.asList(new Die("monkey "),new Die("monkey "),
                new Die("parrot "),new Die("monkey "),new Die("monkey "),new Die("monkey "),
                new Die("parrot "), new Die("monkey ")));
        p.setCard("MP");
        p.setDice(dice);
        p.calculateScore(dice);
        assertEquals(4500, p.getScore());
        p.reset();
        dice = new ArrayList<>(Arrays.asList(new Die("monkey "),new Die("monkey "),
                new Die("monkey "),new Die("monkey "),new Die("monkey "),new Die("monkey "),
                new Die("diamond"), new Die("monkey ")));
        p.setDice(dice);
        p.calculateScore(dice);
        assertEquals(2600, p.getScore());
    }

    @Test
    public void getInfoTest(){
        Player p = new Player(0,0);
        int[] info = p.getInfo();
        assertEquals(2, info.length);
        assertEquals(0, info[0]);
        assertEquals(0, info[1]);
    }

    @Test
    public void removeSkullTest(){
        Player p = new Player(0,0);
        ArrayList<Die> dice = new ArrayList<>(Arrays.asList(new Die("monkey "),new Die("monkey "),
                new Die("monkey "),new Die("monkey "),new Die("monkey "),new Die("monkey "),
                new Die("skull  "), new Die("skull  ")));
        p.setDice(dice);
        p.addSkulls(dice);
        assertTrue(p.getSkulls().size()==2);
        p.removeSkull("6");
        assertTrue(p.getSkulls().size()==1);
    }

    @Test
    public void addToTreasureTest(){
        Player p = new Player(0,0);
        ArrayList<Die> dice = new ArrayList<>(Arrays.asList(new Die("monkey "),new Die("monkey "),
                new Die("coin  "),new Die("monkey "),new Die("diamond"),new Die("monkey "),
                new Die("skull  "), new Die("skull  ")));
        p.setDice(dice);
        p.addToTreasures(new String[]{"2"});
        assertEquals(1, p.getTreasures().size());
    }

    @Test
    public void removeFromTreasureTest(){
        Player p = new Player(0,0);
        ArrayList<Die> dice = new ArrayList<>(Arrays.asList(new Die("monkey "),new Die("monkey "),
                new Die("coin  "),new Die("monkey "),new Die("diamond"),new Die("monkey "),
                new Die("skull  "), new Die("skull  ")));
        p.setDice(dice);
        p.addToTreasures(new String[]{"2"});
        assertEquals(1, p.getTreasures().size());
        assertFalse(p.removeFromTreasures(new String[]{"3"}));
        assertTrue(p.removeFromTreasures(new String[]{"2"}));
        assertEquals(0, p.getTreasures().size());
    }

    @Test
    public void activateSeaBattlesTest(){
        Player p = new Player(0,0);
        p.activateSeaBattles(4);
        assertEquals(4, p.getSeabattleNum());
    }
    @Test
    public void checkRollSelectionTest(){
        Player p = new Player(0,0);
        ArrayList<Die> dice = new ArrayList<>(Arrays.asList(new Die("monkey "),new Die("monkey "),
                new Die("coin  "),new Die("monkey "),new Die("diamond"),new Die("monkey "),
                new Die("skull  "), new Die("skull  ")));
        p.setDice(dice);
        p.addSkulls(dice);
        p.addToTreasures(new String[]{"3"});
        assertFalse(p.checkRollSelection(new String[]{"6","7"}));
        assertFalse(p.checkRollSelection(new String[]{"3"}));
        assertTrue(p.checkRollSelection(new String[]{"1", "2"}));
    }

    @Test
    public void islandOfSkullTest(){
        Player p = new Player(0,0);
        ArrayList<Die> dice = new ArrayList<>(Arrays.asList(new Die("monkey "),new Die("monkey "),
                new Die("skull  "),new Die("monkey "),new Die("skull  "),new Die("monkey "),
                new Die("skull  "), new Die("skull  ")));
        p.setDice(dice);
        p.addSkulls(dice);
        assertTrue(p.getSkulls().size() >3);
        assertTrue(p.getIsIOS());
    }
}
