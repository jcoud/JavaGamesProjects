package jcp.smallgames.dots

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
        drawAllDots()
        drawSelectedPoint()
        drawMovingCircle()
        drawDotsConnections()
        g.dispose()
    }

    private fun drawDotsConnections() {
        if (!input.keyIo.findChain) return
        g.color = Color.GREEN
        val chains = Field.instance.getChains()
        for (chain in chains) {
            var cx:Int; var cy:Int; var dx:Int; var dy:Int
            for (i in 1 until chain.size) {
                cx = chain[i - 1].pos.x * DotsMain.bs + DotsMain.bs / 2
                cy = chain[i - 1].pos.y * DotsMain.bs + DotsMain.bs / 2
                dx = chain[i].pos.x * DotsMain.bs + DotsMain.bs / 2
                dy = chain[i].pos.y * DotsMain.bs + DotsMain.bs / 2
                g.drawLine(cx, cy, dx, dy)
            }
            cx = chain.first().pos.x * DotsMain.bs + DotsMain.bs / 2
            cy = chain.first().pos.y * DotsMain.bs + DotsMain.bs / 2
            dx = chain.last().pos.x * DotsMain.bs + DotsMain.bs / 2
            dy = chain.last().pos.y * DotsMain.bs + DotsMain.bs / 2
            g.drawLine(cx, cy, dx, dy)
        }
    }

    private fun drawMovingCircle() {
        if (input.mouseMotionIo.mouseX == null || input.mouseMotionIo.mouseY == null) return
        g.color = Color(255, 0, 0, 70)
        val r = DotsMain.bs
        val off = r / 2
        val cx = input.mouseMotionIo.mouseX!! / DotsMain.bs
        val cy = input.mouseMotionIo.mouseY!! / DotsMain.bs
        g.fillOval(cx * DotsMain.bs, cy * DotsMain.bs, r, r)
    }

    private fun drawSelectedPoint() {
        if (input.mouseIo.mouseX == null || input.mouseIo.mouseY == null) return
        val r = 5
        val f = Field.instance
        val off = r / 2
        for ((pos, player) in f.list) {
            g.color = if (player) Color.RED else Color.BLUE
            g.fillOval(pos.x * DotsMain.bs, pos.y * DotsMain.bs, r, r)
        }
    }


    private fun drawAllDots() {
        for (i in 0 until DotsMain.canvasSize.width / DotsMain.bs) {
            for (j in 0 until DotsMain.canvasSize.height / DotsMain.bs) {
                val r = DotsMain.bs
                g.color = Color.GRAY
                g.drawRect(i * r, j * r, r, r)
                //                int a = (int) (r * (1 + Math.sqrt(2) / 2));
                //                g.setColor(Color.LIGHT_GRAY);
                //                g.drawOval(i * r - a / 2, j * r - a / 2, r + a, r + a);
            }
        }
    }
}