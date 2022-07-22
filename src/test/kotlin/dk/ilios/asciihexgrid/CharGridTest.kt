package dk.ilios.asciihexgrid

import junit.framework.TestCase

class CharGridTest : TestCase() {
    fun testEmptyGrid() {
        val grid = CharGrid(2, 4)
        assertEquals("  \n  \n  \n  \n", grid.print(false))
    }

    fun testInput_String() {
        val grid = CharGrid(2, 2)
        grid.addString(0, 0, "12")
        assertEquals("12\n  \n", grid.print(false))
    }

    fun testInput_MultipleLines() {
        val grid = CharGrid(2, 2)
        grid.addString(0, 0, "12")
        grid.addString(0, 1, "34")
        assertEquals("12\n34\n", grid.print(false))
    }

    fun testToLongInput() {
        val grid = CharGrid(2, 2)
        try {
            grid.addString(0, 0, "123")
            fail("No exception")
        } catch (e: IndexOutOfBoundsException) {
            assertTrue(true)
        }
    }
}