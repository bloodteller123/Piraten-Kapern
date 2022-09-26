package pirate;

import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    void InitializeDicetest() {
        Player p = new Player("");
        assertNotNull(p.getDice());
    }
}
