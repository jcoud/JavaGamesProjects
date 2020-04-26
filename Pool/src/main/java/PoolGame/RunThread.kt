package poolGame

import java.awt.Canvas
import java.awt.Dimension
import javax.swing.JFrame

class RunThread: Canvas(), Runnable {
    private var running: Boolean = false
    private var tickCount: Int = 0
    private val inputs = Input()

    private fun initFrame() {
        val window = JFrame()
        JFrame.setDefaultLookAndFeelDecorated(true)
        this.addMouseListener(inputs.mouseIo)
        this.addMouseMotionListener(inputs.mouseMotionIo)
        window.contentPane.addKeyListener(inputs.keyIo)
        window.title = "Pool"
//    window.layout = null
        window.preferredSize = Dimension(Ref.IWp + 15, Ref.IHp + 38)
        window.contentPane.add(this)
        print("w: ${Ref.IWp}, h: ${Ref.IHp}")
        window.pack()
        window.setLocationRelativeTo(null)
        window.isVisible = true
        window.isResizable = false
        window.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    }

    fun init() {
        initFrame()
        start()
    }
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
        Render.instance.make(bs.drawGraphics, this, inputs)
        bs.show()
    }
}