package poolGame

import java.awt.Canvas
import java.awt.event.*
import kotlin.system.exitProcess

class Input {

    internal val keyIo: KeyIO
        get() = k

    internal val mouseMotionIo: MouseMotionIO
        get() = mm

    internal val mouseIo: MouseIO
        get() = m

    var canvas: Canvas? = null
    set(value) {field = value}

    internal class KeyIO : KeyAdapter() {
        override fun keyPressed(e: KeyEvent) {
            when (e.keyChar) {
                'x' -> exitProcess(0)
            }
        }
    }

    internal class MouseIO : MouseAdapter() {
        var mx: Int? = null
        var my: Int? = null
        override fun mousePressed(e: MouseEvent) {
            mx = e.x
            my = e.y
            System.out.printf("[mp] x:%d, y:%d\r", e.x, e.y)
        }
    }

    internal class MouseMotionIO : MouseMotionAdapter() {
        var mx: Int? = null
        var my: Int? = null
        override fun mouseMoved(e: MouseEvent) {
            mx = e.x
            my = e.y
            System.out.printf("[mm] x:%d, y:%d\r", e.x, e.y)
        }
    }

    companion object {

        private val k = KeyIO()
        private val m = MouseIO()
        private val mm = MouseMotionIO()
    }
}
