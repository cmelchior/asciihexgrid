package dk.ilios.asciihexgrid;


/**
 * 2D representation of a char grid of fixed size. Useful for eg. creating ASCII art.
 * (Top,Left) has coordinates (0,0).
 */
public class CharGrid {

    private static final String LINE_BREAK = "\n";

    private final int width;
    private final int height;
    private final char[][] grid;

    public CharGrid(int width, int height) {
        this.width = width;
        this.height = height;
        grid = new char[this.height][this.width];
        prefillGrid();
    }

    /**
     * Prefill grid with spaces.
     */
    private void prefillGrid() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                addChar(j,i,' ');
            }
        }
    }

    /**
     * Add a string to the grid.
     *
     * @param x Starting x coordinate.
     * @param y Starting y coordinate.
     * @param input String put input. String will not wrap, but throws IndexOutOfBounds if to long.
     */
    public void addString(int x, int y, String input) {
        if (input == null || input.equals("")) return;
        for (int i = 0; i < input.length(); i++) {
            addChar(x + i, y, input.charAt(i));
        }
    }

    /**
     * Add a string to the grid.
     *
     * @param x Starting x coordinate.
     * @param y Starting y coordinate.
     * @param input Char to insert. Trows IndexOutOfBounds if outside grid.
     */
    public void addChar(int x, int y, char input) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            int maxWidth = width - 1;
            int maxHeight = height - 1;
            throw new IndexOutOfBoundsException("("+x+","+y+") is outside ("+maxWidth+","+maxHeight+")");
        }

        grid[y][x] = input;
    }

    /**
     * Returns a char from the grid
     */
    public char getChar(int x, int y) {
        return grid[y][x];
    }

    /**
     * Returns the char grid as a string, ready for output.
     *
     * @param trimToBoundingBox If true, the grid is trimmed to it's contents bounding box. If not grid is printet as is.
     */
    public String print(boolean trimToBoundingBox) {

        int leftBound = trimToBoundingBox ? width - 1 : 0;
        int rightBound = trimToBoundingBox ? 0 : width - 1;
        int topBound = trimToBoundingBox ? height - 1: 0;
        int bottomBound = trimToBoundingBox ? 0 : height - 1;

        // Find bounding box
        if (trimToBoundingBox) {
            for(int i = 0; i < height; i++) {
                for(int j = 0; j < width; j++) {
                    char c = grid[i][j];
                    if (c != ' ') {
                        leftBound = Math.min(leftBound, j);
                        rightBound = Math.max(rightBound, j);
                        topBound = Math.min(topBound, i);
                        bottomBound = Math.max(bottomBound, i);
                    }
                }
            }
        }

        // Print grid
        StringBuilder builder = new StringBuilder((width + LINE_BREAK.length()) * height);
        for(int i = topBound; i <= bottomBound; i++) {
            for(int j = leftBound; j <= rightBound; j++) {
                builder.append(grid[i][j]);
            }
            builder.append(LINE_BREAK);
        }
        return builder.toString();
    }
}
