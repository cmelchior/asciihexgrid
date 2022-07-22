package dk.ilios.asciihexgrid

import dk.ilios.asciihexgrid.printers.AsciiHexPrinter

/**
 * Description of a Ascii hex map.
 * The hex grid uses a trapezoidal or axial coordinate system, like so:
 *
 * _ _
 * /     \
 * _ _ /(0,-1) \ _ _
 * /     \  -R   /     \
 * /(-1,0) \ _ _ /(1,-1) \
 * \  -Q   /     \       /
 * \ _ _ / (0,0) \ _ _ /
 * /     \       /     \
 * /(-1,1) \ _ _ / (1,0) \
 * \       /     \  +Q   /
 * \ _ _ / (0,1) \ _ _ /
 * \  +R   /
 * \ _ _ /
 *
 * or so (depending on hex orientation):
 *
 * / \     / \
 * /     \ /     \
 * | -1,-1 |  1,-1 |
 * |   -R  |       |
 * / \     / \     / \
 * /     \ /     \ /     \
 * | -1,0  |  0,0  |  1,0  |
 * |  -Q   |       |   +Q  |
 * \     / \     / \     /
 * \ /     \ /     \ /
 * | -1,1  |  0,1  |
 * |       |   +R  |
 * \     / \     /
 * \ /     \ /
 *
 */
class AsciiBoard(minQ: Int, maxQ: Int, minR: Int, maxR: Int, printer: AsciiHexPrinter) {
    private val width: Int
    private val height: Int
    private val printer: AsciiHexPrinter
    private val grid: CharGrid

    /**
     * Constructs the virtual hex board.
     *
     * @param printer Reference to the hex printer used
     */
    init {
        width = maxQ - minQ + 1
        height = maxR - minR + 1
        this.printer = printer
        grid = createGrid()
    }

    private fun createGrid(): CharGrid {
        // This potentially creates the grid ½ a hexagon to heigh or wide, as we do not now given the max coordinates
        // (0,0,1,1) if both (0,1) or (1,1) is filled. This is OK, as we can fix it when outputting the grid.
        val gridSize = printer.getMapSizeInChars(width, height)
        return CharGrid(gridSize!![0], gridSize[1])
    }

    /**
     *
     * @param line1 First line of text
     * @param line2 2nd line of
     * @param fillerChar Character used as filler, may be ' '
     * @param hexQ Q coordinate for the hex in the hex grid.
     * @param hexR R coordinate for the hex in the hex grid.
     */
    fun printHex(line1: String?, line2: String?, fillerChar: Char, hexQ: Int, hexR: Int) {
        val hex = printer.getHex(line1, line2, fillerChar)
        val charCoordinates = printer.mapHexCoordsToCharCoords(hexQ, hexR)
        val lines = hex.toString().split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        for (i in lines.indices) {
            val content = lines[i]
            for (j in 0 until content.length) {
                val x = charCoordinates!![0] + j
                val y = charCoordinates[1] + i

                // Only override empty spaces
                if (grid.getChar(x, y) == ' ') {
                    grid.addChar(x, y, content[j])
                }
            }
        }
    }

    /**
     * Prints the Hexagonal map as a string.
     *
     * @param wrapInBox If true, output is wrapped in a Ascii drawn box.
     */
    fun prettPrint(wrapInBox: Boolean): String? {
        return printBoard(wrapInBox)
    }

    /**
     * Returns the Hexagonal map as a string. Any extra empty lines at the end are trimmed away,
     * but map still starts at (0,0), so eg. having a hex at (0,1) will produce whitespace at the top.
     *
     * @param wrapInBox If true, the hex map is wrapped in a ASCII bounding box.
     */
    private fun printBoard(wrapInBox: Boolean): String? {
        return if (wrapInBox) {
            val sb = StringBuilder()

            // Get content
            val lines = grid.print(true).split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val contentLength = if (lines.size > 0) lines[0].length else 0
            val verticalLine = getVerticalLine('=', contentLength)
            val spacerLine = getVerticalLine(' ', contentLength)

            // Build output
            sb.append(verticalLine)
            for (i in lines.indices) {
                val line = lines[i]
                sb.append("| ")
                sb.append(line)
                sb.append(" |")
                sb.append('\n')
            }

            // Flat hexes have to little bottom space as they use the _ char
            // so add a extra filler line.
            if (printer.hexOrientation == HexOrientation.FLAT) {
                sb.append(spacerLine)
            }
            sb.append(verticalLine)
            sb.toString()
        } else {
            grid.print(true)
        }
    }

    private fun getVerticalLine(filler: Char, contentLength: Int): String {
        val verticalLine = StringBuilder("| ")
        for (i in 0 until contentLength) {
            if (i % 2 == 0) {
                verticalLine.append(filler)
            } else {
                verticalLine.append(' ')
            }
        }
        return verticalLine.append(" |\n").toString()
    }
}