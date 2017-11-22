package de.sakul6499.githubgameoff.engine.maths

import java.awt.Color
import java.awt.Graphics

class VectorBox<out V : Vector<*>>(val v0: V, val v1: V) {
    fun isInBox(vx: Vector<*>): Boolean {
        val v0x = v0.x.toDouble()
        val v0y = v0.y.toDouble()
        val v1x = v1.x.toDouble()
        val v1y = v1.y.toDouble()
        val vxx = vx.x.toDouble()
        val vxy = vx.y.toDouble()

        return vxx > v0x && vxx < v1x && vxy > v0y && vxy < v1y
    }

    fun isNotInBox(vx: Vector<*>): Boolean = !isInBox(vx)

    fun drawBox(graphics: Graphics, color: Color = Color.RED) {
        graphics.color = color
        graphics.drawRect(v0.x.toInt(), v0.y.toInt(), v1.x.toInt() - v0.x.toInt(), v1.y.toInt() - v0.y.toInt())
    }
}