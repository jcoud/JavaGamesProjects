package jcp.smallgames.dots

import java.awt.Dimension
import java.awt.Point
import kotlin.math.sqrt

object Utils {
    fun addDimensionGap(dim: Dimension, gap: Int): Dimension {
        return Dimension(dim.width + gap, dim.height + gap)
    }

    fun getDistance(p1: Point, p2: Point): Double {
        val a = p1.x - p2.x
        val b = p1.y - p2.y
        return sqrt((a * a + b * b).toDouble())
    }
}