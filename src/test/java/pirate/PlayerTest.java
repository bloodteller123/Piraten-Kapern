package pirate;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PlayerTest {

    @Test
    void InitializeDicetest() {
        Player p = new Player(0,0);
        assertNotNull(p.getDice());
    }

    @Test
    void InitializeSkulls(){
        Player p = new Player(0,0);
        assertNotNull(p.getSkulls());
    }
    @Test
    void addSkullsIndicesTest(){
        Player p = new Player(0,0);
        ArrayList<Die> dice = new ArrayList<>(Arrays.asList(new Die("skull  "),new Die("skull  "),
                new Die("skull  "),new Die("skull  "),new Die("skull  "),new Die("skull  "),
                new Die("skull  "), new Die("skull  ")));
        addSkulls(dice);
        assertEquals(8, p.getSkulls().size());
    }
}
