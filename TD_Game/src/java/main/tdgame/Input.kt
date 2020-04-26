package tdgame

import java.awt.event.*
import kotlin.system.exitProcess

class Input {

    internal val keyIo: KeyIO get() = k
    internal val mouseMotionIo: MouseMotionIO get() = mm
    internal val mouseIo: MouseIO get() = m

    internal class KeyIO : KeyAdapter() {
        override fun keyPressed(e: KeyEvent) {
            when (e.keyChar) {
                'x' -> exitProcess(0)
            }
        }
    }

    internal class MouseIO : MouseAdapter() {
        override fun mousePressed(e: MouseEvent) {
        }
    }

    internal class MouseMotionIO : MouseMotionAdapter() {
        override fun mouseMoved(e: MouseEvent) {
        }
    }

    companion object {

        private val k = KeyIO()
        private val m = MouseIO()
        private val mm = MouseMotionIO()
    }
}
