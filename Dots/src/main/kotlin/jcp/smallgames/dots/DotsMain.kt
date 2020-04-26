package jcp.smallgames.dots

import java.awt.*
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.WindowConstants

class DotsMain : Canvas(), Runnable {
    private var running: Boolean = false
    private var tickCount: Int = 0
    private val inputs = Input()


    private fun initWindow() {
        val jFrame = JFrame("Dots Game")
        JFrame.setDefaultLookAndFeelDecorated(true)
        jFrame.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        jFrame.isVisible = true
        jFrame.isResizable = false
        val gl = GridBagLayout()
        val gc = GridBagConstraints()
        val p = JPanel(gl)
        p.background = Color.BLACK

        val empty = JPanel()
        empty.preferredSize = Dimension(bs, bs)
        empty.background = Color.BLACK
        gc.anchor = GridBagConstraints.NORTHWEST
        gl.setConstraints(empty, gc)
        p.add(empty)

        val xCount = object : JPanel() {
            override fun paint(g: Graphics?) {
                super.paint(g)
                g!!.color = Color.WHITE
                for (i in 0 until width / bs) {
                    val s = i.toString() + ""
                    val a = (bs - getFontMetrics(font).stringWidth(s)) / 2
                    g.drawString(s, i * bs + a, (getFontMetrics(font).height * 0.75).toInt())
                }
            }
        }
        xCount.preferredSize = Dimension(this.preferredSize.width, bs)
        xCount.background = Color.BLACK
        gc.anchor = GridBagConstraints.NORTHEAST
        gc.insets.right = bs
        gc.weightx = 1.0
        gc.weighty = 0.0
        gc.gridx = 1
        gl.setConstraints(xCount, gc)
        p.add(xCount)

        val yCount = object : JPanel() {
            override fun paint(g: Graphics?) {
                super.paint(g)
                g!!.color = Color.WHITE
                for (i in 0 until height / bs) {
                    val s = i.toString() + ""
                    val a = (bs - getFontMetrics(font).stringWidth(s)) / 2
                    g.drawString(s, a, i * bs + getFontMetrics(font).height)
                }
            }
        }
        yCount.preferredSize = Dimension(bs, this.preferredSize.height)
        yCount.background = Color.BLACK
        gc.anchor = GridBagConstraints.SOUTHWEST
        gc.gridx = 0
        gc.insets.right = 0
        gc.insets.bottom = bs
        gc.gridy = 1
        gl.setConstraints(yCount, gc)
        p.add(yCount)

        gc.anchor = GridBagConstraints.SOUTHEAST
        gc.insets = Insets(0, 0, bs, bs)
        gc.gridx = 1
        gl.setConstraints(this, gc)
        p.add(this)

        jFrame.contentPane.add(p)
        jFrame.pack()
        jFrame.setLocationRelativeTo(null)
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
        initWindow()
        start()
    }
}