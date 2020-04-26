package jcp.smallgames.dots

import java.awt.event.*
import kotlin.system.exitProcess

class Input {

    internal val keyIo: KeyIO
        get() = k

    internal val mouseMotionIo: MouseMotionIO
        get() = mm

    internal val mouseIo: MouseIO
        get() = m

    internal class KeyIO : KeyAdapter() {
        var findChain: Boolean = false
        override fun keyPressed(e: KeyEvent) {
            when (e.keyChar) {
                'x' -> exitProcess(0)
                'f' -> findChain = !findChain
                'n' -> Field.instance.clearField()
            }
        }
    }

    internal class MouseIO : MouseAdapter() {
        var mouseX: Int? = null
        var mouseY: Int? = null

        override fun mousePressed(e: MouseEvent) {
            mouseX = e.x
            mouseY = e.y
            Field.instance.add(mouseX!! / DotsMain.bs, mouseY!! / DotsMain.bs)
            System.out.printf("[mp] x:%d, y:%d\r", mouseX!! / DotsMain.bs, mouseY!! / DotsMain.bs)
        }
    }

    internal class MouseMotionIO : MouseMotionAdapter() {
        var mouseX: Int? = null
        var mouseY: Int? = null

        override fun mouseMoved(e: MouseEvent) {
            mouseX = e.x
            mouseY = e.y
            System.out.printf("[mm] x:%d, y:%d\r", mouseX!! / DotsMain.bs, mouseY!! / DotsMain.bs)
        }
    }

    companion object {

        private val k = KeyIO()
        private val m = MouseIO()
        private val mm = MouseMotionIO()
    }
}
