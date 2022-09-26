package pirate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PlayerTest {

    @Test
    void InitializeDicetest() {
        Player p = new Player(0,0);
        assertNotNull(p.getDice());
    }
}
