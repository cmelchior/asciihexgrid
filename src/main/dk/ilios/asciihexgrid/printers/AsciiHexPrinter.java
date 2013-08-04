package dk.ilios.asciihexgrid.printers;

import dk.ilios.asciihexgrid.HexOrientation;

public abstract class AsciiHexPrinter {

    /**
     * Returns the hex
     */
    public abstract String getHex(String line1, String line2, char fillerChar);

    /**
     * Viewing the board as a grid of hexes. Each hex has a bounding box. Map top-left of bounding box given by hex
     * coordinates to same area viewed as char grid.
     *
     * @returns A int[2] with (x,y) char coordinates. (top,left) is (0,0)
     */
    public abstract int[] mapHexCoordsToCharCoords(int q, int r);

    /**
     * Returns the bounding box in chars for a map of the given size
     *
     * @param hexWidth  Size of board in hexes horisontally.
     * @param hexHeight Size of board in hexes verticall.
     * @return A int[2]: int[0] gives the width in chars and int[1] gives the height.
     */
    public abstract int[] getMapSizeInChars(int hexWidth, int hexHeight);


    /**
     * Returns the orientation of hexes from the given HexPrinter
     */
    public abstract HexOrientation getHexOrientation();

    /**
     * Makes sure that a string has the given length, using " " (whitespace) if input string is shorter.
     */
    protected String restrictToLength(String str, int length) {
        String result = "  ";
        if (str != null) {
            if (str.length() > length) {
                result = str.toUpperCase().substring(0, length);
            } else if (str.length() < length) {
                result = pad(str.toUpperCase(), length - str.length());
            } else {
                result = str;
            }
        }

        return result;
    }

    /**
     * Pads whitespace to both sides, effectively centering the text.
     * Padding starts at the left side
     * @param s
     * @param n
     * @return
     */
    private String pad(String s, int n) {
        while (n > 0) {
            if (n % 2 == 0) {
                s = " " + s;
            } else {
                s = s + " ";
            }
            n--;
        }

        return s;
    }
}
