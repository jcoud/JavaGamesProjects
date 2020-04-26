package jcp.smallgames.dots

import java.awt.Point

data class Dot(val pos: Point, val player:Boolean) {
    var marked: Boolean = false
    var captured: Boolean = false
    override fun equals(other: Any?): Boolean {
        if (other !is Dot) return false
        return pos == other.pos && player == other.player
    }

    override fun hashCode(): Int {
        var result = pos.hashCode()
        result = 31 * result + player.hashCode()
        result = 31 * result + marked.hashCode()
        result = 31 * result + captured.hashCode()
        return result
    }
}