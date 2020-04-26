package tdgame

import tdgame.obj.EnumNodes
import tdgame.obj.EnumObj
import java.awt.Color
import java.awt.Graphics

internal class Render {
    companion object {
        var instance = Render()
    }

    private lateinit var g: Graphics
    private lateinit var input: Input

    fun make(g: Graphics, dimW: Int, dimH: Int, input: Input) {
        this.g = g
        this.input = input
        g.clearRect(0, 0, dimW, dimH)
        ren()
        g.dispose()
    }

    private fun ren() {
        val r = 5
        for (o in EnumObj.values()) {
            g.color = o.color
            g.fillRect(o.pos.x, o.pos.y, o.size.width, o.size.height)
            g.color = Color.BLACK
            val cx = o.size.width / 2 - r / 2 + o.pos.x
            val cy = o.size.height / 2 - r / 2 + o.pos.y
            g.fillOval(cx, cy, r, r)
            val nx = EnumNodes.N1.pos.x
            val ny = EnumNodes.N1.pos.y
            g.fillOval(nx, ny, r, r)
            g.drawLine(cx, cy, nx, ny)
        }
    }
}