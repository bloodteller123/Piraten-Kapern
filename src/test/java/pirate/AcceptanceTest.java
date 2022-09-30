package pirate;


import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;

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
}
