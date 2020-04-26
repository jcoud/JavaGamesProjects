package tdgame.obj

import java.awt.Color
import java.awt.Dimension
import java.awt.Point

enum class EnumObj(var speed:Int, var color: Color, var size: Dimension, var pos: Point) {
    O1(20, Color.RED, Dimension(40,40), Point(100,100)),
    O2(30, Color.GREEN, Dimension(50,50), Point(200,100));

}