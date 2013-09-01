package dk.ilios.asciihexgrid.printers;

import dk.ilios.asciihexgrid.HexOrientation;

public class LargePointyAsciiHexPrinter extends AsciiHexPrinter {

    public static final String TEMPLATE =
                      "     /#\\     \n"  // 0 - 13
                    + "   /# # #\\   \n"
                    + " /# # # # #\\ \n"
                    + "|# XXXXXXX #|\n"
                    + "|# YYYYYYY #|\n"
                    + "|# # # # # #|\n"
                    + " \\# # # # #/ \n"
                    + "   \\# # #/   \n"
                    + "     \\#/     \n";

    private final int width = 14;
    private final int height = 9;
    private final int sideWidth = 6;  // Size from center to left/right border
    private final int sideHeight = 3; // Size from top to left/right border
    private final int bordersLength = 2; // Size of the combined left/right borders

    @Override
    public String getHex(String line1, String line2, char fillerChar) {
        String hex = new String(TEMPLATE);

        line1 = restrictToLength(line1, 7);
        line2 = restrictToLength(line2, 7);

        hex = hex.replace("XXXXXXX", line1);
        hex = hex.replace("YYYYYYY", line2);

        return hex.replace('#', fillerChar);
    }

    @Override
    public int[] mapHexCoordsToCharCoords(int q, int r) {
        int[] result = new int[2];
        result[0] = (width - bordersLength)*q + ((r%2) * (height - sideHeight));
        result[1] = (height - sideHeight)*r;

        return result;
    }

    @Override
    public int[] getMapSizeInChars(int hexWidth, int hexHeight) {
        int widthInChars = hexWidth * width + sideWidth;
        int heightInChars = hexHeight * (height - sideHeight) + sideHeight;
        return new int[] { widthInChars, heightInChars};
    }

    @Override
    public HexOrientation getHexOrientation() {
        return HexOrientation.POINTY;
    }
}
