package de.sakul6499.githubgameoff.engine.graphics

import java.awt.Graphics
import java.awt.image.BufferedImage

abstract class RenderOnceLayer(name: String, order: Int, x: Int = 0, y: Int = 0, width: Int = Screen.GetWidth(), height: Int = Screen.GetHeight()) : BasicLayer(name, order, x, y, width, height) {
    var layer: BufferedImage? = null
        private set
        get() {
            if (field == null) {
                field = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
                val graphics = field!!.createGraphics()
                render(graphics)
                graphics.dispose()
            }
            return field
        }

    abstract fun render(graphics: Graphics)
}