package dk.ilios.asciihexgrid

/**
 * 2D representation of a char grid of fixed size. Useful for eg. creating ASCII art.
 * (Top,Left) has coordinates (0,0).
 */
class CharGrid(private val width: Int, private val height: Int) {

    private val grid: Array<CharArray> = Array(height) { CharArray(width) }

    init {
        prefillGrid()
    }

    /**
     * Prefill grid with spaces.
     */
    private fun prefillGrid() {
        for (i in 0 until height) {
            for (j in 0 until width) {
                addChar(j, i, ' ')
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
    fun addString(x: Int, y: Int, input: String?) {
        if (input == null || input == "") return
        for (i in 0 until input.length) {
            addChar(x + i, y, input[i])
        }
    }

    /**
     * Add a string to the grid.
     *
     * @param x Starting x coordinate.
     * @param y Starting y coordinate.
     * @param input Char to insert. Trows IndexOutOfBounds if outside grid.
     */
    fun addChar(x: Int, y: Int, input: Char) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            val maxWidth = width - 1
            val maxHeight = height - 1
            throw IndexOutOfBoundsException("($x,$y) is outside ($maxWidth,$maxHeight)")
        }
        grid[y][x] = input
    }

    /**
     * Returns a char from the grid
     */
    fun getChar(x: Int, y: Int): Char {
        return grid[y][x]
    }

    /**
     * Returns the char grid as a string, ready for output.
     *
     * @param trimToBoundingBox If true, the grid is trimmed to it's contents bounding box. If not grid is printet as is.
     */
    fun print(trimToBoundingBox: Boolean): String {
        var leftBound = if (trimToBoundingBox) width - 1 else 0
        var rightBound = if (trimToBoundingBox) 0 else width - 1
        var topBound = if (trimToBoundingBox) height - 1 else 0
        var bottomBound = if (trimToBoundingBox) 0 else height - 1

        // Find bounding box
        if (trimToBoundingBox) {
            for (i in 0 until height) {
                for (j in 0 until width) {
                    val c = grid[i][j]
                    if (c != ' ') {
                        leftBound = Math.min(leftBound, j)
                        rightBound = Math.max(rightBound, j)
                        topBound = Math.min(topBound, i)
                        bottomBound = Math.max(bottomBound, i)
                    }
                }
            }
        }

        // Print grid
        val builder = StringBuilder((width + LINE_BREAK.length) * height)
        for (i in topBound..bottomBound) {
            for (j in leftBound..rightBound) {
                builder.append(grid[i][j])
            }
            builder.append(LINE_BREAK)
        }
        return builder.toString()
    }

    companion object {
        private const val LINE_BREAK = "\n"
    }
}