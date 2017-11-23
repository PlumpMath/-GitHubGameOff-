package de.sakul6499.githubgameoff.engine.graphics.asset

import java.awt.image.BufferedImage

abstract class AbstractImage {

    fun convertImage(input: BufferedImage): BufferedImage {
        return if (input.type != BufferedImage.TYPE_INT_ARGB) {
            val bi = BufferedImage(input.width, input.height, BufferedImage.TYPE_INT_ARGB)

            val g = bi.createGraphics()
            g.drawImage(input, 0, 0, input.width, input.height, null, null)
            g.dispose()

            bi
        } else input
    }

    abstract fun getImage(): BufferedImage

    abstract fun rotate(theta: Double = Math.PI / 4): AbstractImage
}