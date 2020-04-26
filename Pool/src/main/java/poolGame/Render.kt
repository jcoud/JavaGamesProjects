package poolGame

import java.awt.Canvas
import java.awt.Color
import java.awt.Graphics

internal class Render {
    companion object {
        var instance = Render()
    }

    private lateinit var g: Graphics
    private lateinit var input: Input

    fun make(g: Graphics, mainCanvas: Canvas, input: Input) {
        this.g = g
        this.input = input
        g.clearRect(0, 0, mainCanvas.width, mainCanvas.height)
        paint(g)
        g.dispose()

    }

    private fun paint(g: Graphics) {
        g.drawImage(Ref.img, 0, 0, Ref.IWp, Ref.IHp, null)
        drawLines(g)
    }

    private fun drawLines(g: Graphics) {
        val t = 8
        val k = .25
        val r = (68 * k).toInt()
        val rxy = listOf(464, 177)
        val k2 = (17 * k).toInt()
        val k4 = (70 * k * 2).toInt() // r1
        val k5 = (70 * k).toInt()
        val k6 = (70 * k * 2).toInt() // r2
        val xsA = listOf(58, 303, 337, 583)
        val ysA = listOf(34, 318)
        val xsB = listOf(34, 606)
        val ysB = listOf(56, 297)
        val s = listOf(
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

        g.color = Color.PINK
        g.drawOval(rxy[0] - r / 2, rxy[1] - r / 2, r, r)

        g.color = Color.BLUE
        g.drawRect(xsB[0], ysA[0], xsB[1] - xsB[0], ysA[1] - ysA[0])
        g.color = Color.MAGENTA
        g.drawLine(xsB[0], ysA[0], xsB[0] - k2, ysA[0] - k2)
        g.drawOval(xsB[0] - k2 - k4 / 2, ysA[0] - k2 - k4 / 2, k4, k4)

        g.drawLine(xsA[1] + (xsA[2] - xsA[1]) / 2, ysA[0], xsA[1] + (xsA[2] - xsA[1]) / 2, ysA[0] - k5)
        g.drawOval(xsA[1] + (xsA[2] - xsA[1]) / 2 - k6 / 2, ysA[0] - k5 - k6 / 2, k6, k6)

        g.drawLine(xsB[1], ysA[0], xsB[1] + k2, ysA[0] - k2)
        g.drawOval(xsB[1] + k2 - k4 / 2, ysA[0] - k2 - k4 / 2, k4, k4)

        g.drawLine(xsB[1], ysA[1], xsB[1] + k2, ysA[1] + k2)
        g.drawOval(xsB[1] + k2 - k4 / 2, ysA[1] + k2 - k4 / 2, k4, k4)

        g.drawLine(xsA[1] + (xsA[2] - xsA[1]) / 2, ysA[1], xsA[1] + (xsA[2] - xsA[1]) / 2, ysA[1] + k5)
        g.drawOval(xsA[1] + (xsA[2] - xsA[1]) / 2 - k6 / 2, ysA[1] + k5 - k6 / 2, k6, k6)

        g.drawLine(xsB[0], ysA[1], xsB[0] - k2, ysA[1] + k2)
        g.drawOval(xsB[0] - k2 - k4 / 2, ysA[1] + k2 - k4 / 2, k4, k4)

        g.color = Color.RED
        //AA
        g.drawLine(xsA[0], ysA[0], xsA[1], ysA[0])
        g.drawLine(xsA[2], ysA[0], xsA[3], ysA[0])
        g.drawLine(xsA[0], ysA[1], xsA[1], ysA[1])
        g.drawLine(xsA[2], ysA[1], xsA[3], ysA[1])
        //BB
        g.drawLine(xsB[0], ysB[0], xsB[0], ysB[1])
        g.drawLine(xsB[1], ysB[0], xsB[1], ysB[1])
        //
        for (i in 0..11) dLine(g, s[i])
    }

    private fun dLine(g: Graphics, list: List<Int>) {
        g.drawLine(list[0], list[1], list[2], list[3])
    }
}