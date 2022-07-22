package dk.ilios.asciihexgrid.printers

import dk.ilios.asciihexgrid.HexOrientation

class LargeFlatAsciiHexPrinter : AsciiHexPrinter() {
    private val width = 13
    private val height = 7
    private val sideLength = 3
    private val sideHeight = 3

    override fun getHex(textLine1: String, textLine2: String, fillerChar: Char): String {
        var line1 = textLine1
        var line2 = textLine2
        var hex: String = TEMPLATE
        line1 = restrictToLength(line1, 7)
        line2 = restrictToLength(line2, 7)
        hex = hex.replace("XXXXXXX", line1)
        hex = hex.replace("YYYYYYY", line2)
        return hex.replace('#', fillerChar)
    }

    override fun mapHexCoordsToCharCoords(q: Int, r: Int): IntArray {
        val result = IntArray(2)
        result[0] = (width - sideLength) * q
        result[1] = sideHeight * q + (height - 1) * r
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
        const val TEMPLATE = ("   _ _ _ _   \n" // 0 - 13
                + "  / # # # \\  \n" // 12 - 24
                + " /# # # # #\\ \n" // 24 - 36
                + "/# XXXXXXX #\\\n" // 36 - 48
                + "\\# YYYYYYY #/\n" // 48 - 60
                + " \\# # # # #/ \n" // 60 - 72
                + "  \\_#_#_#_/  \n") // 72 - 84
    }
}