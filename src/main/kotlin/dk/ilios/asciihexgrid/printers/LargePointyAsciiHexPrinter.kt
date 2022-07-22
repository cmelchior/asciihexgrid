package dk.ilios.asciihexgrid.printers

import dk.ilios.asciihexgrid.HexOrientation

class LargePointyAsciiHexPrinter : AsciiHexPrinter() {
    private val width = 14
    private val height = 9
    private val sideWidth = 6 // Size from center to left/right border
    private val sideHeight = 3 // Size from top to left/right border
    private val bordersLength = 2 // Size of the combined left/right borders
    override fun getHex(line1: String?, line2: String?, fillerChar: Char): String {
        var line1 = line1
        var line2 = line2
        var hex: String = TEMPLATE
        line1 = restrictToLength(line1, 7)
        line2 = restrictToLength(line2, 7)
        hex = hex.replace("XXXXXXX", line1)
        hex = hex.replace("YYYYYYY", line2)
        return hex.replace('#', fillerChar)
    }

    override fun mapHexCoordsToCharCoords(q: Int, r: Int): IntArray {
        val result = IntArray(2)
        result[0] = (width - bordersLength) * q + r % 2 * (height - sideHeight)
        result[1] = (height - sideHeight) * r
        return result
    }

    override fun getMapSizeInChars(hexWidth: Int, hexHeight: Int): IntArray {
        val widthInChars = hexWidth * width + sideWidth
        val heightInChars = hexHeight * (height - sideHeight) + sideHeight
        return intArrayOf(widthInChars, heightInChars)
    }

    override val hexOrientation: HexOrientation
        get() = HexOrientation.POINTY

    companion object {
        const val TEMPLATE = ("     /#\\     \n" // 0 - 13
                + "   /# # #\\   \n"
                + " /# # # # #\\ \n"
                + "|# XXXXXXX #|\n"
                + "|# YYYYYYY #|\n"
                + "|# # # # # #|\n"
                + " \\# # # # #/ \n"
                + "   \\# # #/   \n"
                + "     \\#/     \n")
    }
}