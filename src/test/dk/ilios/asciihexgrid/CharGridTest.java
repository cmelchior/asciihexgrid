package dk.ilios.asciihexgrid;

import junit.framework.TestCase;

public class CharGridTest extends TestCase {

    public void testEmptyGrid() {
        CharGrid grid = new CharGrid(2,4);
        assertEquals("  \n  \n  \n  \n", grid.print(false));
    }

    public void testInput_String() {
        CharGrid grid = new CharGrid(2,2);
        grid.addString(0,0, "12");

        assertEquals("12\n  \n", grid.print(false));
    }

    public void testInput_MultipleLines() {
        CharGrid grid = new CharGrid(2,2);
        grid.addString(0,0, "12");
        grid.addString(0,1, "34");

        assertEquals("12\n34\n", grid.print(false));
    }

    public void testToLongInput() {
        CharGrid grid = new CharGrid(2,2);

        try {
            grid.addString(0,0, "123");
            fail("No exception");
        } catch (IndexOutOfBoundsException e) {
            assertTrue(true);
        }
    }
}
