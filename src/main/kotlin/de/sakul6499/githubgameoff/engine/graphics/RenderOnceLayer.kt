package de.sakul6499.githubgameoff.engine.graphics

import de.sakul6499.githubgameoff.engine.maths.Vector2I
import java.awt.Graphics
import java.awt.image.BufferedImage

abstract class RenderOnceLayer(name: String, order: Int, v0: Vector2I = Vector2I(), v1: Vector2I = Vector2I(Screen.GetWidth(), Screen.GetHeight())) : BasicLayer(name, order, v0, v1) {
    var layer: BufferedImage? = null
        private set
        get() {
            if (field == null) {
                field = BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB)
                val graphics = field!!.createGraphics()
                render(graphics)
                graphics.dispose()
            }
            return field
        }

    abstract fun render(graphics: Graphics)
}