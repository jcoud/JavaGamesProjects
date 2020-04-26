package poolGame

import java.awt.Point

internal class Field private constructor() {
    private val t = 8
    private val k = .25
    private val k2 = (17 * k).toInt()
    private val k4 = (70 * k * 2).toInt() // r1
    private val k5 = (70 * k).toInt()
    private val k6 = (70 * k * 2).toInt() // r2
    private val xsA = listOf(58, 303, 337, 583)
    private val ysA = listOf(34, 318)
    private val xsB = listOf(34, 606)
    private val ysB = listOf(56, 297)
    private val s = listOf(
            listOf(xsA[0], ysA[0], xsA[0] - 9, ysA[0] - t),
            listOf(xsA[1], ysA[0], xsA[1] + 2, ysA[0] - t),
            listOf(xsA[2], ysA[0], xsA[2] - 2, ysA[0] - t),
            listOf(xsA[3], ysA[0], xsA[3] + 9, ysA[0] - t),
            listOf(xsB[1], ysB[0], xsB[1] + t, ysB[0] - 9),
            listOf(xsB[1], ysB[1], xsB[1] + t, ysB[1] + 9),
            listOf(xsA[3], ysA[1], xsA[3] + 9, ysA[1] + t),
            listOf(xsA[2], ysA[1], xsA[2] - 2, ysA[1] + t),
            listOf(xsA[1], ysA[1], xsA[1] + 2, ysA[1] + t),
            listOf(xsA[0], ysA[1], xsA[0] - 9, ysA[1] + t),
            listOf(xsB[0], ysB[1], xsB[0] - t, ysB[1] + 9),
            listOf(xsB[0], ysB[0], xsB[0] - t, ysB[0] - 9)
    )
    val list: List<List<Int>>
        get() = s
    companion object {
        val instance = Field()
    }
}