package dk.ilios.asciihexgrid.printers;

import dk.ilios.asciihexgrid.HexOrientation;

public class LargeFlatAsciiHexPrinter extends AsciiHexPrinter {

    public static final String TEMPLATE =
              "   _ _ _ _   \n" // 0 - 13
            + "  / # # # \\  \n" // 12 - 24
            + " /# # # # #\\ \n" // 24 - 36
            + "/# XXXXXXX #\\\n" // 36 - 48
            + "\\# YYYYYYY #/\n" // 48 - 60
            + " \\# # # # #/ \n" // 60 - 72
            + "  \\_#_#_#_/  \n"; // 72 - 84

    private final int width = 13;
    private final int height = 7;
    private final int sideLength = 3;
    private final int sideHeight = 3;

    @Override
    public String getHex(String line1, String line2, char filler) {
        String hex = new String(TEMPLATE);

        line1 = restrictToLength(line1, 7);
        line2 = restrictToLength(line2, 7);

        hex = hex.replace("XXXXXXX", line1);
        hex = hex.replace("YYYYYYY", line2);

        return hex.replace('#', filler);
    }

    @Override
    public int[] mapHexCoordsToCharCoords(int q, int r) {
        int[] result = new int[2];

        result[0] = (width - sideLength)*q;
        result[1] = sideHeight*q + (height - 1)*r;

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