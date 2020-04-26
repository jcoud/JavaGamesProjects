package jcp.smallgames.dots

import java.awt.*
import kotlin.collections.ArrayList
import kotlin.math.abs

internal class Field private constructor() {
    private var currentPlayer: Boolean = false
    private val dots = ArrayList<Dot>()
    private val chains = ArrayList<List<Dot>>(DotsMain.canvasSize.width / DotsMain.bs * DotsMain.canvasSize.height / DotsMain.bs)

    private var ch = ArrayList<Dot>()
    var completed = false
    private lateinit var selected: Dot
    private var i: Int = 0

    val list: List<Dot>
        get() = dots

    fun clearField() {
        dots.clear()
        ch.clear()
        chains.clear()
    }

    private fun containsSamePos(p: Point): Boolean {
        for ((pos) in dots) {
            if (pos == p) return true
        }
        return false
    }

    private fun getSamePlayerDotByPos(p: Point, player: Boolean): Dot? {
        for (d in dots) {
            if (d.pos == p && d.player == player) return d
        }
        return null
    }

    private fun getNeighbors(anchor: Dot): List<Dot> {
        val nb = arrayOfNulls<Dot>(8)
        nb[0] = getSamePlayerDotByPos(Point(anchor.pos.x - 1, anchor.pos.y - 1), anchor.player)
        nb[1] = getSamePlayerDotByPos(Point(anchor.pos.x - 0, anchor.pos.y - 1), anchor.player)
        nb[2] = getSamePlayerDotByPos(Point(anchor.pos.x + 1, anchor.pos.y - 1), anchor.player)
        nb[3] = getSamePlayerDotByPos(Point(anchor.pos.x + 1, anchor.pos.y - 0), anchor.player)
        nb[4] = getSamePlayerDotByPos(Point(anchor.pos.x + 1, anchor.pos.y + 1), anchor.player)
        nb[5] = getSamePlayerDotByPos(Point(anchor.pos.x - 0, anchor.pos.y + 1), anchor.player)
        nb[6] = getSamePlayerDotByPos(Point(anchor.pos.x - 1, anchor.pos.y + 1), anchor.player)
        nb[7] = getSamePlayerDotByPos(Point(anchor.pos.x - 1, anchor.pos.y - 0), anchor.player)
        val l = ArrayList<Dot>()
        for (d in nb) if (d != null) l.add(d)
        return l
    }

    private fun getChain(start: Dot) {
        ch = ArrayList()
        i = 0
        selected = start
        ch.add(start)
        findChain(start)
        print("ch: ${ch.size}, chs: ${chains.size}\r")
    }

    //есть ли две точки с такой же координатой X и две с Y
    // and
    //есть ли точки две рядом на расстоянии 1 клетки от проверяемого

    private fun isChainCompleted(): Boolean {
        var b = false
        for (anc in ch) {
            var a1 = false
            var a2 = false
            var a3 = true
            for (d in ch) {
                if (d.pos.x == anc.pos.x && abs(d.pos.y - anc.pos.y) >= 2) {
                    a1 = true; break
                }
            }
            for (d in ch) {
                if (d.pos.y == anc.pos.y && abs(d.pos.x - anc.pos.x) >= 2) {
                    a2 = true; break
                }
            }
            for (d in getNeighbors(anc)) {
                if (!d.marked) {
                    a3 = false
                    break
                }
            }
            b = b or a1 or a2 or a3
        }
        return b
    }

    private fun containsDotsInChains(chain: List<Dot>): Boolean {
        for (c in chains)
            for (d in c)
                for (dd in chain)
                    if (d == dd) return true
        return false
    }

    private fun isFitsTheRule(dotAt: Dot): Boolean {
        if (ch.size < 4) return false
        val a = getNeighbors(dotAt)
        if (a.size < 2) return false
        var b = true
        for (d in a) b = b and d.marked
        return b
    }

    private fun findChain(start: Dot) {
        i++
        if (i > 500) {
            println("Iteration count is more then 500! Loop has been stopped")
            return
        }
        val nb = getNeighbors(start)
        for (d in nb) {
            if (ch.contains(d)) {
                completed = false
                if (nb.size == 1) break
                if (ch.size >= 4 && isChainCompleted()) {
                    completed = true
                    if (!containsDotsInChains(ch)) chains.add(ch)
                    return
                }
                continue
            }
            d.marked = true
            ch.add(d)
            findChain(d)
        }
    }

    private fun add(p: Point) {
        if (containsSamePos(p)) return
        currentPlayer = !currentPlayer
        dots.add(Dot(p, currentPlayer))
    }

    fun add(x: Int, y: Int) {
        add(Point(x, y))
    }

    fun getChains(): List<List<Dot>> {
        if (dots.isNotEmpty()) getChain(dots[dots.size - 1])
        return chains
    }

    companion object {
        val instance = Field()
    }
}
