package poolGame

import sun.applet.Main
import java.awt.Dimension
import javax.swing.ImageIcon
import javax.swing.JFrame

object Ref {
    val W = 800
    val H = 400
    private val iimg = ImageIcon(Main::class.java.getResource("/board.png"))
    val img = iimg.image
    val IW = iimg.iconWidth
    val IH = iimg.iconHeight
    val per = .5
    val IWp = (IW * per).toInt()
    val IHp = (IH * per).toInt()
}
fun main(){
    RunThread().init()
}


