package dk.ilios.asciihexgrid.printers;

import dk.ilios.asciihexgrid.HexOrientation;

public class SmallFlatAsciiHexPrinter extends AsciiHexPrinter {

    public static final String TEMPLATE =
             "   _ _   \n"  // 0 - 9
           + " /# # #\\ \n" // 9 - 18
           + "/# XXX #\\\n" // 18 - 27
           + "\\# YYY #/\n" // 27 - 36
           + " \\#_#_#/ ";  // 36 - 45

    private final int width = 9;
    private final int height = 5;
    private final int sideLength = 2;

    @Override
    public String getHex(String line1, String line2, char filler) {
        String hex = new String(TEMPLATE);

        line1 = restrictToLength(line1, 3);
        line2 = restrictToLength(line2, 3);

        hex = hex.replace("XXX", line1);
        hex = hex.replace("YYY", line2);

        return hex.replace('#', filler);
    }

    @Override
    public int[] mapHexCoordsToCharCoords(int q, int r) {
        int[] result = new int[2];

        result[0] = 7*q;        // q * (width - side)
        result[1] = 2*q + 4*r;  // height/2 * q + (height - 1) * r

        return result;
    }

    @Override
    public int[] getMapSizeInChars(int hexWidth, int hexHeight) {
        int widthInChars = hexWidth * (width - sideLength) + sideLength;
        int heightInChars = (hexWidth - 1) * height/2  + hexHeight * height;
        return new int[] { widthInChars, heightInChars};
    }

    @Override
    public HexOrientation getHexOrientation() {
        return HexOrientation.FLAT;
    }

}