package pirate;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FortuneCardTest {
    @Test
    void InitializeFC() {
        FortuneCard fc = new FortuneCard();
        assertNotNull(fc.getFC());
        assertEquals(35, fc.getFC().size());
    }
}
