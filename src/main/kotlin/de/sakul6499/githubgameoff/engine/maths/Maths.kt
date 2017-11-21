package de.sakul6499.githubgameoff.engine.maths

object Maths {
    fun isInAABB(x: Int, y: Int, x0: Int, y0: Int, x1: Int, y1: Int): Boolean = x in (x0 + 1)..(x1 - 1) && y in (y0 + 1)..(y1 - 1)
    fun isInABWH(x: Int, y: Int, x0: Int, y0: Int, w: Int, h: Int): Boolean = x in (x0 + 1)..(x0 + w - 1) && y in (y0 + 1)..(y0 + h - 1)
}