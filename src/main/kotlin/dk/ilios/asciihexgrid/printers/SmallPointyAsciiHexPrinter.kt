package dk.ilios.asciihexgrid.printers

import dk.ilios.asciihexgrid.HexOrientation

class SmallPointyAsciiHexPrinter : AsciiHexPrinter() {
    private val width = 10
    private val height = 6
    private val sideLength = 4
    private val sideHeight = 2
    private val bordersLength = 2
    override fun getHex(line1: String?, line2: String?, fillerChar: Char): String {
        var line1 = line1
        var line2 = line2
        var hex: String = TEMPLATE
        line1 = restrictToLength(line1, 3)
        line2 = restrictToLength(line2, 3)
        hex = hex.replace("XXX", line1)
        hex = hex.replace("YYY", line2)
        return hex.replace('#', fillerChar)
    }

    override fun mapHexCoordsToCharCoords(q: Int, r: Int): IntArray {
        val result = IntArray(2)
        result[0] = (width - bordersLength) * q + r % 2 * (height - sideHeight)
        result[1] = (height - sideHeight) * r
        return result
    }

    override fun getMapSizeInChars(hexWidth: Int, hexHeight: Int): IntArray {
        val widthInChars = hexWidth * width + sideLength
        val heightInChars = hexHeight * (height - 2) + 2
        return intArrayOf(widthInChars, heightInChars)
    }

    override val hexOrientation: HexOrientation
        get() = HexOrientation.POINTY

    companion object {
        const val TEMPLATE = ("   /#\\   \n" // 0 - 10
                + " /# # #\\ \n" // 10 - 20
                + "|# XXX #|\n" // 20 - 30
                + "|# YYY #|\n" // 30 - 40
                + " \\# # #/ \n" // 40 - 50
                + "   \\#/   \n") // 50 - 60
    }
}