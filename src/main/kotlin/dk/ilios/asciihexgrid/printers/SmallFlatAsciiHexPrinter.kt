package dk.ilios.asciihexgrid.printers

import dk.ilios.asciihexgrid.HexOrientation

class SmallFlatAsciiHexPrinter : AsciiHexPrinter() {
    private val width = 9
    private val height = 5
    private val sideLength = 2

    override fun getHex(textLine1: String, textLine2: String, fillerChar: Char): String {
        var line1 = textLine1
        var line2 = textLine2
        var hex: String = TEMPLATE
        line1 = restrictToLength(line1, 3)
        line2 = restrictToLength(line2, 3)
        hex = hex.replace("XXX", line1)
        hex = hex.replace("YYY", line2)
        return hex.replace('#', fillerChar)
    }

    override fun mapHexCoordsToCharCoords(q: Int, r: Int): IntArray {
        val result = IntArray(2)
        result[0] = 7 * q // q * (width - side)
        result[1] = 2 * q + 4 * r // height/2 * q + (height - 1) * r
        return result
    }

    override fun getMapSizeInChars(hexWidth: Int, hexHeight: Int): IntArray {
        val widthInChars = hexWidth * (width - sideLength) + sideLength
        val heightInChars = (hexWidth - 1) * height / 2 + hexHeight * height
        return intArrayOf(widthInChars, heightInChars)
    }

    override val hexOrientation: HexOrientation
        get() = HexOrientation.FLAT

    companion object {
        const val TEMPLATE = ("   _ _   \n" // 0 - 9
                + " /# # #\\ \n" // 9 - 18
                + "/# XXX #\\\n" // 18 - 27
                + "\\# YYY #/\n" // 27 - 36
                + " \\#_#_#/ ") // 36 - 45
    }
}