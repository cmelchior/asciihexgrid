package dk.ilios.asciihexgrid.printers;

import dk.ilios.asciihexgrid.HexOrientation;

public class SmallPointyAsciiHexPrinter extends AsciiHexPrinter {

    public static final String TEMPLATE =
                      "   /#\\   \n"  // 0 - 10
                    + " /# # #\\ \n"  // 10 - 20
                    + "|# XXX #|\n"   // 20 - 30
                    + "|# YYY #|\n"   // 30 - 40
                    + " \\# # #/ \n"  // 40 - 50
                    + "   \\#/   \n"; // 50 - 60

    private final int width = 10;
    private final int height = 6;
    private final int sideLength = 4;
    private final int sideHeight = 2;
    private final int bordersLength = 2;

    @Override
    public String getHex(String line1, String line2, char fillerChar) {
        String hex = new String(TEMPLATE);

        line1 = restrictToLength(line1, 3);
        line2 = restrictToLength(line2, 3);

        hex = hex.replace("XXX", line1);
        hex = hex.replace("YYY", line2);

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
        int widthInChars = hexWidth * width + sideLength;
        int heightInChars = hexHeight * (height - 2) + 2;
        return new int[] { widthInChars, heightInChars};
    }

    @Override
    public HexOrientation getHexOrientation() {
        return HexOrientation.POINTY;
    }
}
