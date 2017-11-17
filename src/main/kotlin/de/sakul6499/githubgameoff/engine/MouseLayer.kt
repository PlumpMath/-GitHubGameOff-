package de.sakul6499.githubgameoff.engine

import de.sakul6499.githubgameoff.engine.asset.SpriteLoader
import de.sakul6499.githubgameoff.engine.input.MouseHandler
import java.awt.Graphics

class MouseLayer: Layer("Mouse", 10000, x = 2, y = 12) {
    override var isActive: Boolean = true

    override fun update(delta: Long, alpha: Long) {}

    override fun render(graphics: Graphics) {
        renderImage(graphics, SpriteLoader.getTile("MouseCursor"), x = MouseHandler.MousePosition.x, y = MouseHandler.MousePosition.y, width = 16, height = 16)
    }
}