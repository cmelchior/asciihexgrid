package dk.ilios.asciihexgrid;

import dk.ilios.asciihexgrid.printers.AsciiHexPrinter;
import dk.ilios.asciihexgrid.printers.SmallFlatAsciiHexPrinter;
import org.junit.Before;
import org.junit.Test;

/**
 * Not real unit tests, I know.
 */
public class FlatHexesAsciiTest {

    public static final boolean USE_BOX = false;

    AsciiHexPrinter printer;

    @Before
    public void setUp() {
        printer = new SmallFlatAsciiHexPrinter();
    }

    @Test
    public void testBoard_singlePiece() {
        AsciiBoard board = new AsciiBoard(0,0,0,0, printer);
        board.printHex("HEX","-W-", '#', 0, 0);

        System.out.print(board.prettPrint(USE_BOX));
    }

    @Test
    public void testBoard_twoPieces() {
        AsciiBoard board = new AsciiBoard(0,1,0,0, printer);
        board.printHex("HX1","-W-", '#', 0, 0);
        board.printHex("HX2","-B-", '+', 1, 0);

        System.out.print(board.prettPrint(USE_BOX));
    }

    @Test
    public void testBoard_twoPieces_2nd() {
        AsciiBoard board = new AsciiBoard(0,0,0,1, printer);
        board.printHex("HX1","-W-", '#', 0, 0);
        board.printHex("HX2","-B-", '+', 0, 1);

        System.out.print(board.prettPrint(USE_BOX));
    }


    @Test
    public void testBoard_circle() {
        AsciiBoard board = new AsciiBoard(0,2,0,2, printer);
        board.printHex("HX1","-B-", '#', 1, 1);
        board.printHex("HX2","-W-", '+', 1, 0);
        board.printHex("HX3","-W-", '-', 2, 0);
        board.printHex("HX4","-W-", '+', 2, 1);
        board.printHex("HX5","-W-", '•', 1, 2);
        board.printHex("HX6","-W-", '-', 0, 2);
        board.printHex("HX7","-W-", '•', 0, 1);

        System.out.print(board.prettPrint(USE_BOX));
    }

    @Test
    public void testBoard_line() {
        AsciiBoard board = new AsciiBoard(0,2,0,0, printer);
        board.printHex("HX1","-B-", '#', 0, 0);
        board.printHex("HX2","-W-", '+', 1, 0);
        board.printHex("HX3","-W-", '+', 2, 0);

        System.out.println(board.prettPrint(USE_BOX));
    }

    @Test
    public void testBoard_fshape() {
        AsciiBoard board = new AsciiBoard(0,2,0,1, printer);
        board.printHex("HX1","-B-", '•', 0, 0);
        board.printHex("HX2","-W-", '-', 1, 0);
        board.printHex("HX3","-W-", '-', 2, 0);
        board.printHex("HX4","-B-", '•', 2, 1);

        System.out.println(board.prettPrint(USE_BOX));
    }

    @Test
    public void testBoard_spaceBetweenHexesVertical() {
        AsciiBoard board = new AsciiBoard(0,2,0,0, printer);
        board.printHex("HX1","-B-", '#', 0, 0);
        board.printHex("HX2","-W-", '+', 2, 0);

        System.out.println(board.prettPrint(USE_BOX));
    }

    @Test
    public void testBoard_spaceBetweenHexesHorizontal() {
        AsciiBoard board = new AsciiBoard(0,0,0,2, printer);
        board.printHex("HX1","-B-", '#', 0, 0);
        board.printHex("HX2","-W-", '+', 0, 2);

        System.out.println(board.prettPrint(USE_BOX));
    }
}