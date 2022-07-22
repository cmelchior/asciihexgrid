package dk.ilios.asciihexgrid

import dk.ilios.asciihexgrid.printers.AsciiHexPrinter
import dk.ilios.asciihexgrid.printers.SmallFlatAsciiHexPrinter
import org.junit.Before
import org.junit.Test

/**
 * Not real unit tests, I know.
 */
class FlatHexesAsciiTest {

    private lateinit var printer: AsciiHexPrinter

    @Before
    fun setUp() {
        printer = SmallFlatAsciiHexPrinter()
    }

    @Test
    fun testBoard_singlePiece() {
        val board = AsciiBoard(0, 0, 0, 0, printer)
        board.printHex("HEX", "-W-", '#', 0, 0)
        print(board.prettPrint(USE_BOX))
    }

    @Test
    fun testBoard_twoPieces() {
        val board = AsciiBoard(0, 1, 0, 0, printer)
        board.printHex("HX1", "-W-", '#', 0, 0)
        board.printHex("HX2", "-B-", '+', 1, 0)
        print(board.prettPrint(USE_BOX))
    }

    @Test
    fun testBoard_twoPieces_2nd() {
        val board = AsciiBoard(0, 0, 0, 1, printer)
        board.printHex("HX1", "-W-", '#', 0, 0)
        board.printHex("HX2", "-B-", '+', 0, 1)
        print(board.prettPrint(USE_BOX))
    }

    @Test
    fun testBoard_circle() {
        val board = AsciiBoard(0, 2, 0, 2, printer)
        board.printHex("HX1", "-B-", '#', 1, 1)
        board.printHex("HX2", "-W-", '+', 1, 0)
        board.printHex("HX3", "-W-", '-', 2, 0)
        board.printHex("HX4", "-W-", '+', 2, 1)
        board.printHex("HX5", "-W-", '•', 1, 2)
        board.printHex("HX6", "-W-", '-', 0, 2)
        board.printHex("HX7", "-W-", '•', 0, 1)
        print(board.prettPrint(USE_BOX))
    }

    @Test
    fun testBoard_line() {
        val board = AsciiBoard(0, 2, 0, 0, printer)
        board.printHex("HX1", "-B-", '#', 0, 0)
        board.printHex("HX2", "-W-", '+', 1, 0)
        board.printHex("HX3", "-W-", '+', 2, 0)
        println(board.prettPrint(USE_BOX))
    }

    @Test
    fun testBoard_fshape() {
        val board = AsciiBoard(0, 2, 0, 1, printer)
        board.printHex("HX1", "-A-", '#', 0, 0)
        board.printHex("HX2", "-B-", '+', 1, 0)
        board.printHex("HX3", "-C-", '-', 2, 0)
        board.printHex("HX4", "-D-", '•', 2, 1)
        println(board.prettPrint(USE_BOX))
    }

    @Test
    fun testBoard_spaceBetweenHexesVertical() {
        val board = AsciiBoard(0, 2, 0, 0, printer)
        board.printHex("HX1", "-B-", '#', 0, 0)
        board.printHex("HX2", "-W-", '+', 2, 0)
        println(board.prettPrint(USE_BOX))
    }

    @Test
    fun testBoard_spaceBetweenHexesHorizontal() {
        val board = AsciiBoard(0, 0, 0, 2, printer)
        board.printHex("HX1", "-B-", '#', 0, 0)
        board.printHex("HX2", "-W-", '+', 0, 2)
        println(board.prettPrint(USE_BOX))
    }

    companion object {
        const val USE_BOX = true
    }
}