package pirate;


import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AcceptanceTest {
    @BeforeAll
    public static void beforeTestCaseOneClass(){
        System.out.println("————————————————");
        System.out.println();
        System.out.println("Executing Test methods of Test Case One class…");
        System.out.println("————————————————");
        System.out.println();
    }

    @Test
    @Order(1)
    public void Test44(){
        Player p = new Player(0,0);
        p.initializeDice();
        // on first roll
        p.getDice().forEach(d -> d.roll());
        // set to 3 skulls
        p.setDice(new ArrayList<>(Arrays.asList(new Die("skull  "),new Die("skull  "),
                new Die("skull  "),new Die("diamond"),new Die("diamond"),new Die("diamond"),
                new Die("diamond"), new Die("diamond"))));
        // check # skulls after first roll
        p.addSkulls(p.getDice());
        p.skullCheck();
        assertEquals(3, p.getSkulls().size());
        assertTrue(p.isEnd());
        assertEquals(0, p.getScore());
        System.out.println("TEST 44 passed");
    }

    @Test
    @Order(2)
    public void Test45(){
        Player p = new Player(0,0);
        p.initializeDice();
        // on first roll
        p.getDice().forEach(d -> d.roll());

        //1 skull, 4 parrots, 3 swords
        p.setDice(new ArrayList<>(Arrays.asList(new Die("skull  "),new Die("parrot "),
                new Die("parrot "),new Die("parrot "),new Die("parrot "),new Die("saber  "),
                new Die("saber  "), new Die("saber  "))));

        p.rerollSome(new String[]{"5", "6", "7"});
        assertEquals("parrot ", p.getDice().get(1).getFace());
        assertEquals("parrot ", p.getDice().get(2).getFace());
        assertEquals("parrot ", p.getDice().get(3).getFace());
        assertEquals("parrot ", p.getDice().get(4).getFace());
        p.reset();
        p.setDice(new ArrayList<>(Arrays.asList(new Die("skull  "),new Die("parrot "),
                new Die("parrot "),new Die("parrot "),new Die("parrot "),new Die("skull  "),
                new Die("skull  "), new Die("saber  "))));
        // check # skulls after re roll
        p.addSkulls(p.getDice());
        p.skullCheck();
        assertEquals(3, p.getSkulls().size());
        assertTrue(p.isEnd());
        assertEquals(0, p.getScore());
        System.out.println("TEST 45 passed");
    }

    @Test
    @Order(3)
    public void Test46(){
        Player p = new Player(0,0);
        p.initializeDice();
        // on first roll
        p.getDice().forEach(d -> d.roll());

        //2 skull, 4 parrots, 2 swords
        p.setDice(new ArrayList<>(Arrays.asList(new Die("skull  "),new Die("skull  "),
                new Die("parrot "),new Die("parrot "),new Die("parrot "),new Die("parrot "),
                new Die("saber  "), new Die("saber  "))));

        p.rerollSome(new String[]{"6", "7"});
        assertEquals("parrot ", p.getDice().get(5).getFace());
        assertEquals("parrot ", p.getDice().get(2).getFace());
        assertEquals("parrot ", p.getDice().get(3).getFace());
        assertEquals("parrot ", p.getDice().get(4).getFace());
        p.reset();
        p.setDice(new ArrayList<>(Arrays.asList(new Die("skull  "),new Die("skull  "),
                new Die("parrot "),new Die("parrot "),new Die("parrot "),new Die("parrot "),
                new Die("skull  "), new Die("saber  "))));
        // check # skulls after re roll
        p.addSkulls(p.getDice());
        p.skullCheck();
        assertEquals(3, p.getSkulls().size());
        assertTrue(p.isEnd());
        assertEquals(0, p.getScore());
        System.out.println("TEST 46 passed");
    }

    @Test
    @Order(4)
    public void Test47(){
        Player p = new Player(0,0);
        p.initializeDice();
        // on first roll
        p.getDice().forEach(d -> d.roll());

        //1 skull, 4 parrots, 3 swords
        p.setDice(new ArrayList<>(Arrays.asList(new Die("skull  "),new Die("parrot "),
                new Die("parrot "),new Die("parrot "),new Die("parrot "),new Die("saber  "),
                new Die("saber  "), new Die("saber  "))));

        p.rerollSome(new String[]{"5", "6", "7"});
        assertEquals("parrot ", p.getDice().get(1).getFace());
        assertEquals("parrot ", p.getDice().get(2).getFace());
        assertEquals("parrot ", p.getDice().get(3).getFace());
        assertEquals("parrot ", p.getDice().get(4).getFace());
        p.reset();
        // 2 skulls 4 parrots 2 monkeys
        p.setDice(new ArrayList<>(Arrays.asList(new Die("skull  "),new Die("parrot "),
                new Die("parrot "),new Die("parrot "),new Die("parrot "),new Die("skull  "),
                new Die("monkey "), new Die("monkey "))));

        p.rerollSome(new String[]{"6", "7"});
        assertEquals("skull  ", p.getDice().get(0).getFace());
        assertEquals("skull  ", p.getDice().get(5).getFace());
        assertEquals("parrot ", p.getDice().get(1).getFace());
        assertEquals("parrot ", p.getDice().get(2).getFace());
        assertEquals("parrot ", p.getDice().get(3).getFace());
        assertEquals("parrot ", p.getDice().get(4).getFace());
        p.reset();
        // 3 skulls 4 parrots 1 monkeys
        p.setDice(new ArrayList<>(Arrays.asList(new Die("skull  "),new Die("parrot "),
                new Die("parrot "),new Die("parrot "),new Die("parrot "),new Die("skull  "),
                new Die("skull  "), new Die("monkey "))));
        // check # skulls after re roll
        p.addSkulls(p.getDice());
        p.skullCheck();
        assertEquals(3, p.getSkulls().size());
        assertTrue(p.isEnd());
        assertEquals(0, p.getScore());
        System.out.println("TEST 47 passed");
    }

    @Test
    public void Test49(){
        Player p = new Player(0,0);
        p.initializeDice();
        // on first roll
        p.getDice().forEach(d -> d.roll());
        //1 skull, 2 parrots, 3 swords 2 coins
        List<Die> dice = new ArrayList<>(Arrays.asList(new Die("skull  "),new Die("parrot "),
                new Die("parrot "),new Die("coin   "),new Die("coin   "),new Die("saber  "),
                new Die("saber  "), new Die("saber  ")));
        p.setDice(dice);
        p.setCard("coin");
        p.rerollSome(new String[]{"1", "2"});
        dice = new ArrayList<>(Arrays.asList(new Die("skull  "),new Die("coin   "),
                new Die("coin   "),new Die("coin   "),new Die("coin   "),new Die("saber  "),
                new Die("saber  "), new Die("saber  ")));
        p.setDice(dice);
        p.rerollSome(new String[]{"5", "6","7"});
        dice = new ArrayList<>(Arrays.asList(new Die("skull  "),new Die("coin   "),
                new Die("coin   "),new Die("coin   "),new Die("coin   "),new Die("coin   "),
                new Die("coin   "), new Die("coin   ")));
        p.setDice(dice);
        p.calculateScore(dice);
        assertEquals(4800, p.getScore());
    }

    @Test
    public void Test51(){
        Player p = new Player(0,0);
        p.initializeDice();
        // on first roll
        p.getDice().forEach(d -> d.roll());
        //1 skull, 2 parrots, 1 swords 2 coins 2 diamonds
        List<Die> dice = new ArrayList<>(Arrays.asList(new Die("skull  "),new Die("parrot "),
                new Die("parrot "),new Die("coin   "),new Die("coin   "),new Die("saber  "),
                new Die("diamond"), new Die("diamond")));
        p.setDice(dice);
        p.setCard("captain");
        p.calculateScore(dice);
        assertEquals(800, p.getInfo()[0]);
    }

    @Test
    public void Test52(){
        Player p = new Player(0,0);
        p.initializeDice();
        // on first roll
        p.getDice().forEach(d -> d.roll());
        //2 skull, 2 parrots, 2 swords 0 coins 0 diamonds 2 monkeys
        List<Die> dice = new ArrayList<>(Arrays.asList(new Die("skull  "),new Die("parrot "),
                new Die("parrot "),new Die("skull  "),new Die("saber  "),new Die("saber  "),
                new Die("monkey "), new Die("monkey ")));
        p.setDice(dice);
        p.setCard("coin");
        p.rerollSome(new String[]{"1"});
        // 3 monkeys
        dice = new ArrayList<>(Arrays.asList(new Die("skull  "),new Die("monkey "),
                new Die("parrot "),new Die("skull  "),new Die("saber  "),new Die("saber  "),
                new Die("monkey "), new Die("monkey ")));
        p.setDice(dice);
        p.calculateScore(dice);
        assertEquals(200, p.getInfo()[0]);
    }

    @Test
    public void Test53(){
        Player p = new Player(0,0);
        p.initializeDice();
        // on first roll
        p.getDice().forEach(d -> d.roll());
        //2 skull, 0 parrots, 3 swords 0 coins 0 diamonds 3 monkeys
        List<Die> dice = new ArrayList<>(Arrays.asList(new Die("skull  "),new Die("saber  "),
                new Die("monkey "),new Die("skull  "),new Die("saber  "),new Die("saber  "),
                new Die("monkey "), new Die("monkey ")));
        p.setDice(dice);
        p.setCard("coin");
        p.calculateScore(dice);
        assertEquals(300, p.getInfo()[0]);
    }

    @Test
    public void Test54(){
        Player p = new Player(0,0);
        p.initializeDice();
        // on first roll
        p.getDice().forEach(d -> d.roll());
        //2 skull, 1 parrots, 2 swords 0 coins 0 diamonds 3 monkeys
        List<Die> dice = new ArrayList<>(Arrays.asList(new Die("skull  "),new Die("parrot "),
                new Die("monkey "),new Die("skull  "),new Die("saber  "),new Die("saber  "),
                new Die("monkey "), new Die("monkey ")));
        p.setDice(dice);
        p.setCard("coin");
        p.rerollSome(new String[]{"1"});
        // 3 monkeys 3 sabers
        dice = new ArrayList<>(Arrays.asList(new Die("skull  "),new Die("saber  "),
                new Die("monkey "),new Die("skull  "),new Die("saber  "),new Die("saber  "),
                new Die("monkey "), new Die("monkey ")));
        p.setDice(dice);
        p.calculateScore(dice);
        assertEquals(300, p.getInfo()[0]);
    }

    @Test
    public void Test55(){
        Player p = new Player(0,0);
        p.initializeDice();
        // on first roll
        p.getDice().forEach(d -> d.roll());
        //2 skull, 0 parrots, 2 swords 0 coins 3 diamonds 1 monkeys
        List<Die> dice = new ArrayList<>(Arrays.asList(new Die("skull  "),new Die("saber  "),
                new Die("monkey "),new Die("skull  "),new Die("saber  "),new Die("diamond"),
                new Die("diamond"), new Die("diamond")));
        p.setDice(dice);
        p.setCard("coin");
        p.calculateScore(dice);
        assertEquals(500, p.getInfo()[0]);
    }

    @Test
    public void Test56(){
        Player p = new Player(0,0);
        p.initializeDice();
        // on first roll
        p.getDice().forEach(d -> d.roll());
        //2 skull, 0 parrots, 1 swords 4 coins 0 diamonds 1 monkeys
        List<Die> dice = new ArrayList<>(Arrays.asList(new Die("skull  "),new Die("saber  "),
                new Die("monkey "),new Die("skull  "),new Die("coin   "),new Die("coin   "),
                new Die("coin   "), new Die("coin   ")));
        p.setDice(dice);
        p.setCard("diamond");
        p.calculateScore(dice);
        assertEquals(700, p.getInfo()[0]);
    }

    @Test
    public void Test57(){
        Player p = new Player(0,0);
        p.initializeDice();
        // on first roll
        p.getDice().forEach(d -> d.roll());
        //2 skull, 4 parrots, 3 swords 0 coins 0 diamonds 0 monkeys
        List<Die> dice = new ArrayList<>(Arrays.asList(new Die("skull  "),new Die("saber  "),
                new Die("parrot "),new Die("parrot "),new Die("saber  "),new Die("saber  "),
                new Die("parrot "), new Die("parrot ")));
        p.setDice(dice);
        p.setCard("coin");
        p.calculateScore(dice);
        assertEquals(400, p.getInfo()[0]);
    }

    @Test
    public void Test58(){
        Player p = new Player(0,0);
        p.initializeDice();
        // on first roll
        p.getDice().forEach(d -> d.roll());
        //2 skull, 1 parrots, 2 swords 0 coins 0 diamonds 3 monkeys
        List<Die> dice = new ArrayList<>(Arrays.asList(new Die("parrot "),new Die("parrot "),
                new Die("monkey "),new Die("saber  "),new Die("saber  "),new Die("saber  "),
                new Die("monkey "), new Die("monkey ")));
        p.setDice(dice);
        p.setCard("coin");
        p.rerollSome(new String[]{"0", "1", "2", "6", "7"});
        // 3 coins 4 sabers
        dice = new ArrayList<>(Arrays.asList(new Die("skull  "),new Die("coin   "),
                new Die("coin   "),new Die("saber  "),new Die("saber  "),new Die("saber  "),
                new Die("coin   "), new Die("saber  ")));
        p.setDice(dice);
        p.calculateScore(dice);
        assertEquals(800, p.getInfo()[0]);
    }

    @Test
    public void Test59(){
        Player p = new Player(0,0);
        p.initializeDice();
        // on first roll
        p.getDice().forEach(d -> d.roll());
        //2 skull, 1 parrots, 2 swords 0 coins 0 diamonds 3 monkeys
        List<Die> dice = new ArrayList<>(Arrays.asList(new Die("parrot "),new Die("parrot "),
                new Die("monkey "),new Die("saber  "),new Die("saber  "),new Die("saber  "),
                new Die("monkey "), new Die("monkey ")));
        p.setDice(dice);
        p.setCard("captain");
        p.rerollSome(new String[]{"0", "1", "2", "6", "7"});
        // 3 coins 4 sabers
        dice = new ArrayList<>(Arrays.asList(new Die("skull  "),new Die("coin   "),
                new Die("coin   "),new Die("saber  "),new Die("saber  "),new Die("saber  "),
                new Die("coin   "), new Die("saber  ")));
        p.setDice(dice);
        p.calculateScore(dice);
        assertEquals(1200, p.getInfo()[0]);
    }

    @Test
    public void Test60(){
        Player p = new Player(0,0);
        p.initializeDice();
        // on first roll
        p.getDice().forEach(d -> d.roll());
        //2 skull, 1 parrots, 2 swords 0 coins 0 diamonds 3 monkeys
        List<Die> dice = new ArrayList<>(Arrays.asList(new Die("parrot "),new Die("parrot "),
                new Die("monkey "),new Die("saber  "),new Die("saber  "),new Die("saber  "),
                new Die("monkey "), new Die("monkey ")));
        p.setDice(dice);
        p.setCard("coin");
        p.rerollSome(new String[]{"0", "1", "2", "6", "7"});
        // 3 coins 4 sabers
        dice = new ArrayList<>(Arrays.asList(new Die("skull  "),new Die("coin   "),
                new Die("coin   "),new Die("saber  "),new Die("saber  "),new Die("saber  "),
                new Die("coin   "), new Die("saber  ")));

        p.rerollSome(new String[]{"1" ,"2", "6"});
        //  5 sabers
        dice = new ArrayList<>(Arrays.asList(new Die("skull  "),new Die("saber  "),
                new Die("monkey "),new Die("saber  "),new Die("saber  "),new Die("saber  "),
                new Die("skull  "), new Die("saber  ")));
        p.setDice(dice);
        p.calculateScore(dice);
        assertEquals(600, p.getInfo()[0]);
    }
    @Test
    public void Test61(){
        Player p = new Player(0,0);
        p.initializeDice();
        // on first roll
        p.getDice().forEach(d -> d.roll());
        //2 skull, 0 parrots, 0 swords 0 coins 0 diamonds 6 monkeys
        List<Die> dice = new ArrayList<>(Arrays.asList(new Die("skull  "),new Die("saber  "),
                new Die("monkey "),new Die("monkey "),new Die("monkey "),new Die("monkey "),
                new Die("monkey "), new Die("monkey ")));
        p.setDice(dice);
        p.setCard("coin");
        p.calculateScore(dice);
        assertEquals(1100, p.getInfo()[0]);
    }
    @Test
    public void Test62(){
        Player p = new Player(0,0);
        p.initializeDice();
        // on first roll
        p.getDice().forEach(d -> d.roll());
        //1 skull, 7 parrots, 0 swords 0 coins 0 diamonds 0 monkeys
        List<Die> dice = new ArrayList<>(Arrays.asList(new Die("skull  "),new Die("parrot "),
                new Die("parrot "),new Die("parrot "),new Die("parrot "),new Die("parrot "),
                new Die("parrot "), new Die("parrot ")));
        p.setDice(dice);
        p.setCard("coin");
        p.calculateScore(dice);
        assertEquals(2100, p.getInfo()[0]);
    }
    @Test
    public void Test63(){
        Player p = new Player(0,0);
        p.initializeDice();
        // on first roll
        p.getDice().forEach(d -> d.roll());
        //0 skull, 0 parrots, 0 swords 8 coins 0 diamonds 0 monkeys
        List<Die> dice = new ArrayList<>(Arrays.asList(new Die("coin   "),new Die("coin   "),
                new Die("coin   "),new Die("coin   "),new Die("coin   "),new Die("coin   "),
                new Die("coin   "), new Die("coin   ")));
        p.setDice(dice);
        p.setCard("coin");
        p.calculateScore(dice);
        assertEquals(5400, p.getInfo()[0]);
    }
    @Test
    public void Test64(){
        Player p = new Player(0,0);
        p.initializeDice();
        // on first roll
        p.getDice().forEach(d -> d.roll());
        //0 skull, 0 parrots, 0 swords 8 coins 0 diamonds 0 monkeys
        List<Die> dice = new ArrayList<>(Arrays.asList(new Die("coin   "),new Die("coin   "),
                new Die("coin   "),new Die("coin   "),new Die("coin   "),new Die("coin   "),
                new Die("coin   "), new Die("coin   ")));
        p.setDice(dice);
        p.setCard("diamond");
        p.calculateScore(dice);
        assertEquals(5400, p.getInfo()[0]);
    }
    @Test
    public void Test65(){
        Player p = new Player(0,0);
        p.initializeDice();
        // on first roll
        p.getDice().forEach(d -> d.roll());
        //0 skull, 0 parrots, 8 swords 0 coins 0 diamonds 0 monkeys
        List<Die> dice = new ArrayList<>(Arrays.asList(new Die("saber  "),new Die("saber  "),
                new Die("saber  "),new Die("saber  "),new Die("saber  "),new Die("saber  "),
                new Die("saber  "), new Die("saber  ")));
        p.setDice(dice);
        p.setCard("captain");
        p.calculateScore(dice);
        assertEquals(9000, p.getInfo()[0]);
    }
    @Test
    public void Test66(){
        Player p = new Player(0,0);
        p.initializeDice();
        // on first roll
        p.getDice().forEach(d -> d.roll());
        //0 skull, 2 parrots, 3 swords 0 coins 0 diamonds 3 monkeys
        List<Die> dice = new ArrayList<>(Arrays.asList(new Die("parrot "),new Die("parrot "),
                new Die("monkey "),new Die("saber  "),new Die("saber  "),new Die("saber  "),
                new Die("monkey "), new Die("monkey ")));
        p.setDice(dice);
        p.setCard("coin");
        p.rerollSome(new String[]{"0", "1", "3", "4", "5"});
        //  6 monkeys
        dice = new ArrayList<>(Arrays.asList(new Die("monkey "),new Die("coin   "),
                new Die("monkey "),new Die("saber  "),new Die("monkey "),new Die("monkey "),
                new Die("monkey "), new Die("monkey ")));
        p.setDice(dice);
        p.rerollSome(new String[]{"1" ,"3"});
        //  8 monkeys
        dice = new ArrayList<>(Arrays.asList(new Die("monkey "),new Die("monkey "),
                new Die("monkey "),new Die("monkey "),new Die("monkey "),new Die("monkey "),
                new Die("monkey "), new Die("monkey ")));
        p.setDice(dice);
        p.calculateScore(dice);
        assertEquals(4600, p.getInfo()[0]);
    }
    @Test
    public void Test67(){
        Player p = new Player(0,0);
        p.initializeDice();
        // on first roll
        p.getDice().forEach(d -> d.roll());
        //2 skull, 0 parrots, 4 swords 0 coins 1 diamonds 1 monkeys
        List<Die> dice = new ArrayList<>(Arrays.asList(new Die("skull  "),new Die("skull  "),
                new Die("diamond"),new Die("saber  "),new Die("saber  "),new Die("saber  "),
                new Die("saber  "), new Die("monkey ")));
        p.setDice(dice);
        p.setCard("diamond");
        p.rerollSome(new String[]{"3" ,"4"});
        // 2 diamonds
        dice = new ArrayList<>(Arrays.asList(new Die("skull  "),new Die("skull  "),
                new Die("diamond"),new Die("diamond"),new Die("monkey "),new Die("saber  "),
                new Die("saber  "), new Die("monkey ")));
        p.setDice(dice);
        p.calculateScore(dice);
        assertEquals(400, p.getInfo()[0]);
    }
    @Test
    public void Test68(){
        Player p = new Player(0,0);
        p.initializeDice();
        // on first roll
        p.getDice().forEach(d -> d.roll());
        //2 skull, 0 parrots, 4 swords 0 coins 1 diamonds 1 monkeys
        List<Die> dice = new ArrayList<>(Arrays.asList(new Die("skull  "),new Die("skull  "),
                new Die("diamond"),new Die("saber  "),new Die("saber  "),new Die("saber  "),
                new Die("saber  "), new Die("monkey ")));
        p.setDice(dice);
        p.setCard("coin");
        p.rerollSome(new String[]{"3" ,"4"});
        // 3 diamonds
        dice = new ArrayList<>(Arrays.asList(new Die("skull  "),new Die("skull  "),
                new Die("diamond"),new Die("diamond"),new Die("diamond"),new Die("saber  "),
                new Die("saber  "), new Die("monkey ")));
        p.setDice(dice);
        p.calculateScore(dice);
        assertEquals(500, p.getInfo()[0]);
    }
    @Test
    public void Test69(){
        Player p = new Player(0,0);
        p.initializeDice();
        // on first roll
        p.getDice().forEach(d -> d.roll());
        //2 skull, 0 parrots, 4 swords 1 coins 0 diamonds 1 monkeys
        List<Die> dice = new ArrayList<>(Arrays.asList(new Die("skull  "),new Die("skull  "),
                new Die("coin   "),new Die("saber  "),new Die("saber  "),new Die("saber  "),
                new Die("saber  "), new Die("monkey ")));
        p.setDice(dice);
        p.setCard("coin");
        p.rerollSome(new String[]{"3" ,"4", "5"});
        // 3 coins
        dice = new ArrayList<>(Arrays.asList(new Die("skull  "),new Die("skull  "),
                new Die("coin   "),new Die("coin   "),new Die("coin   "),new Die("monkey "),
                new Die("saber  "), new Die("monkey ")));
        p.setDice(dice);
        p.calculateScore(dice);
        assertEquals(600, p.getInfo()[0]);
    }
    @Test
    public void Test70(){
        Player p = new Player(0,0);
        p.initializeDice();
        // on first roll
        p.getDice().forEach(d -> d.roll());
        //2 skull, 0 parrots, 4 swords 1 coins 0 diamonds 1 monkeys
        List<Die> dice = new ArrayList<>(Arrays.asList(new Die("skull  "),new Die("skull  "),
                new Die("coin   "),new Die("saber  "),new Die("saber  "),new Die("saber  "),
                new Die("saber  "), new Die("monkey ")));
        p.setDice(dice);
        p.setCard("diamond");
        p.rerollSome(new String[]{"3" ,"4", "5"});
        // 3 coins
        dice = new ArrayList<>(Arrays.asList(new Die("skull  "),new Die("skull  "),
                new Die("coin   "),new Die("coin   "),new Die("coin   "),new Die("monkey "),
                new Die("saber  "), new Die("monkey ")));
        p.setDice(dice);
        p.calculateScore(dice);
        assertEquals(500, p.getInfo()[0]);
    }
}
