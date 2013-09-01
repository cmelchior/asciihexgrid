package dk.ilios.asciihexgrid;

import dk.ilios.asciihexgrid.printers.AsciiHexPrinter;

/**
 * Description of a Ascii hex map.
 * The hex grid uses a trapezoidal or axial coordinate system, like so:
 *
 *           _ _
 *         /     \
 *    _ _ /(0,-1) \ _ _
 *  /     \  -R   /     \
 * /(-1,0) \ _ _ /(1,-1) \
 * \  -Q   /     \       /
 *  \ _ _ / (0,0) \ _ _ /
 *  /     \       /     \
 * /(-1,1) \ _ _ / (1,0) \
 * \       /     \  +Q   /
 *  \ _ _ / (0,1) \ _ _ /
 *        \  +R   /
 *         \ _ _ /
 *
 * or so (depending on hex orientation):
 *
 *        / \     / \
 *      /     \ /     \
 *     | -1,-1 |  1,-1 |
 *     |   -R  |       |
 *    / \     / \     / \
 *  /     \ /     \ /     \
 * | -1,0  |  0,0  |  1,0  |
 * |  -Q   |       |   +Q  |
 *  \     / \     / \     /
 *    \ /     \ /     \ /
 *     | -1,1  |  0,1  |
 *     |       |   +R  |
 *      \     / \     /
 *        \ /     \ /
 *
 */

public class AsciiBoard {

    private final int width;
    private final int height;

    private AsciiHexPrinter printer;
    private CharGrid grid;

    /**
     * Constructs the virtual hex board.
     *
     * @param printer Reference to the hex printer used
     */
    public AsciiBoard(int minQ, int maxQ, int minR, int maxR, AsciiHexPrinter printer) {
        this.width = maxQ - minQ + 1;
        this.height = maxR - minR + 1;
        this.printer = printer;
        this.grid = createGrid();
    }

    private CharGrid createGrid() {
        // This potentially creates the grid ½ a hexagon to heigh or wide, as we do not now given the max coordinates
        // (0,0,1,1) if both (0,1) or (1,1) is filled. This is OK, as we can fix it when outputting the grid.
        int[] gridSize = printer.getMapSizeInChars(width, height);
        return new CharGrid(gridSize[0], gridSize[1]);
    }

    /**
     *
     * @param line1 First line of text
     * @param line2 2nd line of
     * @param fillerChar Character used as filler, may be ' '
     * @param hexQ Q coordinate for the hex in the hex grid.
     * @param hexR R coordinate for the hex in the hex grid.
     */
    public void printHex(String line1, String line2, char fillerChar, int hexQ, int hexR) {

        String hex = printer.getHex(line1, line2, fillerChar);
        int[] charCoordinates = printer.mapHexCoordsToCharCoords(hexQ, hexR);
        String[] lines = hex.toString().split("\n");

        for (int i = 0; i < lines.length; i++) {
            String content = lines[i];
            for (int j = 0; j < content.length(); j++) {
                int x = charCoordinates[0] + j;
                int y = charCoordinates[1] + i;

                // Only override empty spaces
                if (grid.getChar(x,y) == ' ') {
                    grid.addChar(x, y, content.charAt(j));
                }
            }
        }
    }

    /**
     * Prints the Hexagonal map as a string.
     *
     * @param wrapInBox If true, output is wrapped in a Ascii drawn box.
     */
    public String prettPrint(boolean wrapInBox) {
        return printBoard(wrapInBox);
    }

    /**
     * Returns the Hexagonal map as a string. Any extra empty lines at the end are trimmed away,
     * but map still starts at (0,0), so eg. having a hex at (0,1) will produce whitespace at the top.
     *
     * @param wrapInBox If true, the hex map is wrapped in a ASCII bounding box.
     */
    private String printBoard(boolean wrapInBox) {
        if (wrapInBox) {
            StringBuilder sb = new StringBuilder();

            // Get content
            String[] lines = grid.print(true).split("\n");
            int contentLength = (lines.length > 0) ? lines[0].length() : 0;
            String verticalLine = getVerticalLine('=', contentLength);
            String spacerLine = getVerticalLine(' ', contentLength);

            // Build output
            sb.append(verticalLine);
            for (int i = 0; i < lines.length; i++) {
                String line = lines[i];
                sb.append("| ");
                sb.append(line);
                sb.append(" |");
                sb.append('\n');
            }

            // Flat hexes have to little bottom space as they use the _ char
            // so add a extra filler line.
            if (printer.getHexOrientation() == HexOrientation.FLAT) {
                sb.append(spacerLine);
            }

            sb.append(verticalLine);
            return sb.toString();

        } else {
            return grid.print(true);
        }
    }

    private String getVerticalLine(char filler, int contentLength) {
        StringBuilder verticalLine = new StringBuilder("| ");
        for (int i = 0; i < contentLength; i++) {
            if (i % 2 == 0) {
                verticalLine.append(filler);
            } else {
                verticalLine.append(' ');
            }
        }
        return verticalLine.append(" |\n").toString();
    }
}
