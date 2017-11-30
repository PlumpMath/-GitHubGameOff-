package de.sakul6499.githubgameoff.engine.maths

import java.awt.Color
import java.awt.Graphics

class BoxVector<V : Vector<*>>(val v0: V, val v1: V) {
    fun isInBox(vx: V): Boolean {
        val v0x = v0.x.toInt()
        val v0y = v0.y.toInt()
        val v1x = v1.x.toInt()
        val v1y = v1.y.toInt()
        val vxx = vx.x.toInt()
        val vxy = vx.y.toInt()

        return Maths.isInAABB(vxx, vxy, v0x, v0y, v1x, v1y)
    }

    fun isInBox(vb: BoxVector<V>): Boolean {
        val x0 = vb.v0.x.toDouble()
        val y0 = vb.v0.y.toDouble()
        val x0e = vb.v1.x.toDouble()
        val y0e = vb.v1.y.toDouble()

        val tx0 = v0.x.toDouble()
        val ty0 = v0.y.toDouble()
        val tx0e = v1.x.toDouble()
        val ty0e = v1.y.toDouble()

        return (x0 in (tx0 + 1)..(tx0e - 1) && y0 in (ty0 + 1)..(ty0e - 1)) && (x0e in (tx0 + 1)..(tx0e - 1) && y0e in (ty0 + 1)..(ty0e - 1))
    }

    fun intersect(vb: BoxVector<V>, mid: Boolean = false): Boolean {
        val Ax1 = vb.v0.x.toDouble()
        val Ax2 = vb.v1.x.toDouble()
        val Ay1 = vb.v0.y.toDouble()
        val Ay2 = vb.v1.y.toDouble()

        val Bx1 = v0.x.toDouble()
        val Bx2 = v1.x.toDouble()
        val By1 = v0.y.toDouble()
        val By2 = v1.y.toDouble()

//        return Ax1 < Bx2 && Ax2 > Bx1 &&
//                Ay1 > By2 && Ay2 < By1

        val xOverlap: Boolean
        val yOverlap: Boolean
        if (mid) {
            val xa = Bx2 - Bx1
            val xb = Ax2 - Ax1
            xOverlap = Maths.valueInRange(Ax1, Bx1 - xa / 2, Bx1 + xa / 2) ||
                    Maths.valueInRange(Bx1, Ax1 - xb / 2, Ax1 + xb / 2)

            val ya = By2 - By1
            val yb = Ay2 - Ay1
            yOverlap = Maths.valueInRange(Ay1, By1 - ya / 2, By1 + ya / 2) ||
                    Maths.valueInRange(By1, Ay1 - yb / 2, Ay1 + yb / 2)
        } else {
            xOverlap = Maths.valueInRange(Ax1, Bx1, Bx1 + (Bx2 - Bx1)) ||
                    Maths.valueInRange(Bx1, Ax1, Ax1 + (Ax2 - Ax1))
            yOverlap = Maths.valueInRange(Ay1, By1, By1 + (By2 - By1)) ||
                    Maths.valueInRange(By1, Ay1, Ay1 + (Ay2 - Ay1))
        }

        return xOverlap && yOverlap


//        val e0x = vb.v0.x.toDouble()
//        val e0y = vb.v1.y.toDouble()
//        val e1x = vb.v1.y.toDouble()
//        val e1y = vb.v0.y.toDouble()
//
//        return (x0 in (tx0 + 1)..(tx0e - 1) && y0 in (ty0 + 1)..(ty0e - 1)) ||
//                (x0e in (tx0 + 1)..(tx0e - 1) && y0e in (ty0 + 1)..(ty0e - 1)) ||
//                (e0x in (tx0 + 1)..(tx0e - 1) && e0y in (ty0 + 1)..(ty0e - 1)) ||
//                (e1x in (tx0 + 1)..(tx0e - 1) && e1y in (ty0 + 1)..(ty0e - 1))
    }

    fun isNotInBox(vx: V): Boolean = !isInBox(vx)
    fun isNotInBox(vb: BoxVector<V>): Boolean = !isInBox(vb)

    fun drawBox(graphics: Graphics, color: Color = Color.RED) {
        graphics.color = color
        graphics.drawRect(v0.x.toInt(), v0.y.toInt(), v1.x.toInt() - v0.x.toInt(), v1.y.toInt() - v0.y.toInt())
    }

    override fun toString(): String = "BoxVector(v0=$v0, v1=$v1)"
}