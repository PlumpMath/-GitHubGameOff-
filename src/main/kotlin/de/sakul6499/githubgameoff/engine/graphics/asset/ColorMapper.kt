package de.sakul6499.githubgameoff.engine.graphics.asset

import java.awt.Color
import java.awt.image.LookupTable
import java.util.*

class ColorMapper(from: Color, to: Color): LookupTable(0, 4) {
    private val from: IntArray = kotlin.IntArray(4, {
        from.red
        from.green
        from.blue
        from.alpha
    })
    private val to: IntArray = kotlin.IntArray(4, {
        to.red
        to.green
        to.blue
        to.alpha
    })

    override fun lookupPixel(src: IntArray, _dest: IntArray?): IntArray {
        var dest = _dest
        if(dest == null) {
            dest = kotlin.IntArray(src.size)
        }

        if(Arrays.equals(src, from))  {
            System.arraycopy(to, 0, dest, 0, to.size)
            println("CHANGE")
        } else {
            System.arraycopy(src, 0, dest, 0, src.size)
            println("SAME")
        }

        return dest
    }
}