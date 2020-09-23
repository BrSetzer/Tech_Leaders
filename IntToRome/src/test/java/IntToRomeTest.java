import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class IntToRomeTest {
    @Test
    void testGetRomeNum() {
        Rome rome = new Rome();
        assertEquals("XXI", rome.getRomeNum(21), "The output should be the sum of the two arguments");
        assertEquals("", rome.getRomeNum(0));
    }
}
