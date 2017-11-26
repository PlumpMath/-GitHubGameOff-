package de.sakul6499.githubgameoff.engine.graphics

import de.sakul6499.githubgameoff.engine.graphics.asset.SpriteLoader
import de.sakul6499.githubgameoff.engine.input.MouseHandler
import java.awt.Graphics

class MouseLayer : Layer("Mouse", 10000) {
    override var isActive: Boolean = true

    private val size = 16

    override fun update(delta: Long, alpha: Long) {}

    override fun render(graphics: Graphics) {
        val x = MouseHandler.MousePosition.x - size / 2
        val y = MouseHandler.MousePosition.y - size / 2
        if (isRangeValid(x, y, size, size))
            renderImage(graphics, SpriteLoader.getTile("MouseCursor"), x, y, width = size, height = size)
    }
}