package tdgame

import java.awt.Canvas
import java.awt.Color
import java.awt.Dimension
import java.awt.image.BufferStrategy
import javax.swing.JFrame
import javax.swing.JPanel

class InitMain : Canvas(), Runnable {
    private var running: Boolean = false
    private var tickCount: Int = 0
    private val inputs = Input()

    private fun start() {
        running = true
        Thread(this).start()
    }

    override fun run() {
        var lastTime = System.nanoTime()
        var unprocessed = 0.0
        val nsPerTick = 1000000000.0 / 60.0
        var frames = 0
        var ticks = 0
        var lt = System.currentTimeMillis()
        while (running) {
            val currentTime = System.nanoTime()
            unprocessed += (currentTime - lastTime) / nsPerTick
            lastTime = currentTime
            while (unprocessed >= 1) {
                ticks++
                tick()
                unprocessed -= 1.0
            }
            run {
                frames++
                render()
            }

            try {
                Thread.sleep((((1 - unprocessed) * 1000).toInt() / 60).toLong())
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            if (System.currentTimeMillis() - lt > 1000) {
                lt += 1000
                //                System.out.printf("ticks: %d, fps: %d\r", ticks, frames);
                frames = 0
                ticks = 0
            }
        }
    }

    private fun tick() {
        tickCount++
    }

    private fun render() {
        val bs = bufferStrategy
        if (bs == null) {
            createBufferStrategy(3)
            requestFocus()
            return
        }
        Render.instance.make(bs.drawGraphics, this.width, this.height, inputs)
        bs.show()
    }
    companion object {
        val bs = 20
        val gap = 10
        val canvasSize = Dimension(bs * 30, bs * 30)
    }
    fun init() {
        this.preferredSize = Dimension(canvasSize.width + 1, canvasSize.height + 1)
        this.minimumSize = Dimension(canvasSize.width + 1, canvasSize.height + 1)
        this.maximumSize = Dimension(canvasSize.width + 1, canvasSize.height + 1)
        this.addKeyListener(inputs.keyIo)
        this.addMouseListener(inputs.mouseIo)
        this.addMouseMotionListener(inputs.mouseMotionIo)
        this.background = Color.GRAY
        initWindow()
        start()
    }

    private fun initWindow() {
        val window = JFrame("TDGame")
        JFrame.setDefaultLookAndFeelDecorated(true)
        window.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        window.isVisible = true
        window.isResizable = false
        window.add(this)
        window.pack()
        window.setLocationRelativeTo(null)
    }
}