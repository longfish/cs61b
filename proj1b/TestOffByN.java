import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestOffByN {
    @Test
    public void testEqualChars() {
        OffByN obN = new OffByN(5);
        assertTrue(obN.equalChars('a', 'f'));
        assertFalse(obN.equalChars('a', ' '));
        assertFalse(obN.equalChars('a', 'a'));
    }
}
